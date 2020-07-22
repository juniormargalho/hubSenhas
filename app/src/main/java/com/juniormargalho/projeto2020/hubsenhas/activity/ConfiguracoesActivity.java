package com.juniormargalho.projeto2020.hubsenhas.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.juniormargalho.projeto2020.hubsenhas.R;
import com.juniormargalho.projeto2020.hubsenhas.helper.ConfiguracaoUsuario;

public class ConfiguracoesActivity extends AppCompatActivity {
    private Button buttonConfiguracoesAlterarNome, buttonConfiguracoesAlterarSenha, buttonConfiguracoesBackup;
    private FirebaseUser usuarioAutenticado;

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
        final EditText editAlterarNome = new EditText(this);
        editAlterarNome.setText(usuarioAutenticado.getDisplayName());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Digite o novo nome para saudação de boas vindas!");
        builder.setCancelable(false);
        builder.setView( editAlterarNome );

        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String nome = String.valueOf(editAlterarNome.getText());

                if( !nome.isEmpty() ){
                    ConfiguracaoUsuario.setNomeProfile(nome);
                    Toast.makeText(ConfiguracoesActivity.this, "Nome de exibição alterado com sucesso!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ConfiguracoesActivity.this, "Preencha com um nome!", Toast.LENGTH_SHORT).show();
                }
            }
        }).setNegativeButton("Cancelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void alterarSenha(){
    }

    private void backup(){
    }

    private void inicializar(){
        buttonConfiguracoesAlterarNome = findViewById(R.id.buttonConfiguracoesAlterarNome);
        buttonConfiguracoesAlterarSenha = findViewById(R.id.buttonConfiguracoesAlterarSenha);
        buttonConfiguracoesBackup = findViewById(R.id.buttonConfiguracoesBackup);
        usuarioAutenticado = ConfiguracaoUsuario.getUsuarioAutenticado();
    }
}