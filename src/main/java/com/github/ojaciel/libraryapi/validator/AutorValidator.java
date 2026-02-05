package com.github.ojaciel.libraryapi.validator;

import com.github.ojaciel.libraryapi.exceptions.RegistroDuplicadoException;
import com.github.ojaciel.libraryapi.model.Autor;
import com.github.ojaciel.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public class AutorValidator {

    private AutorRepository repository;


    public AutorValidator(AutorRepository repository) {
        this.repository = repository;
    }

    public void validar(Autor autor) {
        if (existeAutorCadastrado(autor)) {
            throw new RegistroDuplicadoException("Autor já cadastrado!");
        }
    }

    private boolean existeAutorCadastrado(Autor autor) {
        //Busca no banco os dados do autor
        Optional<Autor> autorEncontrado = repository.findByNomeAndDataNascimentoAndNacionalidade(autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());

        //Se for um novo autor, verifica se existe um autor assim no banco
        if (autor.getId() == null) {
            return autorEncontrado.isPresent();
        }

        //Se for atualização, verifica se o autor atualizado não é igual a um autor cadastrado
        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
    }
}
