package com.juniormargalho.projeto2020.hubsenhas.model;

import com.google.firebase.database.DatabaseReference;
import com.juniormargalho.projeto2020.hubsenhas.helper.ConfiguracaoFirebase;

public class Usuario {
    private String idUsuario;
    private String nome, email;

    public Usuario() {
    }

    public void salvar(){
        DatabaseReference databaseReference = ConfiguracaoFirebase.getReferenciaDatabase();
        DatabaseReference usuarioRef = databaseReference.child("usuarios").child(getIdUsuario());
        usuarioRef.setValue(this);
    }

    public void remover(){
        DatabaseReference databaseReference = ConfiguracaoFirebase.getReferenciaDatabase();
        DatabaseReference usuarioRef = databaseReference.child("usuarios").child(getIdUsuario());
        usuarioRef.removeValue();
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
