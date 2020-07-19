package com.juniormargalho.projeto2020.hubsenhas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.juniormargalho.projeto2020.hubsenhas.model.Senha;

import java.util.ArrayList;
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
    public boolean editar(Senha senha, String idUsuarioAutenticado) {
        ContentValues cv = new ContentValues();
        cv.put("idUsuarioAutenticado", idUsuarioAutenticado);
        cv.put("titulo", senha.getTitulo());
        cv.put("login", senha.getLogin());
        cv.put("senha", senha.getSenha());
        cv.put("obs", senha.getObs());

        try{
            String[] args = {String.valueOf(senha.getIdSenha()), idUsuarioAutenticado};
            write.update(SqLite.TABELA_SENHAS, cv, "idSenha=? AND idUsuarioAutenticado=?", args);
            Log.i("INFO_DB", "Senha editada com sucesso!");
        }catch (Exception e){
            Log.i("INFO_DB", "Erro ao editar senha!" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean excluir(Senha senha, String idUsuarioAutenticado) {

        try{
            String[] args = {String.valueOf(senha.getIdSenha()), idUsuarioAutenticado};
            write.delete(SqLite.TABELA_SENHAS, "idSenha=? AND idUsuarioAutenticado=?", args);
            Log.i("INFO_DB", "Senha excluida com sucesso!");
        }catch (Exception e){
            Log.i("INFO_DB", "Erro ao excluir senha!" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Senha> listar(String idUsuarioAutenticado) {
        List<Senha> senhas = new ArrayList<>();

        String sql = "SELECT * FROM " + SqLite.TABELA_SENHAS + " WHERE idUsuarioAutenticado = '" + idUsuarioAutenticado +"';";
        Cursor cursor = read.rawQuery(sql, null);

        while (cursor.moveToNext()){
            Senha senhalistagem = new Senha();

            Long id = cursor.getLong(cursor.getColumnIndex("idSenha"));
            String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            String login = cursor.getString(cursor.getColumnIndex("login"));
            String senha = cursor.getString(cursor.getColumnIndex("senha"));
            String obs = cursor.getString(cursor.getColumnIndex("obs"));

            senhalistagem.setIdSenha(id);
            senhalistagem.setIdUsuarioAutenticado(idUsuarioAutenticado);
            senhalistagem.setTitulo(titulo);
            senhalistagem.setLogin(login);
            senhalistagem.setSenha(senha);
            senhalistagem.setObs(obs);

            senhas.add(senhalistagem);

        }

        return senhas;
    }
}
