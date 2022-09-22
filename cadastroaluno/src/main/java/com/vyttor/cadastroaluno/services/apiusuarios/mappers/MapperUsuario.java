package com.vyttor.cadastroaluno.services.apiusuarios.mappers;

import com.vyttor.cadastroaluno.entities.Usuario;
import com.vyttor.cadastroaluno.services.apiusuarios.dtos.responses.UsuarioResponseApiUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperUsuario {

    Usuario toModel(UsuarioResponseApiUser usuarioResponseApiUser);
}
