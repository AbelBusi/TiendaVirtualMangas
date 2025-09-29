package com.example.wbm.implementation;

import com.example.wbm.model.dto.PersonaDTO;
import com.example.wbm.model.entity.Persona;
import com.example.wbm.repository.IPersonaRepository;
import com.example.wbm.services.IPersonaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonaServicioImpl implements IPersonaServicio {

    private IPersonaRepository personaRepository;

    @Override
    public Persona leerPersona() {
        return null;
    }

    @Override
    public Persona crearPersona(PersonaDTO personaDTO) {

        Persona persona =

        return null;
    }
}
