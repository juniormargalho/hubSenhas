package com.juniormargalho.projeto2020.hubsenhas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juniormargalho.projeto2020.hubsenhas.R;
import com.juniormargalho.projeto2020.hubsenhas.model.Senha;

import java.util.List;

public class SenhaAdapter extends RecyclerView.Adapter<SenhaAdapter.MyViewHolder> {
    private List<Senha> listaSenhas;
    private Context context;

    public SenhaAdapter(List<Senha> lista, Context context) {
        this.listaSenhas = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_senha_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Senha senha = listaSenhas.get(position);
        holder.titulo.setText(senha.getTitulo());
        holder.login.setText(senha.getLogin());
        holder.obs.setText(senha.getObs());
    }

    @Override
    public int getItemCount() {
        return listaSenhas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, login, obs;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.textSenhaAdapterTitulo);
            login = itemView.findViewById(R.id.textSenhaAdapterLogin);
            obs = itemView.findViewById(R.id.textSenhaAdapterObs);
        }
    }

}
