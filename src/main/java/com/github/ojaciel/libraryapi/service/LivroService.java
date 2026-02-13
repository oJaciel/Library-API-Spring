package com.github.ojaciel.libraryapi.service;

import com.github.ojaciel.libraryapi.controller.dto.CadastroLivroDTO;
import com.github.ojaciel.libraryapi.controller.dto.ErroResposta;
import com.github.ojaciel.libraryapi.exceptions.RegistroDuplicadoException;
import com.github.ojaciel.libraryapi.model.Livro;
import com.github.ojaciel.libraryapi.repository.LivroRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }
}
