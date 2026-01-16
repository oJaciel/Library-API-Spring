package com.github.ojaciel.libraryapi.service;

import com.github.ojaciel.libraryapi.model.Autor;
import com.github.ojaciel.libraryapi.model.GeneroLivro;
import com.github.ojaciel.libraryapi.model.Livro;
import com.github.ojaciel.libraryapi.repository.AutorRepository;
import com.github.ojaciel.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void executar() {
        //Salva o autor
        Autor autor = new Autor();
        autor.setNome("Paulo");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1834, 6, 7));

        //Já executa e manda pro banco, ignorando possibilidade de rollback
        autorRepository.saveAndFlush(autor);


        //Salva o livro
        Livro livro = new Livro();
        livro.setIsbn("9841851-5151");
        livro.setPreco(BigDecimal.valueOf(25));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("História do Paulo");
        livro.setDataPublicacao(LocalDate.of(1880, 6, 12));

        livro.setAutor(autor);

        livroRepository.saveAndFlush(livro);

        //Forçando rollback (não faz as operações no banco, porque dá erro antes do final)
        if (autor.getNome().equals("Paulo")) {
            throw new RuntimeException("Rollback!");
        }
    }

    @Transactional
    public void atualizacaoSemAtualizar() {
        var livro = livroRepository.findById(UUID.fromString("b4378df6-1c02-4c55-a685-efc8d89b8485")).orElse(null);

        livro.setTitulo("Biografia do Paulo");
    }


}
