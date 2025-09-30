package com.example.wbm.services;

import com.example.wbm.model.dto.PersonaDTO;
import com.example.wbm.model.dto.UsuarioDTO;
import com.example.wbm.model.entity.Persona;

public interface IPersonaServicio {

    Persona leerPersona();

    Persona crearPersona(PersonaDTO personaDTO);

    boolean PersonaExistente(String dni);


}