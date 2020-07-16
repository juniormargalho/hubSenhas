package com.juniormargalho.projeto2020.hubsenhas.activity;

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

import com.google.firebase.auth.FirebaseAuth;
import com.juniormargalho.projeto2020.hubsenhas.R;
import com.juniormargalho.projeto2020.hubsenhas.adapter.SenhaAdapter;
import com.juniormargalho.projeto2020.hubsenhas.helper.ConfiguracaoFirebase;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("HUB SENHAS");
        setSupportActionBar(toolbar);

        inicializar();

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
    }
}