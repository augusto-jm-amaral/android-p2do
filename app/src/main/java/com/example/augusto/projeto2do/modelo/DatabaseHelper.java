package com.example.augusto.projeto2do.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by augusto on 13/11/15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Categoria.TABELA + "(" + Categoria.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + Categoria.NOME + " TEXT)");
        db.execSQL("CREATE TABLE " + Usuario.TABELA + "(" + Usuario.USERNAME + " TEXT PRIMARY KEY, " + Usuario.PASSWORD + " TEXT, " + Usuario.NOME + " TEXT, " + Usuario.SEXO + " TEXT)");
        db.execSQL("CREATE TABLE " + Tarefa.TABELA + "("+Tarefa.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Tarefa.DESCRICAO+" TEXT, "+Tarefa.DATA_LIMITE+" INTEGER, "+ Tarefa.DATA_LEMBRETE +" INTEGER, "+Tarefa.DATA_REALIZACAO+" INTEGER, "+Tarefa.STATUS+" TEXT, "+Tarefa.USERNAME+" TEXT, "+Tarefa.ID_CATEGORIA+" INTEGER, FOREIGN KEY("+Tarefa.USERNAME+") REFERENCES "+ Usuario.TABELA +"("+Usuario.USERNAME+"), FOREIGN KEY("+Tarefa.ID_CATEGORIA+") REFERENCES "+ Categoria.TABELA +"("+Categoria.ID+"))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
