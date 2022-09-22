package com.vyttor.cadastroaluno.services;

import com.vyttor.cadastroaluno.dtos.requests.AlunoRequest;
import com.vyttor.cadastroaluno.dtos.responses.AlunoResponse;
import com.vyttor.cadastroaluno.entities.AlunoEntity;
import com.vyttor.cadastroaluno.exceptions.AlunoNaoEncontradoException;
import com.vyttor.cadastroaluno.exceptions.AlunoSaveException;
import com.vyttor.cadastroaluno.mappers.MapperAlunoEntity;
import com.vyttor.cadastroaluno.repositories.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.metrics.AutoConfigureMetrics;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


//Junit + Mockito + SpringBoot
@SpringBootTest
class AlunoServiceTest {

    @MockBean
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoService alunoService;

    @Test
    void createSucess() {
        //Cenario
        AlunoRequest alunoRequest = getAlunoRequest();

        AlunoEntity alunoEntity = new AlunoEntity();
        alunoEntity.setId(2L);
        alunoEntity.setNome( alunoRequest.getNome() );
        alunoEntity.setMateria( alunoRequest.getMateria() );
        alunoEntity.setDataNascimento( alunoRequest.getDataNascimento() );

        when(alunoRepository.save(any()))
                .thenReturn(alunoEntity);

        //Test, Execução
        AlunoResponse alunoResponse = alunoService.create(alunoRequest);

        //Verificação
        assertNotNull( alunoResponse.getId() );
        assertEquals( 2L, alunoResponse.getId() );
        assertEquals( "aluno teste", alunoResponse.getNome() );
    }

    @Test
    void createFail() {
        //Cenario
        AlunoRequest alunoRequest = getAlunoRequest();

        AlunoEntity alunoEntity = null;

        when(alunoRepository.save(any()))
                .thenReturn(alunoEntity);

        //Test, Execução

        assertThrows(
                AlunoSaveException.class,
                () -> alunoService.create(alunoRequest)
        );
    }

    @Test
    void updateSucess() {
        //Cenario
        Long idAluno = 1L;
        AlunoRequest alunoRequest = getAlunoRequest();

        AlunoEntity alunoEntity = new AlunoEntity();
        alunoEntity.setId( idAluno );
        alunoEntity.setNome( alunoRequest.getNome() );
        alunoEntity.setMateria( alunoRequest.getMateria() );
        alunoEntity.setDataNascimento( alunoRequest.getDataNascimento() );

        when(alunoRepository.save(any()))
                .thenReturn(alunoEntity);

        when( alunoRepository.findById(any()) )
                .thenReturn( Optional.of(alunoEntity) );

        //Teste- execução
        AlunoResponse alunoResponse = alunoService.update(idAluno, alunoRequest);

        //Verificação
        assertEquals( alunoRequest.getNome(), alunoResponse.getNome() );
        assertEquals( alunoRequest.getMateria(), alunoResponse.getMateria() );
        assertEquals( alunoRequest.getDataNascimento(), alunoResponse.getDataNascimento() );
        assertEquals( idAluno, alunoResponse.getId() );
        assertNotNull( alunoResponse.getId() );
    }

    @Test
    void updateFail() {
        //Cenario
        Long idAluno = 1L;
        AlunoRequest alunoRequest = getAlunoRequest();

        AlunoEntity alunoEntity = null;

        when(alunoRepository.save(any()))
                .thenReturn(alunoEntity);

        when( alunoRepository.findById(any()) )
                .thenReturn( Optional.ofNullable(alunoEntity) );

        //Teste- execução
        assertThrows(
                AlunoNaoEncontradoException.class,
                () -> alunoService.update(idAluno, alunoRequest)
        );
    }

    @Test
    void deleteSucess() {
        //Cenario
        Long idAluno = 1L;

        //Execução
        alunoService.delete( idAluno );
    }

    @Test
    void findAllSucess() {
        //cenario
        AlunoEntity alunoEntity1 = getAlunoEntity(1L);
        AlunoEntity alunoEntity2 = getAlunoEntity(2L);
        AlunoEntity alunoEntity3 = getAlunoEntity(3L);
        AlunoEntity alunoEntity4 = getAlunoEntity(4L);

        List<AlunoEntity> entityList = List.of(
                alunoEntity1,
                alunoEntity2,
                alunoEntity3,
                alunoEntity4
        );

        when( alunoRepository.findAll() )
                .thenReturn( entityList );

        //execução
        List<AlunoResponse> listaAlunos = alunoService.findAll();

        //verificação
        assertTrue( listaAlunos.size() > 0 );
        assertEquals( 4, listaAlunos.size() );
        assertEquals( 1L, listaAlunos.get(0).getId() );
        assertEquals( 2L, listaAlunos.get(1).getId() );
        assertEquals( 3L, listaAlunos.get(2).getId() );
        assertEquals( 4L, listaAlunos.get(3).getId() );
    }

    @Test
    void findByIdSucess() {
        //cenario
        Long idAluno = 1L;

        AlunoEntity alunoEntity = getAlunoEntity(1L);

        when( alunoRepository.findById(any()) )
                .thenReturn( Optional.of(alunoEntity) );

        //execução
        AlunoResponse alunoResponse = alunoService.findById(idAluno);

        //verificação
        assertEquals( idAluno, alunoResponse.getId() );
        assertNotNull( alunoResponse.getNome() );
        assertNotNull( alunoResponse.getMateria() );
        assertNotNull( alunoResponse.getDataNascimento() );
    }

    private AlunoRequest getAlunoRequest() {
        AlunoRequest alunoRequest = new AlunoRequest();
        alunoRequest.setNome("aluno teste");
        alunoRequest.setMateria("matemática");
        alunoRequest.setDataNascimento( LocalDate.of(1990, 4, 20) );

        return alunoRequest;
    }

    private AlunoEntity getAlunoEntity(Long id) {
        AlunoEntity alunoEntity = new AlunoEntity();
        alunoEntity.setId(id);
        alunoEntity.setNome( "aluno teste " + id );
        alunoEntity.setMateria( "materia teste" );
        alunoEntity.setDataNascimento( LocalDate.of( 1990, 06, 27 ) );

        return alunoEntity;
    }
}