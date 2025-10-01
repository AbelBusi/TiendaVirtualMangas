package com.example.wbm.model.mapStructure;

import com.example.wbm.model.dto.UsuarioDTO;
import com.example.wbm.model.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUsuarioMapper {

    UsuarioDTO toDto (Usuario usuario);

    Usuario toEntity(UsuarioDTO usuarioDTO);

}
