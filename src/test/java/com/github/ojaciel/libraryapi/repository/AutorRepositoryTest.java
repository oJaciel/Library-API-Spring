package com.github.ojaciel.libraryapi.repository;

import com.github.ojaciel.libraryapi.model.Autor;
import com.github.ojaciel.libraryapi.model.GeneroLivro;
import com.github.ojaciel.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Juan");
        autor.setNacionalidade("Espanhola");
        autor.setDataNascimento(LocalDate.of(2000, 1, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo.toString());
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("70a1a514-fde2-431b-8947-8fbedb97f22e");

        Optional<Autor> autor = repository.findById(id);
        if(autor.isPresent()) {
            Autor autorEncontrado = autor.get();

            System.out.println("Dados do autor");
            System.out.println(autor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 25));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores " + repository.count());
    }

    //Deleta pelo id
    @Test
    public void deleteByIdTest() {
        var id = UUID.fromString("70a1a514-fde2-431b-8947-8fbedb97f22e");
        repository.deleteById(id);
    }

    //Deleta pela entidade completa
    @Test
    public void deleteTest() {
        var id = UUID.fromString("338ee841-a58d-4ce9-aaa2-b74c034983e3");
        var autor = repository.findById(id).get();
        repository.delete(autor);
    }

    @Test
    void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("Lovecraft");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1917, 8, 2));

        Livro livro = new Livro();
        livro.setIsbn("8941154-15115");
        livro.setPreco(BigDecimal.valueOf(70));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Chamado de Cthulhu");
        livro.setDataPublicacao(LocalDate.of(1940, 9, 6));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("1511-98451");
        livro2.setPreco(BigDecimal.valueOf(40));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("A Cor que Caiu do Céu");
        livro2.setDataPublicacao(LocalDate.of(1938, 9, 6));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        //livroRepository.saveAll(autor.getLivros());
    }
}
