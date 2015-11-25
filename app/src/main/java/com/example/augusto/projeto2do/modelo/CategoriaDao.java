package com.example.augusto.projeto2do.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by augusto on 13/11/15.
 */
public class CategoriaDao {

    private DatabaseHelper helper;

    private SQLiteDatabase db;

    public CategoriaDao(Context context){
        helper = new DatabaseHelper(context);
    }

    public long inserir(Categoria categoria){

        ContentValues valores = new ContentValues();
        valores.put(Categoria.NOME, categoria.getNome());

        db = helper.getWritableDatabase();

        long rowid = db.insert(Categoria.TABELA, null, valores);
        db.close();

        return rowid;
    }

    public void atualizar(Categoria categoria){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Categoria.ID, categoria.getId());
        contentValues.put(Categoria.NOME, categoria.getNome());

        db = helper.getWritableDatabase();
        db.update(Categoria.TABELA,contentValues,Categoria.ID + "=" + categoria.getId(), null);
        db.close();
    }

    public void excluirCategoria(int id){
        db = helper.getReadableDatabase();
        db.delete(Categoria.TABELA, Categoria.ID + "=" + id, null);
        db.close();
    }

    public List<Categoria> selecionarTodas(){
        List<Categoria> lista = new ArrayList<Categoria>();

        db = helper.getReadableDatabase();

        Cursor cursor = db.query(Categoria.TABELA, null, null, null, null, null, Categoria.NOME);

        if(cursor.moveToFirst()){
            do{
                Categoria categoria = new Categoria();
                categoria.setId(cursor.getInt(cursor.getColumnIndex(Categoria.ID)));
                categoria.setNome(cursor.getString(cursor.getColumnIndex(Categoria.NOME)));
                lista.add(categoria);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        helper.close();

        return lista;
    }

    public Categoria buscarCategoria(int id){

        db = helper.getReadableDatabase();

        Cursor cursor = db.query(Categoria.TABELA, null, Categoria.ID + "=" + id, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
            String nomeCategoria = cursor.getString(cursor.getColumnIndex(Categoria.NOME));

            Categoria categoria = new Categoria();
            categoria.setId(id);
            categoria.setNome(nomeCategoria);
            return categoria;
        }

        return null;
    }
}
