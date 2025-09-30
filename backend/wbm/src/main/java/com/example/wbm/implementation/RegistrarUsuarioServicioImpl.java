package com.example.wbm.implementation;

import com.example.wbm.model.dto.FormIngresarDTO;
import com.example.wbm.model.dto.FormIngresarResponseDTO;
import com.example.wbm.model.entity.Persona;
import com.example.wbm.model.entity.Usuario;
import com.example.wbm.model.mapStructure.IUsuarioMapper;
import com.example.wbm.model.mapStructure.PersonaMapper;
import com.example.wbm.repository.IPersonaRepository;
import com.example.wbm.repository.IUsuarioRepository;
import com.example.wbm.services.IRegistrarUsuarioServicio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrarUsuarioServicioImpl implements IRegistrarUsuarioServicio {

    private final IPersonaRepository personaRepository;
    private final IUsuarioRepository usuarioRepository;
    private final PersonaMapper personaMapper;
    private final IUsuarioMapper usuarioMapper;
    private final UsuarioServicioImpl usuarioServicio;
    private final PersonaServicioImpl personaServicio;

    @Transactional
    @Override
    public FormIngresarResponseDTO guardarUsuario(FormIngresarDTO formIngresarDTO) {

        Persona persona = personaMapper.toEntity(formIngresarDTO.getPersonaDTO());
        Usuario usuario = usuarioMapper.toEntity(formIngresarDTO.getUsuarioDTO());

        if(personaServicio.PersonaExistente(persona.getDni())){
            return new FormIngresarResponseDTO("La persoma con este dni ya existe",false);
        }

        if ( usuarioServicio.UsuarioExistente(usuario.getCorreo())){
            return new FormIngresarResponseDTO("El correo de la pesona ya existe",false);
        }

        persona.setEstado(1);
        personaRepository.save(persona);
        usuario.setPersona(persona);
        usuario.setEstado(1);
        usuarioRepository.save(usuario);

        return new FormIngresarResponseDTO("La persona fue creada con exito y su respectivo usuario tambien fue agregado de forma correcta",true);

    }
}