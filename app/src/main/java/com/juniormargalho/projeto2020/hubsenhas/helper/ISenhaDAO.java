package com.juniormargalho.projeto2020.hubsenhas.helper;

import com.juniormargalho.projeto2020.hubsenhas.model.Senha;

import java.util.List;

public interface ISenhaDAO {

    public boolean salvar(Senha senha, String idUsuarioLogado);
    public boolean editar(Senha senha, String idUsuarioAutenticado);
    public boolean excluir(Senha senha, String idUsuarioAutenticado);
    public List<Senha> listar(String idUsuarioAutenticado);
    public List<Senha> pesquisar(String idUsuarioAutenticado, String pesquisa);
}
