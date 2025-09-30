package com.example.wbm.implementation;

import com.example.wbm.model.dto.PersonaDTO;
import com.example.wbm.model.entity.Persona;
import com.example.wbm.model.mapStructure.PersonaMapper;
import com.example.wbm.repository.IPersonaRepository;
import com.example.wbm.services.IPersonaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaServicioImpl implements IPersonaServicio {

    private final IPersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    @Override
    public Persona leerPersona() {
        return null;
    }

    @Override
    public Persona crearPersona(PersonaDTO personaDTO) {

        Persona persona = personaMapper.toEntity(personaDTO);

        return personaRepository.save(persona);
    }

    @Override
    public boolean PersonaExistente(String dni) {

        Optional<Persona> personaOptional = personaRepository.findByDni(dni);

        if(personaOptional.isPresent()){
            return true;
        }

        return false;
    }
}
