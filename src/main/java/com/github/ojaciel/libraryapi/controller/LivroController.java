package com.github.ojaciel.libraryapi.controller;

import com.github.ojaciel.libraryapi.controller.dto.CadastroLivroDTO;
import com.github.ojaciel.libraryapi.controller.dto.ErroResposta;
import com.github.ojaciel.libraryapi.controller.mappers.LivroMapper;
import com.github.ojaciel.libraryapi.exceptions.RegistroDuplicadoException;
import com.github.ojaciel.libraryapi.model.Livro;
import com.github.ojaciel.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
        try {
            //Mapear DTO para Entidade
            Livro livro = mapper.toEntity(dto);

            //Enviar a entidade para o Service validar e salvar
            service.salvar(livro);

            //Criar URL para acesso dos dados do livro
            var location = generateHeaderLocation(livro.getId());

            //Retornar código CREATED com header location
            return ResponseEntity.created(location).build();
        } catch (RegistroDuplicadoException e) {
            var erroDto = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDto.status()).body(erroDto);
        }
    }
}
