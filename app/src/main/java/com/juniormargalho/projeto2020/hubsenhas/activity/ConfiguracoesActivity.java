package com.juniormargalho.projeto2020.hubsenhas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.juniormargalho.projeto2020.hubsenhas.R;

public class ConfiguracoesActivity extends AppCompatActivity {
    private Button buttonConfiguracoesAlterarNome, buttonConfiguracoesAlterarSenha, buttonConfiguracoesBackup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicializar();

        buttonConfiguracoesAlterarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarNome();
            }
        });

        buttonConfiguracoesAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alterarSenha();
            }
        });

        buttonConfiguracoesBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backup();
            }
        });
    }

    private void alterarNome(){
    }

    private void alterarSenha(){
    }

    private void backup(){
    }

    private void inicializar(){
        buttonConfiguracoesAlterarNome = findViewById(R.id.buttonConfiguracoesAlterarNome);
        buttonConfiguracoesAlterarSenha = findViewById(R.id.buttonConfiguracoesAlterarSenha);
        buttonConfiguracoesBackup = findViewById(R.id.buttonConfiguracoesBackup);
    }
}