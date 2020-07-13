package com.juniormargalho.projeto2020.hubsenhas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.juniormargalho.projeto2020.hubsenhas.R;

public class NovaSenhaActivity extends AppCompatActivity {
    private EditText editNovaSenhaTitulo, editNovaSenhaLogin, editNovaSenhaSenha, editNovaSenhaObs;
    private RadioButton radioNovaSenha1, radioNovaSenha2, radioNovaSenha3, radioNovaSenha4, radioNovaSenha5;
    private Button buttonNovaSenhaGerar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_senha);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicializar();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_nova_senha, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void inicializar(){
        editNovaSenhaTitulo = findViewById(R.id.editNovaSenhaTitulo);
        editNovaSenhaLogin = findViewById(R.id.editNovaSenhaLogin);
        editNovaSenhaSenha = findViewById(R.id.editNovaSenhaSenha);
        editNovaSenhaObs = findViewById(R.id.editNovaSenhaObs);
        radioNovaSenha1 = findViewById(R.id.radioNovaSenha1);
        radioNovaSenha2 = findViewById(R.id.radioNovaSenha2);
        radioNovaSenha3 = findViewById(R.id.radioNovaSenha3);
        radioNovaSenha4 = findViewById(R.id.radioNovaSenha4);
        radioNovaSenha5 = findViewById(R.id.radioNovaSenha5);
        buttonNovaSenhaGerar = findViewById(R.id.buttonNovaSenhaGerar);
    }
}