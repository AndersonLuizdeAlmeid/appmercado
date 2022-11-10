package com.example.appstore.Model;

public class PessoaModel {

    private String nomeProduto;
    private Double preco;
    private Integer codigo;

    public String getNomeProduto(){return nomeProduto;}
    public void setNomeProduto(String nomeProduto){this.nomeProduto = nomeProduto;}

    public Double getPreco(){return preco;}
    public void setPreco(Double preco){this.preco = preco;}

    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

}
