package com.juniormargalho.projeto2020.hubsenhas.model;

import java.io.Serializable;

public class Senha implements Serializable {
    private long idSenha;
    private String idUsuarioAutenticado;
    private String titulo, login, senha, obs;

    public Senha() {
    }

    public long getIdSenha() {
        return idSenha;
    }

    public void setIdSenha(long idSenha) {
        this.idSenha = idSenha;
    }

    public String getIdUsuarioAutenticado() {
        return idUsuarioAutenticado;
    }

    public void setIdUsuarioAutenticado(String idUsuarioAutenticado) {
        this.idUsuarioAutenticado = idUsuarioAutenticado;
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
