package com.juniormargalho.projeto2020.hubsenhas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.juniormargalho.projeto2020.hubsenhas.R;
import com.juniormargalho.projeto2020.hubsenhas.helper.ConfiguracaoFirebase;
import com.juniormargalho.projeto2020.hubsenhas.helper.ConfiguracaoUsuario;

public class LoginActivity extends AppCompatActivity {
    private EditText editLoginEmail, editLoginSenha;
    private Button buttonLoginEntrar;
    private TextView textLoginCadastrese;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializar();

        verificaUsuarioLogado();

        buttonLoginEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editLoginEmail.getText().toString();
                String senha = editLoginSenha.getText().toString();

                if( !email.isEmpty()){
                    if( !senha.isEmpty()){

                        autenticacao.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    abrirMainActivity();
                                }else {
                                    Toast.makeText(LoginActivity.this, "Erro ao fazer login: " + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }else {
                        Toast.makeText(LoginActivity.this, "Preencha sua senha, por favor!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Preencha seu e-mail, por favor!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void abrirMainActivity(){
        String usuarioAtual = ConfiguracaoUsuario.getUsuarioAtual().getDisplayName();
        Toast.makeText(LoginActivity.this, "Bem vindo(a), " + usuarioAtual, Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private void verificaUsuarioLogado(){
        FirebaseUser usuarioAtual = ConfiguracaoUsuario.getUsuarioAtual();

        if(usuarioAtual != null){
            abrirMainActivity();
        }
    }

    public void cadastrese(View view){
        Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(i);
    }

    private void inicializar(){
        editLoginEmail = findViewById(R.id.editLoginEmail);
        editLoginSenha = findViewById(R.id.editLoginSenha);
        buttonLoginEntrar = findViewById(R.id.buttonLoginEntrar);
        textLoginCadastrese = findViewById(R.id.textLoginCadastrese);

        autenticacao = ConfiguracaoFirebase.getReferenciaAutenticacao();
    }
}