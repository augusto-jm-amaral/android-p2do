package com.example.augusto.projeto2do.modelo;

/**
 * Created by augusto on 11/11/15.
 */
public class Categoria {

    public static final String TABELA = "categoria";
    public static final String ID = "id";
    public static final String NOME = "nome";

    private int id;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
