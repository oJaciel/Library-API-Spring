package com.github.ojaciel.libraryapi.controller.mappers;

import com.github.ojaciel.libraryapi.controller.dto.CadastroLivroDTO;
import com.github.ojaciel.libraryapi.model.Livro;
import com.github.ojaciel.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )")
    public abstract Livro toEntity(CadastroLivroDTO dto);
}
