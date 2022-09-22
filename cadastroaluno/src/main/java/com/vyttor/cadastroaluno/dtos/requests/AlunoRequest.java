package com.vyttor.cadastroaluno.dtos.requests;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AlunoRequest {
    private String nome;
    private String materia;
    private LocalDate dataNascimento;
}
