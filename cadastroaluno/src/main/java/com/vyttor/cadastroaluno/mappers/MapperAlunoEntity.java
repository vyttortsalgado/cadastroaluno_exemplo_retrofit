package com.vyttor.cadastroaluno.mappers;

import com.vyttor.cadastroaluno.dtos.requests.AlunoRequest;
import com.vyttor.cadastroaluno.dtos.responses.AlunoResponse;
import com.vyttor.cadastroaluno.entities.AlunoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MapperAlunoEntity {

    AlunoEntity toEntity(AlunoRequest alunoRequest);
    AlunoResponse toResponse(AlunoEntity alunoEntity);
    AlunoEntity update(AlunoRequest alunoRequest, @MappingTarget AlunoEntity alunoEntity);
}
