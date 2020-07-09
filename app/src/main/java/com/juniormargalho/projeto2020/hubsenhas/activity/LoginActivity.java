package com.juniormargalho.projeto2020.hubsenhas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.juniormargalho.projeto2020.hubsenhas.R;

public class LoginActivity extends AppCompatActivity {
    private EditText editLoginEmail, editLoginSenha;
    private Button buttonLoginEntrar;
    private TextView textLoginCadastrese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        inicializar();
    }

    public void cadastrese(View view){
        Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(i);
        finish();
    }

    private void inicializar(){
        editLoginEmail = findViewById(R.id.editLoginEmail);
        editLoginSenha = findViewById(R.id.editLoginSenha);
        buttonLoginEntrar = findViewById(R.id.buttonLoginEntrar);
        textLoginCadastrese = findViewById(R.id.textLoginCadastrese);
    }
}