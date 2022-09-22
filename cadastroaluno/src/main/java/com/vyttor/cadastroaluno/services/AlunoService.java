package com.vyttor.cadastroaluno.services;

import com.vyttor.cadastroaluno.dtos.requests.AlunoRequest;
import com.vyttor.cadastroaluno.dtos.responses.AlunoResponse;
import com.vyttor.cadastroaluno.exceptions.AlunoNaoEncontradoException;
import com.vyttor.cadastroaluno.exceptions.AlunoSaveException;
import com.vyttor.cadastroaluno.mappers.MapperAlunoEntity;
import com.vyttor.cadastroaluno.repositories.AlunoRepository;
import com.vyttor.cadastroaluno.services.apiusuarios.ApiUsuario;
import com.vyttor.cadastroaluno.services.apiusuarios.ListarUsuariosApiUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final MapperAlunoEntity mapperAlunoEntity;
    private final AlunoRepository alunoRepository;

    private final ListarUsuariosApiUser listarUsuariosApiUser;

    public AlunoResponse create(AlunoRequest alunoRequest) {
        return Optional
                .ofNullable(alunoRequest)
                .map(mapperAlunoEntity::toEntity)
                .map(alunoRepository::save)
                .map(mapperAlunoEntity::toResponse)
                .orElseThrow(() -> new AlunoSaveException("Não foi possível criar novo Aluno"));
    }

    public AlunoResponse update(Long id, AlunoRequest alunoRequest){
        return alunoRepository
                .findById(id)
                .map(alunoEntity -> mapperAlunoEntity.update(alunoRequest, alunoEntity))
                .map(alunoRepository::save)
                .map(mapperAlunoEntity::toResponse)
                .orElseThrow( () -> new AlunoNaoEncontradoException("Aluno não foi encontrado no banco de dados") );
    }

    public void delete(Long id){
        Optional
                .ofNullable(id)
                        .ifPresent(alunoRepository::deleteById);
    }

    public List<AlunoResponse> findAll(){
        listarUsuariosApiUser
                .listar()
                .forEach(System.out::println);


        return alunoRepository.findAll()
                .stream()
                .map( mapperAlunoEntity::toResponse )
                .collect(Collectors.toList());
    }

    public AlunoResponse findById(Long id){
        return alunoRepository
                .findById(id)
                .map(mapperAlunoEntity::toResponse)
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não foi encontrado no banco de dados"));
    }
}
