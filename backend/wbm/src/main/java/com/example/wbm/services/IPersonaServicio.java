package com.example.wbm.services;

import com.example.wbm.model.dto.*;
import com.example.wbm.model.entity.Persona;

import java.util.List;

public interface IPersonaServicio {

    List<CDPersonaDTO> leerPersonas();

    Persona crearPersona(PersonaDTO personaDTO);

    boolean PersonaExistente(String dni);

    FormResponseSuccessDTO crear(CDPersonaDTO cdPersonaDTO);

    FormResponseSuccessDTO editarPersona(CDPersonaDTO personaDTO);

    FormResponseSuccessDTO cambiarEStadoPersona(Integer idPersona);




}