package com.github.ojaciel.libraryapi.repository;

import com.github.ojaciel.libraryapi.model.Autor;
import com.github.ojaciel.libraryapi.model.GeneroLivro;
import com.github.ojaciel.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor<Livro> {
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

    //Query JPQL (referencia as entidades e propriedades da entidade)

    //SELECT l.* FROM livro as l ORDER BY l.titulo
    @Query("SELECT l FROM Livro AS l ORDER BY l.titulo, l.preco")
    List<Livro> listarTodosOrdenadoPorTituloEPreco();

    //SELECT a.* FROM livro l JOIN autor a on a.id = l.id_autor
    @Query("SELECT a from Livro l JOIN l.autor a")
    List<Autor> listarAutoresDosLivros();

    //SELECT DISTINCT l.titulo FROM livro
    @Query("SELECT DISTINCT l.titulo FROM Livro l")
    List<String> listarNomesDiferentesLivros();

    @Query("""
            SELECT DISTINCT l.genero
            FROM Livro l
            JOIN l.autor a
            WHERE a.nacionalidade = 'Brasileira'
            ORDER BY l.genero
            """)
    List<String> listarGenerosAutoresBrasileiros();

    //Named parameters
    @Query("SELECT l FROM Livro l WHERE l.genero = :genero ")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro);

    //Positional parameters
    @Query("SELECT l FROM Livro l WHERE l.genero = ?1")
    List<Livro> findByGeneroPositionalParams(GeneroLivro generoLivro);

    @Modifying //Significa que está modificando registros no banco
    @Transactional //Também precisa quando fizer update / delete / insert
    @Query("DELETE FROM Livro WHERE genero = ?1")
    void deleteByGenero(GeneroLivro genero);

    @Modifying //Significa que está modificando registros no banco
    @Transactional //Também precisa quando fizer update / delete / insert
    @Query("UPDATE Livro SET dataPublicacao = ?1")
    void updateDataPublicacao(LocalDate novaData);

    boolean existsByAutor(Autor autor);
}
