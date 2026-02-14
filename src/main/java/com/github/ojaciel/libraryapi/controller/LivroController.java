package com.github.ojaciel.libraryapi.controller;

import com.github.ojaciel.libraryapi.controller.dto.CadastroLivroDTO;
import com.github.ojaciel.libraryapi.controller.dto.ErroResposta;
import com.github.ojaciel.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import com.github.ojaciel.libraryapi.controller.mappers.LivroMapper;
import com.github.ojaciel.libraryapi.exceptions.RegistroDuplicadoException;
import com.github.ojaciel.libraryapi.model.Livro;
import com.github.ojaciel.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
        //Mapear DTO para Entidade
        Livro livro = mapper.toEntity(dto);

        //Enviar a entidade para o Service validar e salvar
        service.salvar(livro);

        //Criar URL para acesso dos dados do livro
        var location = generateHeaderLocation(livro.getId());

        //Retornar código CREATED com header location
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable("id") String id) {
        return service.obterPorId(UUID.fromString(id)).map(livro -> {
            ResultadoPesquisaLivroDTO dto = mapper.toDTO(livro);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
