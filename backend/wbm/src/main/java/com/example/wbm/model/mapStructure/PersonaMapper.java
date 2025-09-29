package com.example.wbm.model.mapStructure;

import com.example.wbm.model.dto.PersonaDTO;
import com.example.wbm.model.entity.Persona;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    PersonaDTO toDto(Persona persona);

    Persona toEntity(PersonaDTO personaDTO);

}