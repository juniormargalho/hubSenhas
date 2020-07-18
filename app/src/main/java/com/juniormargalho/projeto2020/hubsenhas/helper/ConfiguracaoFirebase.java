package com.juniormargalho.projeto2020.hubsenhas.helper;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {
    private static FirebaseAuth referenciaAutenticacao;

    public static FirebaseAuth getReferenciaAutenticacao(){
        if(referenciaAutenticacao == null){
            referenciaAutenticacao = FirebaseAuth.getInstance();
        }
        return referenciaAutenticacao;
    }
}
