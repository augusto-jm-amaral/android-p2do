package com.example.augusto.projeto2do.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by augusto on 24/11/15.
 */
public class TarefaDao {

    private DatabaseHelper databaseHelper;

    private SQLiteDatabase db;
    private CategoriaDao categoriaDao = null;

    public TarefaDao(Context context){
        databaseHelper = new DatabaseHelper(context);
        categoriaDao = new CategoriaDao(context);
    }

    public long inserir(Tarefa tarefa){

        ContentValues contentValues = new ContentValues();
        contentValues.put(Tarefa.DESCRICAO, tarefa.getDescricao());
        contentValues.put(Tarefa.STATUS, tarefa.getStatus());
        contentValues.put(Tarefa.DATA_LIMITE, tarefa.getDataLimite().getTime());
        contentValues.put(Tarefa.DATA_LEMBRETE, tarefa.getDateLembrete().getTime());
        contentValues.put(Tarefa.DATA_REALIZACAO, tarefa.getDatarealizacao().getTime());
        contentValues.put(Tarefa.ID_CATEGORIA, tarefa.getIdCategoria().getId());
        contentValues.put(Tarefa.USERNAME, tarefa.getUserName());

        db = databaseHelper.getWritableDatabase();

        long insert = db.insert(Tarefa.TABELA, null, contentValues);

        db.close();
        databaseHelper.close();

        return insert;
    }

    public void atualizar(Tarefa tarefa){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tarefa.ID, tarefa.getId());
        contentValues.put(Tarefa.DESCRICAO, tarefa.getDescricao());
        contentValues.put(Tarefa.STATUS, tarefa.getStatus());
        contentValues.put(Tarefa.DATA_LIMITE, tarefa.getDataLimite().getTime());
        contentValues.put(Tarefa.DATA_LEMBRETE, tarefa.getDateLembrete().getTime());
        contentValues.put(Tarefa.DATA_REALIZACAO, tarefa.getDatarealizacao().getTime());
        contentValues.put(Tarefa.ID_CATEGORIA, tarefa.getIdCategoria().getId());
        contentValues.put(Tarefa.USERNAME, tarefa.getUserName());

        db = databaseHelper.getWritableDatabase();

        db.update(Tarefa.TABELA, contentValues, Tarefa.ID + "=" + tarefa.getId(), null);

        db.close();
        databaseHelper.close();
    }

    public void excluir(int id){
        db = databaseHelper.getReadableDatabase();
        db.delete(Tarefa.TABELA, Tarefa.ID + "=" + id, null);
        db.close();
        databaseHelper.close();
    }

    public Tarefa buscaTarefa(int id){
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(Tarefa.TABELA, null, Tarefa.ID + "=" + id, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();

            long dataLembrete = cursor.getLong(cursor.getColumnIndex(Tarefa.DATA_LEMBRETE));
            long dataLimite = cursor.getLong(cursor.getColumnIndex(Tarefa.DATA_LIMITE));
            long dataRealizacao = cursor.getLong(cursor.getColumnIndex(Tarefa.DATA_REALIZACAO));

            String descricao = cursor.getString(cursor.getColumnIndex(Tarefa.DESCRICAO));
            String username = cursor.getString(cursor.getColumnIndex(Tarefa.USERNAME));
            String status = cursor.getString(cursor.getColumnIndex(Tarefa.STATUS));

            int idCategoria = cursor.getInt(cursor.getColumnIndex(Tarefa.ID_CATEGORIA));

            Tarefa tarefa = new Tarefa();
            tarefa.setId(id);

            tarefa.setDataLimite(new Date(dataLimite));
            tarefa.setDatarealizacao(new Date(dataRealizacao));
            tarefa.setDateLembrete(new Date(dataRealizacao));

            tarefa.setDescricao(descricao);
            tarefa.setStatus(status);
            tarefa.setIdCategoria(categoriaDao.buscarCategoria(idCategoria));
            tarefa.setUserName(username);


            cursor.close();
            db.close();
            databaseHelper.close();

            return tarefa;
        }


        cursor.close();
        db.close();
        databaseHelper.close();

        return null;
    }

    public List<Tarefa> listarTodos(){
        List<Tarefa> tarefas = new ArrayList<>();
        db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(Tarefa.TABELA, null, null, null, null, null, Tarefa.DATA_LIMITE);

        if(cursor.moveToFirst()){
            do{
                Tarefa tarefa = new Tarefa();
                tarefa.setId(cursor.getInt(cursor.getColumnIndex(Tarefa.ID)));
                tarefa.setIdCategoria(categoriaDao.buscarCategoria(cursor.getInt(cursor.getColumnIndex(Tarefa.ID_CATEGORIA))));
                tarefa.setUserName(cursor.getString(cursor.getColumnIndex(Tarefa.USERNAME)));
                tarefa.setStatus(cursor.getString(cursor.getColumnIndex(Tarefa.STATUS)));
                tarefa.setDescricao(cursor.getString(cursor.getColumnIndex(Tarefa.DESCRICAO)));
                tarefa.setDateLembrete(new Date(cursor.getLong(cursor.getColumnIndex(Tarefa.DATA_LEMBRETE))));
                tarefa.setDataLimite(new Date(cursor.getLong(cursor.getColumnIndex(Tarefa.DATA_LIMITE))));
                tarefa.setDatarealizacao(new Date(cursor.getLong(cursor.getColumnIndex(Tarefa.DATA_REALIZACAO))));
            }while(cursor.moveToNext());
        }


        cursor.close();
        db.close();
        databaseHelper.close();

        return tarefas;
    }



}
