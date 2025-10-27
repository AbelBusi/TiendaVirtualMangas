package com.example.wbm.model.mapStructure;

import com.example.wbm.model.dto.CDUsuarioDTO;
import com.example.wbm.model.dto.UsuarioDTO;
import com.example.wbm.model.dto.UsuarioSesionDTO;
import com.example.wbm.model.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface IUsuarioMapper {

    UsuarioDTO toDto (Usuario usuario);

    Usuario toEntity(UsuarioDTO usuarioDTO);

    UsuarioSesionDTO toDtoSession (Usuario usuario);

    Usuario toEntitySession(UsuarioSesionDTO usuarioDTO);

    // Mapea Usuario + Persona a CDUsuarioDTO
    @Mapping(source = "persona.idPersona", target = "idPersona")
    @Mapping(source = "persona.dni", target = "dni")
    @Mapping(source = "persona.nombre", target = "nombrePersona")
    @Mapping(source = "persona.apellidos", target = "apellidos")
    @Mapping(source = "persona.edad", target = "edad")
    @Mapping(source = "persona.direccion", target = "direccion")
    @Mapping(source = "persona.telefono", target = "telefono")
    @Mapping(source = "persona.pais", target = "pais")
    @Mapping(source = "persona.estado", target = "estadoPersona")
    CDUsuarioDTO toCDDto(Usuario usuario);

    // Si quieres al revés (CDUsuarioDTO -> Usuario con Persona)
    @Mapping(target = "persona.idPersona", source = "idPersona")
    @Mapping(target = "persona.dni", source = "dni")
    @Mapping(target = "persona.nombre", source = "nombrePersona")
    @Mapping(target = "persona.apellidos", source = "apellidos")
    @Mapping(target = "persona.edad", source = "edad")
    @Mapping(target = "persona.direccion", source = "direccion")
    @Mapping(target = "persona.telefono", source = "telefono")
    @Mapping(target = "persona.pais", source = "pais")
    @Mapping(target = "persona.estado", source = "estadoPersona")
    Usuario toEntity(CDUsuarioDTO dto);

}
