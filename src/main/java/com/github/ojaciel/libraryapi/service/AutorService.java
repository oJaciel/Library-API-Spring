package com.github.ojaciel.libraryapi.service;

import com.github.ojaciel.libraryapi.model.Autor;
import com.github.ojaciel.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    public Autor salvar(Autor autor) {
        return repository.save(autor);
    }
}

