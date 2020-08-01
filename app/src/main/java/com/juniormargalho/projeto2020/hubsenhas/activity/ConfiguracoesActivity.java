package com.juniormargalho.projeto2020.hubsenhas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.juniormargalho.projeto2020.hubsenhas.R;
import com.juniormargalho.projeto2020.hubsenhas.helper.Base64Custom;
import com.juniormargalho.projeto2020.hubsenhas.helper.ConfiguracaoUsuario;
import com.juniormargalho.projeto2020.hubsenhas.helper.SenhaDAO;
import com.juniormargalho.projeto2020.hubsenhas.model.Senha;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class ConfiguracoesActivity extends AppCompatActivity {
    private Button buttonConfiguracoesAlterarNome, buttonConfiguracoesAlterarSenha, buttonConfiguracoesBackup;
    private FirebaseUser usuarioAutenticado;
    private List<Senha> listaSenhas = new ArrayList<>();
    private AlertDialog dialog;

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
        editAlterarNome.setPadding(20,20,20,20);

        LinearLayout layoutAlterarNome = new LinearLayout(this);
        layoutAlterarNome.setOrientation(LinearLayout.VERTICAL);
        layoutAlterarNome.addView(editAlterarNome);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Digite o novo nome para saudação de boas vindas!");
        builder.setCancelable(false);
        builder.setView( layoutAlterarNome );

        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String nome = editAlterarNome.getText().toString();

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
        final EditText editAlterarSenhaSenhaAtual = new EditText(this);
        editAlterarSenhaSenhaAtual.setHint("Senha atual");
        editAlterarSenhaSenhaAtual.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editAlterarSenhaSenhaAtual.setPadding(20,20,20,20);

        final EditText editAlterarSenhaNovaSenha = new EditText(this);
        editAlterarSenhaNovaSenha.setHint("Digite a nova senha");
        editAlterarSenhaNovaSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editAlterarSenhaNovaSenha.setPadding(20,20,20,20);

        final EditText editAlterarSenhaConfirmarSenha = new EditText(this);
        editAlterarSenhaConfirmarSenha.setHint("Confirme a nova senha");
        editAlterarSenhaConfirmarSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editAlterarSenhaConfirmarSenha.setPadding(20,20,20,20);

        LinearLayout layoutNovaSenha = new LinearLayout(this);
        layoutNovaSenha.setOrientation(LinearLayout.VERTICAL);
        layoutNovaSenha.addView(editAlterarSenhaSenhaAtual);
        layoutNovaSenha.addView(editAlterarSenhaNovaSenha);
        layoutNovaSenha.addView(editAlterarSenhaConfirmarSenha);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Alteração de senha");
        builder.setCancelable(false);
        builder.setView( layoutNovaSenha );

        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String senhaAtual = editAlterarSenhaSenhaAtual.getText().toString();
                final String novaSenha = editAlterarSenhaNovaSenha.getText().toString();
                String confirmarSenha = editAlterarSenhaConfirmarSenha.getText().toString();

                if( !senhaAtual.isEmpty() ){
                    if( !novaSenha.isEmpty() ){
                        if( !confirmarSenha.isEmpty() ){
                            if( novaSenha.equals(confirmarSenha) ){
                                AuthCredential credential = EmailAuthProvider.getCredential(usuarioAutenticado.getEmail(), senhaAtual);

                                usuarioAutenticado.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(task.isSuccessful()){
                                            usuarioAutenticado.updatePassword(novaSenha).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(ConfiguracoesActivity.this, "Senha alterada com sucesso!", Toast.LENGTH_SHORT).show();
                                                    }else {
                                                        String erroExcecao = "";
                                                        try{
                                                            throw task.getException();
                                                        }catch (FirebaseAuthWeakPasswordException e) {
                                                            erroExcecao = "Digite uma senha mais forte!";
                                                        }catch (Exception e) {
                                                            erroExcecao = "Ocorreu um erro na alteração de senha: "  + e.getMessage();
                                                            e.printStackTrace();
                                                        }
                                                        Toast.makeText(ConfiguracoesActivity.this, erroExcecao, Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }else {
                                            Toast.makeText(ConfiguracoesActivity.this, "Verifique a senha digitada!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }else {
                                Toast.makeText(ConfiguracoesActivity.this, "A confirmação de senha não confere com a nova senha!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(ConfiguracoesActivity.this, "Confirmação de senha não preenchida!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(ConfiguracoesActivity.this, "Nova senha não preenchida!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ConfiguracoesActivity.this, "Senha atual não preenchida!", Toast.LENGTH_SHORT).show();
                }
            }
        }).setNegativeButton("Cancelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void backup(){
        final EditText editBackupEmail = new EditText(this);
        editBackupEmail.setHint("Digite seu e-mail!");
        editBackupEmail.setPadding(20,20,20,20);

        final EditText editBackupSenha = new EditText(this);
        editBackupSenha.setHint("Digite sua senha!");
        editBackupSenha.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editBackupSenha.setPadding(20,20,20,20);

        LinearLayout layoutBackup = new LinearLayout(this);
        layoutBackup.setOrientation(LinearLayout.VERTICAL);
        layoutBackup.addView(editBackupEmail);
        layoutBackup.addView(editBackupSenha);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Autenticação");
        builder.setCancelable(false);
        builder.setView( layoutBackup );

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String email = editBackupEmail.getText().toString();
                String senha = editBackupSenha.getText().toString();

                if( !email.isEmpty()){
                    if( !senha.isEmpty()){
                        AuthCredential credential = EmailAuthProvider.getCredential(email, senha);
                        usuarioAutenticado.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    dialog = new SpotsDialog.Builder().setContext(ConfiguracoesActivity.this).setMessage("Criando e-mail de backup!").setCancelable(false).build();
                                    dialog.show();
                                    enviarEmailBackup();
                                    dialog.dismiss();
                                }else {
                                    Toast.makeText(ConfiguracoesActivity.this, "E-mail ou senha inválidos, verifique, por favor!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(ConfiguracoesActivity.this, "Digite sua senha, por favor!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ConfiguracoesActivity.this, "Digite seu e-mail, por favor!", Toast.LENGTH_SHORT).show();
                }
            }
        }).setNegativeButton("Cancelar", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void enviarEmailBackup(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("vnd.android.cursor.dir/email");
        i.putExtra(Intent.EXTRA_SUBJECT, "Backup de senhas");
        i.putExtra(Intent.EXTRA_TEXT, gerarCorpoEmail(usuarioAutenticado.getUid()));
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{ConfiguracaoUsuario.getUsuarioAutenticado().getEmail()});
        startActivity(Intent.createChooser(i, "Enviando e-mail!"));
    }

    private String gerarCorpoEmail(String idUsuarioAutenticado){
        listaSenhas.clear();
        SenhaDAO senhaDAO = new SenhaDAO(getApplicationContext());
        listaSenhas = senhaDAO.listar(idUsuarioAutenticado);
        StringBuilder senhas = new StringBuilder();

        for(int i=0; i < listaSenhas.size(); i++){
            senhas.append("titulo: ").append(listaSenhas.get(i).getTitulo() + "\n");
            senhas.append("login: ").append(listaSenhas.get(i).getLogin() + "\n");
            senhas.append("senha: ").append(Base64Custom.Decode64(listaSenhas.get(i).getSenha()) + "\n");
            senhas.append("obs: ").append(listaSenhas.get(i).getObs() + "\n\n");
        }
        return senhas.toString();
    }

    private void inicializar(){
        buttonConfiguracoesAlterarNome = findViewById(R.id.buttonConfiguracoesAlterarNome);
        buttonConfiguracoesAlterarSenha = findViewById(R.id.buttonConfiguracoesAlterarSenha);
        buttonConfiguracoesBackup = findViewById(R.id.buttonConfiguracoesBackup);
        usuarioAutenticado = ConfiguracaoUsuario.getUsuarioAutenticado();
    }
}