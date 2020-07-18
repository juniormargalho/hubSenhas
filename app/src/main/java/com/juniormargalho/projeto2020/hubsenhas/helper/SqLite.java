package com.juniormargalho.projeto2020.hubsenhas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqLite extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String NAME_DB = "DB_SENHAS";
    public static String TABELA_SENHAS = "senhas";

    public SqLite(Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_SENHAS +
                " (idSenha INTEGER PRIMARY KEY AUTOINCREMENT, idUsuarioAutenticado TEXT NOT NULL, titulo TEXT NOT NULL, login TEXT NOT NULL, senha TEXT NOT NULL, obs TEXT); ";

        try {
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO_DB", "Sucesso ao criar a tabela!");
        }catch (Exception e){
            Log.i("INFO_DB", "Erro ao criar tabela: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
