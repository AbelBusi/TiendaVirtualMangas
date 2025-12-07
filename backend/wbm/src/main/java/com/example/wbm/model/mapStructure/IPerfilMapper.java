package com.example.wbm.model.mapStructure;

import com.example.wbm.model.dto.CDPerfilDTO;
import com.example.wbm.model.entity.Perfil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IPerfilMapper {

    @Mappings({
            // Usuario
            @Mapping(source = "usuario.idUsuario", target = "idUsuario"),
            @Mapping(source = "usuario.nombre", target = "nombreUsuario"),
            @Mapping(source = "usuario.correo", target = "correoUsuario"),

            // Persona
            @Mapping(source = "usuario.persona.idPersona", target = "idPersona"),
            @Mapping(source = "usuario.persona.dni", target = "dni"),
            @Mapping(source = "usuario.persona.nombre", target = "nombrePersona"),
            @Mapping(source = "usuario.persona.apellidos", target = "apellidos"),
            @Mapping(source = "usuario.persona.edad", target = "edad"),
            @Mapping(source = "usuario.persona.direccion", target = "direccion"),
            @Mapping(source = "usuario.persona.telefono", target = "telefono"),
            @Mapping(source = "usuario.persona.pais", target = "pais"),
            @Mapping(source = "usuario.persona.estado", target = "estadoPersona"),

            // Rol
            @Mapping(source = "rol.idRol", target = "idRol"),
            @Mapping(source = "rol.nombre", target = "nombreRol"),
            @Mapping(source = "rol.descripcion", target = "descripcionRol"),
            @Mapping(source = "rol.estado", target = "estadoRol"),

            // Permiso
            @Mapping(source = "permiso.idPermiso", target = "idPermiso"),
            @Mapping(source = "permiso.nombre", target = "nombrePermiso"),
            @Mapping(source = "permiso.descripcion", target = "descripcionPermiso"),
            @Mapping(source = "permiso.estado", target = "estadoPermiso"),

            // Perfil
            @Mapping(source = "idPerfil", target = "idPerfil"),
            @Mapping(source = "fechaAsignacion", target = "fechaAsignacion"),
            @Mapping(source = "estado", target = "estado")
    })
    CDPerfilDTO toCDDto(Perfil perfil);

    @Mappings({
            @Mapping(target = "usuario.idUsuario", source = "idUsuario"),

            @Mapping(target = "usuario.persona.idPersona", source = "idPersona"),

            @Mapping(target = "rol.idRol", source = "idRol"),

            @Mapping(target = "permiso.idPermiso", source = "idPermiso"),

            @Mapping(target = "idPerfil", source = "idPerfil"),
            @Mapping(target = "fechaAsignacion", source = "fechaAsignacion"),
            @Mapping(target = "estado", source = "estado")
    })
    Perfil toEntity(CDPerfilDTO dto);

}

