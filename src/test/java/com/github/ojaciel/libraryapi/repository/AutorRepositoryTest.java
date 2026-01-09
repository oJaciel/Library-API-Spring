package com.github.ojaciel.libraryapi.repository;

import com.github.ojaciel.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository repository;

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
}
