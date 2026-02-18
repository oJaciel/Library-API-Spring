package com.github.ojaciel.libraryapi.service;

import com.github.ojaciel.libraryapi.controller.dto.CadastroLivroDTO;
import com.github.ojaciel.libraryapi.controller.dto.ErroResposta;
import com.github.ojaciel.libraryapi.exceptions.RegistroDuplicadoException;
import com.github.ojaciel.libraryapi.model.GeneroLivro;
import com.github.ojaciel.libraryapi.model.Livro;
import com.github.ojaciel.libraryapi.repository.LivroRepository;
import com.github.ojaciel.libraryapi.repository.specs.LivroSpecs;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(Livro livro) {
        repository.delete(livro);
    }

    public List<Livro> pesquisa(String isbn, String titulo, String nomeAutor, GeneroLivro genero, Integer anoPublicacao) {
        Specification<Livro> specs = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if (isbn !=null) {
            specs = specs.and(LivroSpecs.isbnEqual(isbn));
        }

        if (titulo != null) {
            specs = specs.and(LivroSpecs.tituloLike(titulo));
        }

        if (genero != null) {
            specs = specs.and(LivroSpecs.generoEqual(genero));
        }

        if (anoPublicacao != null) {
            specs = specs.and(LivroSpecs.anoPublicacaoEqual(anoPublicacao));
        }

        if (nomeAutor != null) {
            specs = specs.and(LivroSpecs.nomeAutorLike(nomeAutor));
        }

        return repository.findAll(specs);
    }
}
