package com.example.augusto.projeto2do.modelo;

/**
 * Created by augusto on 11/11/15.
 */
public class Usuario {

    public static final String TABELA = "usuario";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NOME = "nome";
    public static final String SEXO = "sexo";

    private String username;
    private String password;
    private String nome;
    private String sexo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
