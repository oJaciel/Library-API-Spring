package com.github.ojaciel.libraryapi.controller.mappers;

import com.github.ojaciel.libraryapi.controller.dto.AutorDTO;
import com.github.ojaciel.libraryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}
