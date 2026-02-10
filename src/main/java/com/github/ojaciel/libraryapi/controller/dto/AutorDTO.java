package com.github.ojaciel.libraryapi.controller.dto;

import com.github.ojaciel.libraryapi.model.Autor;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        @NotBlank(message = "Campo obrigatório!")
        @Size(min = 2, max = 100, message = "Campo fora do tamanho padrão!")
        String nome,
        @NotNull(message = "Campo obrigatório!")
        @Past(message = "Nascimento deve ser uma data anterior ao dia atual!")
        LocalDate dataNascimento,
        @NotBlank(message = "Campo obrigatório!")
        @Size(min = 2, max = 50, message = "Campo fora do tamanho padrão!")
        String nacionalidade
) {

    public Autor mapearParaAutor() {
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
