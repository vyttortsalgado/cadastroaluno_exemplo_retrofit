package com.vyttor.cadastroaluno.dtos.responses;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AlunoResponse {
    private Long id;
    private String nome;
    private String materia;
    private LocalDate dataNascimento;
}
