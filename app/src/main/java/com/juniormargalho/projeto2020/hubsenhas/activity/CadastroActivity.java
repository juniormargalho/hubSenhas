package com.juniormargalho.projeto2020.hubsenhas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.juniormargalho.projeto2020.hubsenhas.R;
import com.juniormargalho.projeto2020.hubsenhas.helper.ConfiguracaoFirebase;
import com.juniormargalho.projeto2020.hubsenhas.helper.ConfiguracaoUsuario;

import dmax.dialog.SpotsDialog;

public class CadastroActivity extends AppCompatActivity {
    private EditText editCadastroNome, editCadastroEmail, editCadastroEmailConfirmar, editCadastroSenha, editCadastroSenhaConfirmar;
    private FirebaseAuth autenticacao;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inicializar();
    }

    private void cadastrar(){
        final String nome = editCadastroNome.getText().toString();
        String email = editCadastroEmail.getText().toString();
        String confirmarEmail = editCadastroEmailConfirmar.getText().toString();
        String senha = editCadastroSenha.getText().toString();
        String confirmarSenha = editCadastroSenhaConfirmar.getText().toString();

        if( !nome.isEmpty()){
            if( !email.isEmpty()){
                if( !confirmarEmail.isEmpty()){
                    if( email.equals(confirmarEmail)){
                        if( !senha.isEmpty()){
                            if( !confirmarSenha.isEmpty()){
                                if( senha.equals(confirmarSenha)){

                                    dialog = new SpotsDialog.Builder().setContext(CadastroActivity.this).setMessage("Cadastrando novo usuário!").setCancelable(false).build();
                                    dialog.show();

                                    autenticacao.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()){
                                                ConfiguracaoUsuario.setNomeProfile(nome);
                                                Toast.makeText(CadastroActivity.this, "Bem vindo(a), " + nome, Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                                finish();
                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            }else {
                                                String erroExcecao = "";
                                                try{
                                                    throw task.getException();
                                                }catch (FirebaseAuthWeakPasswordException e){
                                                    erroExcecao = "Digite uma senha mais forte!";
                                                }catch (FirebaseAuthInvalidCredentialsException e){
                                                    erroExcecao = "Por favor, digite um e-mail válido!";
                                                }catch (FirebaseAuthUserCollisionException e){
                                                    erroExcecao = "Esta conta já foi cadastrada!";
                                                } catch (Exception e) {
                                                    erroExcecao = "Erro ao cadastrar usuário: "  + e.getMessage();
                                                    e.printStackTrace();
                                                }
                                                Toast.makeText(CadastroActivity.this, erroExcecao , Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                }else {
                                    Toast.makeText(CadastroActivity.this, "Senha não confere!", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(CadastroActivity.this, "Confirme sua senha, por favor!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(CadastroActivity.this, "Preencha sua senha, por favor!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(CadastroActivity.this, "E-mail não confere!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(CadastroActivity.this, "Confirme seu e-mail, por favor!", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(CadastroActivity.this, "Preencha seu e-mail, por favor!", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(CadastroActivity.this, "Preencha seu nome, por favor!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuCadastrar :
                cadastrar();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastrar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void inicializar(){
        editCadastroNome = findViewById(R.id.editCadastroNome);
        editCadastroEmail = findViewById(R.id.editCadastroEmail);
        editCadastroEmailConfirmar = findViewById(R.id.editCadastroEmailConfirmar);
        editCadastroSenha = findViewById(R.id.editCadastroSenha);
        editCadastroSenhaConfirmar = findViewById(R.id.editCadastroSenhaConfirmar);

        autenticacao = ConfiguracaoFirebase.getReferenciaAutenticacao();
    }
}