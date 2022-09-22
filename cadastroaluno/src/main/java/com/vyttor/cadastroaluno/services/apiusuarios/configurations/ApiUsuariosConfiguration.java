package com.vyttor.cadastroaluno.services.apiusuarios.configurations;

import com.vyttor.cadastroaluno.services.apiusuarios.ApiUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class ApiUsuariosConfiguration {

    @Bean
    public ApiUsuario apiUsuarios(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiUsuario service = retrofit.create(ApiUsuario.class);

        return service;
    }
}
