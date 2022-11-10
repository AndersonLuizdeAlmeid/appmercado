package com.example.appstore.PessoaRepository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.example.appstore.Utils.DatabaseUtil;
import com.example.appstore.Model.PessoaModel;

public class PessoaRepository {

    DatabaseUtil databaseUtil;

    /***
     * CONSTRUTOR
     * @param context
     */
    public PessoaRepository(Context context){

        databaseUtil =  new DatabaseUtil(context);

    }

    /***
     * SALVA UM NOVO REGISTRO NA BASE DE DADOS
     * @param pessoaModel
     */
    public void Salvar(PessoaModel pessoaModel){

        ContentValues contentValues =  new ContentValues();
        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        contentValues.put("ds_nomeProduto",       pessoaModel.getNomeProduto());
        contentValues.put("ds_preco",   pessoaModel.getPreco());


        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        databaseUtil.GetConexaoDataBase().insert("tb_produto",null,contentValues);

    }

    /***
     * ATUALIZA UM REGISTRO JÁ EXISTENTE NA BASE
     * @param pessoaModel
     */
    public void Atualizar(PessoaModel pessoaModel){

        ContentValues contentValues =  new ContentValues();

        /*MONTA OS PARAMENTROS PARA REALIZAR UPDATE NOS CAMPOS*/
        contentValues.put("ds_nomeProduto",       pessoaModel.getNomeProduto());
        contentValues.put("ds_preco",   pessoaModel.getPreco());

        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/
        databaseUtil.GetConexaoDataBase().update("tb_produto", contentValues, "id_produto = ?", new String[]{Integer.toString(pessoaModel.getCodigo())});
    }

    /***
     * EXCLUI UM REGISTRO PELO CÓDIGO
     * @param codigo
     * @return
     */
    public Integer Excluir(int codigo){

        //EXCLUINDO  REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS
        return databaseUtil.GetConexaoDataBase().delete("tb_produto","id_produto = ?", new String[]{Integer.toString(codigo)});
    }

    /***
     * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
     * @param codigo
     * @return
     */
    @SuppressLint("Range")
    public PessoaModel GetPessoa(int codigo){


        Cursor cursor =  databaseUtil.GetConexaoDataBase().rawQuery("SELECT * FROM tb_pessoa WHERE id_pessoa= "+ codigo,null);

        cursor.moveToFirst();

        ///CRIANDO UMA NOVA PESSOAS
        PessoaModel pessoaModel =  new PessoaModel();

        //ADICIONANDO OS DADOS DA PESSOA
        pessoaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_produto")));
        pessoaModel.setNomeProduto(cursor.getString(cursor.getColumnIndex("ds_nomeProduto")));
        pessoaModel.setPreco(Double.valueOf(cursor.getString(cursor.getColumnIndex("ds_preco"))));

        //RETORNANDO A PESSOA
        return pessoaModel;

    }

    /***
     * CONSULTA TODAS AS PESSOAS CADASTRADAS NA BASE
     * @return
     */
    @SuppressLint("Range")
    public List<PessoaModel> SelecionarTodos(){

        List<PessoaModel> pessoas = new ArrayList<PessoaModel>();


        //MONTA A QUERY A SER EXECUTADA
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id_produto,      ");
        stringBuilderQuery.append("        ds_nomeProduto,        ");
        stringBuilderQuery.append("        ds_preco,    ");
        stringBuilderQuery.append("  FROM  tb_produto       ");
        stringBuilderQuery.append(" ORDER BY ds_nomeProduto       ");


        //CONSULTANDO OS REGISTROS CADASTRADOS
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        /*POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
        cursor.moveToFirst();


        PessoaModel pessoaModel;

        //REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR
        while (!cursor.isAfterLast()){

            /* CRIANDO UMA NOVA PESSOAS */
            pessoaModel =  new PessoaModel();

            //ADICIONANDO OS DADOS DA PESSOA
            pessoaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_produto")));
            pessoaModel.setNomeProduto(cursor.getString(cursor.getColumnIndex("ds_nomeProduto")));
            pessoaModel.setPreco(Double.valueOf(cursor.getString(cursor.getColumnIndex("ds_preco"))));


            //ADICIONANDO UMA PESSOA NA LISTA
            pessoas.add(pessoaModel);

            //VAI PARA O PRÓXIMO REGISTRO
            cursor.moveToNext();
        }

        //RETORNANDO A LISTA DE PESSOAS
        return pessoas;

    }
}

