package com.juniormargalho.projeto2020.hubsenhas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.juniormargalho.projeto2020.hubsenhas.R;

public class CadastroActivity extends AppCompatActivity {
    private EditText editCadastroNome, editCadastroEmail, editCadastroEmailConfirmar, editCadastroSenha, editCadastroSenhaConfirmar;
    private Button buttonCadastroCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().hide();

        inicializar();
    }

    private void inicializar(){
        editCadastroNome = findViewById(R.id.editCadastroNome);
        editCadastroEmail = findViewById(R.id.editCadastroEmail);
        editCadastroEmailConfirmar = findViewById(R.id.editCadastroEmailConfirmar);
        editCadastroSenha = findViewById(R.id.editCadastroSenha);
        editCadastroSenhaConfirmar = findViewById(R.id.editCadastroSenhaConfirmar);
    }
}