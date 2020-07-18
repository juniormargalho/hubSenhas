package com.juniormargalho.projeto2020.hubsenhas.helper;

import com.juniormargalho.projeto2020.hubsenhas.model.Senha;

import java.util.List;

public interface ISenhaDAO {

    public boolean salvar(Senha senha, String idUsuarioLogado);
    public boolean atualizar(Senha senha);
    public boolean deletar(Senha senha);
    public List<Senha> listar();
}
