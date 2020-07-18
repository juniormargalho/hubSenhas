package com.juniormargalho.projeto2020.hubsenhas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.juniormargalho.projeto2020.hubsenhas.model.Senha;

import java.util.List;

public class SenhaDAO implements ISenhaDAO {
    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public SenhaDAO(Context context) {
        SqLite sqLite = new SqLite(context);
        write = sqLite.getWritableDatabase();
        read = sqLite.getReadableDatabase();
    }

    @Override
    public boolean salvar(Senha senha, String idUsuarioAutenticado) {
        ContentValues cv = new ContentValues();
        cv.put("idUsuarioAutenticado", idUsuarioAutenticado);
        cv.put("titulo", senha.getTitulo());
        cv.put("login", senha.getLogin());
        cv.put("senha", senha.getSenha());
        cv.put("obs", senha.getObs());

        try{
            write.insert(SqLite.TABELA_SENHAS, null, cv);
            Log.i("INFO_DB", "Senha salva com sucesso!");
        }catch (Exception e){
            Log.i("INFO_DB", "Erro ao salvar senha" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Senha senha) {
        return false;
    }

    @Override
    public boolean deletar(Senha senha) {
        return false;
    }

    @Override
    public List<Senha> listar() {
        return null;
    }
}
