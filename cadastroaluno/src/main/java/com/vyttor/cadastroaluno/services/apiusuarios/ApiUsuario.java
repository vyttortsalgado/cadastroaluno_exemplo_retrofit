package com.vyttor.cadastroaluno.services.apiusuarios;

import com.vyttor.cadastroaluno.services.apiusuarios.dtos.responses.UsuarioResponseApiUser;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;

import java.util.List;

public interface ApiUsuario {

    @DELETE("/usuario/listar/{iduser}")
    public Call<List<UsuarioResponseApiUser>> getUsuarios();
}
