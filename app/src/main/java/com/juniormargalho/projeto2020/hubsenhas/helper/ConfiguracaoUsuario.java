package com.juniormargalho.projeto2020.hubsenhas.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ConfiguracaoUsuario {

    public static String getIdUsuarioAutenticado(){
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getReferenciaAutenticacao();
        return autenticacao.getCurrentUser().getUid();
    }

    public static FirebaseUser getUsuarioAtual(){
        FirebaseAuth usuario = ConfiguracaoFirebase.getReferenciaAutenticacao();
        return usuario.getCurrentUser();
    }

    public static void setNomeProfile(String nome){
        FirebaseUser usuarioAtual = getUsuarioAtual();
        UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(nome).build();
        usuarioAtual.updateProfile(profile);
    }

}
