package com.github.ojaciel.libraryapi.repository;

import com.github.ojaciel.libraryapi.model.Autor;
import com.github.ojaciel.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    //Query Method
    //SELECT * FROM livro WHERE id_autor = id
    List<Livro> findByAutor(Autor autor);

    //SELECT * FROM livro WHERE titulo = titulo
    List<Livro> findByTitulo(String titulo);

    //SELECT * FROM livro WHERE isbn = isbn
    List<Livro> findByIsbn(String isbn);

    //SELECT * FROM livro WHERE titulo = titulo and preco = preco
    List<Livro> findByTituloAndPreco(String Titulo, BigDecimal preco);

    //SELECT * FROM livro WHERE titulo = titulo or isbn = isbn
    List<Livro> findByTituloOrIsbn(String Titulo, String isbn);
}
