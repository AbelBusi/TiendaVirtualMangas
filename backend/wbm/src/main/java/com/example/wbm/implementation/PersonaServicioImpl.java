package com.example.wbm.implementation;

import com.example.wbm.model.dto.CDPersonaDTO;
import com.example.wbm.model.dto.FormResponseSuccessDTO;
import com.example.wbm.model.dto.PersonaDTO;
import com.example.wbm.model.entity.Persona;
import com.example.wbm.model.entity.Rol;
import com.example.wbm.model.mapStructure.PersonaMapper;
import com.example.wbm.repository.IPersonaRepository;
import com.example.wbm.repository.IUsuarioRepository;
import com.example.wbm.services.IPersonaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonaServicioImpl implements IPersonaServicio {

    private final IPersonaRepository personaRepository;
    private final PersonaMapper personaMapper;
    private final IUsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CDPersonaDTO> leerPersonas() {

        List<Persona> personas = personaRepository.findAll();

        if (personas.size()==0){
            return Collections.emptyList();
        }
        List<CDPersonaDTO> personasDto= personas.stream()
                .map(personaMapper::toDtoPersona)
                .toList();

        return personasDto;

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

    @Transactional
    @Override
    public FormResponseSuccessDTO crear(CDPersonaDTO cdPersonaDTO) {
        Persona persona = personaMapper.toEntityPersona(cdPersonaDTO);

        if(PersonaExistente(persona.getDni())){
            return new FormResponseSuccessDTO("La persona que intenta ingresar ya existe",false);
        }
        persona.setEstado(1);
        personaRepository.save(persona);

        return new FormResponseSuccessDTO("La persona fue creado con exito",true );
    }

    @Override
    public FormResponseSuccessDTO editarPersona(CDPersonaDTO personaDTO) {
        Optional<Persona> persona = personaRepository.findById(personaDTO.getIdPersona());

        if(!persona.isPresent()){
            return new FormResponseSuccessDTO("No se puede guardar una persona que no existe. ERROR",false);
        }

        Persona guardarPersona = personaMapper.toEntityPersona(personaDTO);
        personaRepository.save(guardarPersona);

        return new FormResponseSuccessDTO("Se modifico la persona de forma correcta",true);
    }

    @Override
    public FormResponseSuccessDTO cambiarEStadoPersona(Integer idPersona) {
        Optional<Persona> personaOpt = personaRepository.findById(idPersona);

        if (!personaOpt.isPresent()) {
            return new FormResponseSuccessDTO("No se puede cambiar el estado de la persona. ERROR", false);
        }

        Persona persona = personaOpt.get();
        if (persona.getEstado() == 1) {
            persona.setEstado(0);
            personaRepository.save(persona);
            return new FormResponseSuccessDTO("El estado de la persona pasó a Inactivo", true);
        } else {
            persona.setEstado(1);
            personaRepository.save(persona);
            return new FormResponseSuccessDTO("El estado de la persona pasó a Activo", true);
        }
    }

    @Transactional(readOnly = true)
    public List<CDPersonaDTO> leerPersonasSinUsuario() {
        return personaRepository.findAll().stream()
                .map(personaMapper::toDtoPersona)
                .filter(p -> !usuarioRepository.existsByPersona_IdPersona(p.getIdPersona()))
                .toList();
    }


}
