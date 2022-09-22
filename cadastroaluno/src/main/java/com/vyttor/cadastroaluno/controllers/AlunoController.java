package com.vyttor.cadastroaluno.controllers;

import com.vyttor.cadastroaluno.dtos.requests.AlunoRequest;
import com.vyttor.cadastroaluno.dtos.responses.AlunoResponse;
import com.vyttor.cadastroaluno.services.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoResponse> criar(@RequestBody AlunoRequest alunoRequest){
        AlunoResponse alunoResponse = alunoService.create(alunoRequest);

        return ResponseEntity.ok( alunoResponse );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> atualizar(@PathVariable Long id, @RequestBody AlunoRequest alunoRequest){
        AlunoResponse alunoResponse = alunoService.update(id, alunoRequest);

        return ResponseEntity.ok( alunoResponse );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        alunoService.delete( id );
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listarTodos(){

        List<AlunoResponse> listaAlunosResponse = alunoService.findAll();

        return ResponseEntity.ok( listaAlunosResponse );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> obter(@PathVariable Long id){
        AlunoResponse alunoResponse = alunoService.findById(id);

        return ResponseEntity.ok( alunoResponse );
    }
}
