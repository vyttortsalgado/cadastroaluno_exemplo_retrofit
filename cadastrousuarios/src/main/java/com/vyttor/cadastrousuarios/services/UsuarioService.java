package com.vyttor.cadastrousuarios.services;

import com.vyttor.cadastrousuarios.entities.Usuario;
import com.vyttor.cadastrousuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements CadastroServiceDefault {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Override
    public Usuario salvar(Usuario usuario) {
        //CAST
        return usuarioRepository.save( usuario );
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }
}
