package com.example.appstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appstore.Model.PessoaModel;
import com.example.appstore.PessoaRepository.PessoaRepository;
import com.example.appstore.Utils.Utils;

public class AddActivity extends AppCompatActivity {

    EditText editTextNomeProduto;
    EditText editTextPreco;
    Button buttonSalvar;
    Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //VINCULA OS COMPONENTES DA TELA COM OS DA ATIVIDADE
        this.CriarComponentes();
        //CRIA OS EVENTOS DOS COMPONENTES
        this.CriarEventos();
    }

    protected void CriarComponentes(){
        editTextNomeProduto = (EditText) this.findViewById(R.id.editTextNomeProduto);
        editTextPreco       = (EditText) this.findViewById(R.id.editTextPreco);
        buttonSalvar        = (Button) this.findViewById(R.id.buttonSalvar);
        buttonVoltar        = (Button) this.findViewById(R.id.buttonVoltar);
    }

    protected void CriarEventos(){
        //CRIANDO EVENTO NO BOTÃO SALVAR
        buttonSalvar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Salvar_onClick();
            }
        });

        //CRIANDO EVENTO NO BOTÃO VOLTAR
        buttonVoltar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intentMainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentMainActivity);
                finish();
            }
        });
    }
    //VALIDA OS CAMPOS E SALVA AS INFORMAÇÕES NO BANCO DE DADOS
    protected  void Salvar_onClick(){

        if(editTextNomeProduto.getText().toString().trim().equals("")){
            Utils.Alert(this, "Nome do produto Obrigatório");
            editTextNomeProduto.requestFocus();
        }
        else if(editTextPreco.getText().toString().trim().equals("")){
            Utils.Alert(this, "Preço Obrigatório");
            editTextPreco.requestFocus();
        }

        else{
            /*CRIANDO UM OBJETO PESSOA*/
            PessoaModel pessoaModel = new PessoaModel();
            /*SETANDO O VALOR DO CAMPO NOME*/
            pessoaModel.setNomeProduto(editTextNomeProduto.getText().toString().trim());
            /*SETANDO O ENDEREÇO*/
            pessoaModel.setPreco(Double.valueOf(editTextPreco.getText().toString().trim()));

            /*SALVANDO UM NOVO REGISTRO*/
            new PessoaRepository(this).Salvar(pessoaModel);
            /*MENSAGEM DE SUCESSO!*/
            Utils.Alert(this,"Registro Salvo");
            LimparCampos();
        }
    }
    //LIMPA OS CAMPOS APÓS SALVAR AS INFORMAÇÕES
    protected void LimparCampos(){
        editTextNomeProduto.setText(null);
        editTextPreco.setText(null);
    }
}