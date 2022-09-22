package com.vyttor.cadastrousuarios.services;

import com.vyttor.cadastrousuarios.entities.Usuario;

import java.util.List;

public interface CadastroServiceDefault {
    Usuario salvar(Usuario usuario);

    List<Usuario> listar();
}
