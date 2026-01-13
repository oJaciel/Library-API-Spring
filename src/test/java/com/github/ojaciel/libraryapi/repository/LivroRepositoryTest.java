package com.github.ojaciel.libraryapi.repository;

import com.github.ojaciel.libraryapi.model.Autor;
import com.github.ojaciel.libraryapi.model.GeneroLivro;
import com.github.ojaciel.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("9841851-5151");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Alienígenas");
        livro.setDataPublicacao(LocalDate.of(1980, 6, 12));

        Autor autor = autorRepository.findById(UUID.fromString("be22ee50-4547-4570-a07b-c1dc62aac03e")).orElse(null);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("9841851-5151");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Dungeons and Dragons");
        livro.setDataPublicacao(LocalDate.of(1980, 6, 12));

        Autor autor = new Autor();
        autor.setNome("Lúcio");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1983, 4, 6));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("9841851-5151");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Dungeons and Dragons");
        livro.setDataPublicacao(LocalDate.of(1980, 6, 12));

        Autor autor = new Autor();
        autor.setNome("Lúcio");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1983, 4, 6));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    public void atualizarAutorDoLivro() {
        UUID id = UUID.fromString("87798ea7-829a-41c5-a8d5-d04e5334c4be");
        var livroParaAtualizar =
                repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("9004cc87-ee33-4393-a10a-eaed056a3505");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar() {
        UUID id = UUID.fromString("87798ea7-829a-41c5-a8d5-d04e5334c4be");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest() {
        UUID id = UUID.fromString("79797829-b32d-480a-befe-f009f1518914");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro");
        System.out.println(livro.getTitulo());
        System.out.println("Autor");
        System.out.println(livro.getAutor().getNome());
    }
}