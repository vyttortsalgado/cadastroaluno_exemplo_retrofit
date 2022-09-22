package com.vyttor.cadastroaluno.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Usuario {

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
}
