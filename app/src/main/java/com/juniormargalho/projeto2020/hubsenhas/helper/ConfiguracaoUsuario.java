package com.juniormargalho.projeto2020.hubsenhas.helper;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoUsuario {

    public static String getIdUsuarioAutenticado(){
        FirebaseAuth autenticacao = ConfiguracaoFirebase.getReferenciaAutenticacao();
        return autenticacao.getCurrentUser().getUid();
    }
}
