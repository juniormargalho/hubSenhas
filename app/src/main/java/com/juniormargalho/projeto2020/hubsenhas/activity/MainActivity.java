package com.juniormargalho.projeto2020.hubsenhas.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.juniormargalho.projeto2020.hubsenhas.R;
import com.juniormargalho.projeto2020.hubsenhas.adapter.SenhaAdapter;
import com.juniormargalho.projeto2020.hubsenhas.helper.ConfiguracaoFirebase;
import com.juniormargalho.projeto2020.hubsenhas.helper.ConfiguracaoUsuario;
import com.juniormargalho.projeto2020.hubsenhas.helper.RecyclerItemClickListener;
import com.juniormargalho.projeto2020.hubsenhas.helper.SenhaDAO;
import com.juniormargalho.projeto2020.hubsenhas.model.Senha;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth autenticacao;
    private MaterialSearchView searchView;
    private RecyclerView recyclerViewSenhas;
    private SenhaAdapter senhaAdapter;
    private List<Senha> listaSenhas = new ArrayList<>();
    private String idUsuarioAutenticado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("HUB SENHAS");
        setSupportActionBar(toolbar);

        inicializar();

        recyclerViewSenhas.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerViewSenhas,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(final View view, final int position) {
                        Senha senhaSelecionada = listaSenhas.get(position);
                        view.setBackgroundResource(R.color.azulClaro);

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle(senhaSelecionada.getTitulo());
                        builder.setMessage("Login: " + senhaSelecionada.getLogin() +
                                "\nSenha: " + senhaSelecionada.getSenha() +
                                "\nObservação: " + senhaSelecionada.getObs());

                        builder.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Senha senhaSelecionada = listaSenhas.get(position);
                                Intent i = new Intent(MainActivity.this, NovaSenhaActivity.class);
                                i.putExtra("senhaSelecionada", senhaSelecionada);
                                startActivity(i);

                                view.setBackgroundResource(R.color.colorPrimaryDark);
                            }
                        });

                        builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                view.setBackgroundResource(R.color.colorPrimaryDark);
                            }
                        });
                        builder.setCancelable(false);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                    @Override
                    public void onLongItemClick(final View view, final int position) {
                        final Senha senhaSelecionada = listaSenhas.get(position);
                        view.setBackgroundResource(R.color.azulClaro);

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Excluir senha selecionada?");
                        builder.setMessage("Titulo: " + senhaSelecionada.getTitulo() +
                                "\nLogin: " + senhaSelecionada.getLogin() +
                                "\nSenha: " + senhaSelecionada.getSenha() +
                                "\nObservação: " + senhaSelecionada.getObs());

                        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Senha senhaSelecionada = listaSenhas.get(position);
                                SenhaDAO senhaDAO = new SenhaDAO(getApplicationContext());
                                if(senhaDAO.excluir(senhaSelecionada, idUsuarioAutenticado)){
                                    carregarListaSenhas();
                                    Toast.makeText(MainActivity.this, "Senha excluída com sucesso!", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(MainActivity.this, "Erro ao tentar excluir!", Toast.LENGTH_SHORT).show();
                                }
                                view.setBackgroundResource(R.color.colorPrimaryDark);
                            }
                        });

                        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                view.setBackgroundResource(R.color.colorPrimaryDark);
                            }
                        });
                        builder.setCancelable(false);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    }
                }));

        FloatingActionButton fab = findViewById(R.id.fabNovo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NovaSenhaActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        carregarListaSenhas();
        super.onStart();
    }

    private void carregarListaSenhas(){
        listaSenhas.clear();
        SenhaDAO senhaDAO = new SenhaDAO(getApplicationContext());
        listaSenhas = senhaDAO.listar(idUsuarioAutenticado);

        recyclerViewSenhas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSenhas.setHasFixedSize(true);
        senhaAdapter = new SenhaAdapter(listaSenhas, this);
        recyclerViewSenhas.setAdapter(senhaAdapter);
    }

    private void deslogarUsuario(){
        try {
            autenticacao.signOut();
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuConfiguracoes :

                break;
            case R.id.menuSair :
                deslogarUsuario();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.menuPesquisa);
        searchView.setMenuItem(item);

        return super.onCreateOptionsMenu(menu);
    }

    private void inicializar(){
        autenticacao = ConfiguracaoFirebase.getReferenciaAutenticacao();
        searchView = findViewById(R.id.materialSearchView);
        recyclerViewSenhas = findViewById(R.id.recyclerViewSenhas);
        idUsuarioAutenticado = ConfiguracaoUsuario.getIdUsuarioAutenticado();
    }
}