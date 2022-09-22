package com.vyttor.cadastrousuarios.controllers;

import com.vyttor.cadastrousuarios.entities.Usuario;
import com.vyttor.cadastrousuarios.services.CadastroServiceDefault;
import com.vyttor.cadastrousuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsuarioController {

    @Autowired
    private CadastroServiceDefault cadastroServiceDefault;

    // http:// localhost:8080 /usuario/salvar - VERBO/METHOD = intenção
    //POST - Criar alguma
    //GET - Pegar alguma coisa
    //PUT / PATCH - Atualizar Alguma coisa

    @RequestMapping( value = "/usuario/salvar", method = RequestMethod.POST)
    public Usuario salvar( @RequestBody Usuario usuario ) {
        return cadastroServiceDefault.salvar( usuario );
    }

    @RequestMapping(value = "/usuario/listar", method = RequestMethod.GET)
    public List<Usuario> listar() {
        return cadastroServiceDefault.listar();
    }
}
