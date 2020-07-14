package com.juniormargalho.projeto2020.hubsenhas.model;

import com.google.firebase.database.DatabaseReference;
import com.juniormargalho.projeto2020.hubsenhas.helper.ConfiguracaoFirebase;

public class Senha {
    private String idUsuario, idSenha;
    private String titulo, login, senha, obs;

    public Senha() {
        DatabaseReference databaseReference = ConfiguracaoFirebase.getReferenciaDatabase();
        DatabaseReference senhaRef = databaseReference.child("senhas");
        setIdSenha(senhaRef.push().getKey());
    }

    public void salvar(){
        DatabaseReference databaseReference = ConfiguracaoFirebase.getReferenciaDatabase();
        DatabaseReference senhaRef = databaseReference.child("senhas").child(getIdUsuario()).child(getIdSenha());
        senhaRef.setValue(this);
    }

    public void remover(){
        DatabaseReference databaseReference = ConfiguracaoFirebase.getReferenciaDatabase();
        DatabaseReference senhaRef = databaseReference.child("senhas").child(getIdUsuario()).child(getIdSenha());
        senhaRef.removeValue();
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdSenha() {
        return idSenha;
    }

    public void setIdSenha(String idSenha) {
        this.idSenha = idSenha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
