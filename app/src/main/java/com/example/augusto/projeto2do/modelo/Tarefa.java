package com.example.augusto.projeto2do.modelo;

import java.util.Date;

/**
 * Created by augusto on 11/11/15.
 */
public class Tarefa {

    public static final String TABELA = "tarefa";
    public static final String ID = "id";
    public static final String DESCRICAO = "descricao";
    public static final String DATA_LIMITE = "dataLimite";
    public static final String DATA_LEMBRETE = "dataLembrete";
    public static final String DATA_REALIZACAO = "datarealizacao";
    public static final String STATUS = "status";
    public static final String USERNAME = "username";
    public static final String ID_CATEGORIA = "idcategoria";


    private int id;
    private String descricao;
    private Date dataLimite;
    private Date dateLembrete;
    private Date datarealizacao;
    private String status;
    private String userName;
    private Categoria idCategoria;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Date dataLimite) {
        this.dataLimite = dataLimite;
    }

    public Date getDateLembrete() {
        return dateLembrete;
    }

    public void setDateLembrete(Date dateLembrete) {
        this.dateLembrete = dateLembrete;
    }

    public Date getDatarealizacao() {
        return datarealizacao;
    }

    public void setDatarealizacao(Date datarealizacao) {
        this.datarealizacao = datarealizacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }
}
