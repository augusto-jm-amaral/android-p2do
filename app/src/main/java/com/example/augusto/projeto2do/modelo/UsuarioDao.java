package com.example.augusto.projeto2do.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UsuarioDao {

    private DatabaseHelper databaseHelper;

    private SQLiteDatabase db;

    public UsuarioDao(Context context){
            databaseHelper = new DatabaseHelper(context);
    }

    public long inserir(Usuario usuario){


        ContentValues contentValues = new ContentValues();
        contentValues.put(Usuario.NOME, usuario.getNome());
        contentValues.put(Usuario.USERNAME, usuario.getUsername());
        contentValues.put(Usuario.PASSWORD, usuario.getPassword());
        contentValues.put(Usuario.SEXO, usuario.getSexo());

        db = databaseHelper.getWritableDatabase();

        long insert = db.insert(Usuario.TABELA, null, contentValues);

        db.close();
        databaseHelper.close();

        return insert;
    }

    public void atualizar(Usuario usuario){

        ContentValues contentValues = new ContentValues();
        contentValues.put(Usuario.NOME, usuario.getNome());
        contentValues.put(Usuario.USERNAME, usuario.getUsername());
        contentValues.put(Usuario.PASSWORD, usuario.getPassword());
        contentValues.put(Usuario.SEXO, usuario.getSexo());

        db = databaseHelper.getWritableDatabase();

        db.update(Usuario.TABELA, contentValues, Usuario.USERNAME + "='" + usuario.getUsername() + "'", null);
        db.close();
    }

    public void excluir(String username){
        db = databaseHelper.getReadableDatabase();
        db.delete(Usuario.TABELA, Usuario.USERNAME + "='" + username + "'", null);
        db.close();
    }

    public boolean validar(String nome, String senha){

        db = databaseHelper.getReadableDatabase();
        Cursor query = db.query(Usuario.TABELA, null, Usuario.USERNAME + "='" + nome +"'", null, null, null, null, null);

        Log.i("DEBUG", "ANTES DO IF");
        if(query.moveToFirst()){
            Log.i("DEBUG","Dentro do if");
            String password = query.getString(query.getColumnIndex(Usuario.PASSWORD));
            return password.equals(senha);
        }

        return false;
    }

    public Usuario buscaUsuario(String username) {

        db = databaseHelper.getReadableDatabase();
        Cursor query = db.query(Usuario.TABELA, null, Usuario.USERNAME + "='" + username + "'", null, null, null, null, null);

        if(query!=null){
            query.moveToFirst();
            String sexo = query.getString(query.getColumnIndex(Usuario.SEXO));
            String password = query.getString(query.getColumnIndex(Usuario.PASSWORD));
            String nome = query.getString(query.getColumnIndex(Usuario.NOME));

            Usuario usuario = new Usuario();
            usuario.setPassword(password);
            usuario.setSexo(sexo);
            usuario.setNome(nome);
            usuario.setUsername(username);

            return usuario;
        }

        return null;
    }
}
