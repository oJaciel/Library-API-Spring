package com.github.ojaciel.libraryapi.controller;

import com.github.ojaciel.libraryapi.controller.dto.CadastroLivroDTO;
import com.github.ojaciel.libraryapi.controller.dto.ErroResposta;
import com.github.ojaciel.libraryapi.exceptions.RegistroDuplicadoException;
import com.github.ojaciel.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController {

    private LivroService service;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
        try {
            //Mapear DTO para Entidade

            //Enviar a entidade para o Service validar e salvar

            //Criar URL para acesso dos dados do livro

            //Retornar código CREATED com header location

            return ResponseEntity.ok(dto);
        } catch (RegistroDuplicadoException e) {
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }
}
