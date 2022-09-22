package com.vyttor.cadastroaluno.services.apiusuarios;

import com.vyttor.cadastroaluno.entities.Usuario;
import com.vyttor.cadastroaluno.exceptions.ApiUserException;
import com.vyttor.cadastroaluno.services.apiusuarios.mappers.MapperUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarUsuariosApiUser {

    private final ApiUsuario apiUsuario;
    private final MapperUsuario mapperUsuario;

    public List<Usuario> listar(){
        try {
            return apiUsuario
                    .getUsuarios()
                    .execute()
                    .body()
                    .stream()
                    .map( mapperUsuario::toModel )
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new ApiUserException(e.getMessage());
        }

    }
}
