package com.juniormargalho.projeto2020.hubsenhas.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.juniormargalho.projeto2020.hubsenhas.R;
import com.juniormargalho.projeto2020.hubsenhas.helper.ConfiguracaoUsuario;
import com.juniormargalho.projeto2020.hubsenhas.helper.GeradorSenha;
import com.juniormargalho.projeto2020.hubsenhas.helper.SenhaDAO;
import com.juniormargalho.projeto2020.hubsenhas.model.Senha;

public class NovaSenhaActivity extends AppCompatActivity {
    private EditText editNovaSenhaTitulo, editNovaSenhaLogin, editNovaSenhaSenha, editNovaSenhaObs;
    private RadioButton radioNovaSenha1, radioNovaSenha2, radioNovaSenha3, radioNovaSenha4, radioNovaSenha5;
    private Button buttonNovaSenhaGerar;
    private String idUsuarioAutenticado;
    private Senha senhaEdicao;

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

        preecherCamposEditar();

        buttonNovaSenhaGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioNovaSenha1.isChecked()){
                    editNovaSenhaSenha.setText(GeradorSenha.getSenha("1"));
                }else if (radioNovaSenha2.isChecked()){
                    editNovaSenhaSenha.setText(GeradorSenha.getSenha("2"));
                }else if (radioNovaSenha3.isChecked()) {
                    editNovaSenhaSenha.setText(GeradorSenha.getSenha("3"));
                }else if (radioNovaSenha4.isChecked()) {
                    editNovaSenhaSenha.setText(GeradorSenha.getSenha("4"));
                }else if (radioNovaSenha5.isChecked()) {
                    editNovaSenhaSenha.setText(GeradorSenha.getSenha("5"));
                }
            }
        });
    }

    private void editar(){
        String titulo = editNovaSenhaTitulo.getText().toString();
        String login = editNovaSenhaLogin.getText().toString();
        String senha = editNovaSenhaSenha.getText().toString();
        String obs = editNovaSenhaObs.getText().toString();

        if( !titulo.isEmpty() ){
            if( !login.isEmpty() ){
                if( !senha.isEmpty() ){

                    Senha novaSenha = new Senha();
                    novaSenha.setIdSenha(senhaEdicao.getIdSenha());
                    novaSenha.setTitulo(titulo);
                    novaSenha.setLogin(login);
                    novaSenha.setSenha(senha);
                    novaSenha.setObs(obs);
                    SenhaDAO senhaDAO = new SenhaDAO(getApplicationContext());

                    if(senhaDAO.editar(novaSenha, idUsuarioAutenticado)){
                        Toast.makeText(NovaSenhaActivity.this, "Senha editada com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(NovaSenhaActivity.this, "Erro ao tentar editar!", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(NovaSenhaActivity.this, "Preencha ou gere a senha, por favor!", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(NovaSenhaActivity.this, "Preencha o login, por favor!", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(NovaSenhaActivity.this, "Preencha o titulo, por favor!", Toast.LENGTH_SHORT).show();
        }
    }

    private void salvar(){
        String titulo = editNovaSenhaTitulo.getText().toString();
        String login = editNovaSenhaLogin.getText().toString();
        String senha = editNovaSenhaSenha.getText().toString();
        String obs = editNovaSenhaObs.getText().toString();

        if( !titulo.isEmpty() ){
            if( !login.isEmpty() ){
                if( !senha.isEmpty() ){

                    Senha novaSenha = new Senha();
                    novaSenha.setTitulo(titulo);
                    novaSenha.setLogin(login);
                    novaSenha.setSenha(senha);
                    novaSenha.setObs(obs);
                    SenhaDAO senhaDAO = new SenhaDAO(getApplicationContext());

                    if(senhaDAO.salvar(novaSenha, idUsuarioAutenticado)){
                        Toast.makeText(NovaSenhaActivity.this, "Nova senha adicionada!", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(NovaSenhaActivity.this, "Erro ao tentar salvar!", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(NovaSenhaActivity.this, "Preencha ou gere a senha, por favor!", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(NovaSenhaActivity.this, "Preencha o login, por favor!", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(NovaSenhaActivity.this, "Preencha o titulo, por favor!", Toast.LENGTH_SHORT).show();
        }
    }

    private void preecherCamposEditar(){
        senhaEdicao = (Senha) getIntent().getSerializableExtra("senhaSelecionada");

        if(senhaEdicao != null){
            editNovaSenhaTitulo.setText(senhaEdicao.getTitulo());
            editNovaSenhaLogin.setText(senhaEdicao.getLogin());
            editNovaSenhaSenha.setText(senhaEdicao.getSenha());
            editNovaSenhaObs.setText(senhaEdicao.getObs());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuSalvar :

                if( senhaEdicao != null){
                    editar();
                }else {
                    salvar();
                }
                break;
        }
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

        idUsuarioAutenticado = ConfiguracaoUsuario.getIdUsuarioAutenticado();
    }
}