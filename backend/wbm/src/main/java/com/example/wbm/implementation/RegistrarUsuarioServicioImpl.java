package com.example.wbm.implementation;

import com.example.wbm.model.dto.FormIngresarDTO;
import com.example.wbm.model.mapStructure.PersonaMapper;
import com.example.wbm.repository.IPersonaRepository;
import com.example.wbm.repository.IUsuarioRepository;
import com.example.wbm.services.IRegistrarUsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrarUsuarioServicioImpl implements IRegistrarUsuarioServicio {

    private final IPersonaRepository personaRepository;
    private final IUsuarioRepository usuarioRepository;
    private final PersonaMapper personaMapper;
    @Override
    public void guardarUsuario(FormIngresarDTO formIngresarDTO) {



    }
}
