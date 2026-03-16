package com.github.ojaciel.libraryapi.controller.mappers;

import com.github.ojaciel.libraryapi.controller.dto.UsuarioDTO;
import com.github.ojaciel.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
