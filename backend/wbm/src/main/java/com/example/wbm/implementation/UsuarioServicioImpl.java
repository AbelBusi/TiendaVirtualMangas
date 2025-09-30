package com.example.wbm.implementation;

import com.example.wbm.model.dto.UsuarioDTO;
import com.example.wbm.model.entity.Usuario;
import com.example.wbm.model.mapStructure.IUsuarioMapper;
import com.example.wbm.repository.IUsuarioRepository;
import com.example.wbm.services.IUsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements IUsuarioServicio {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioMapper usuarioMapper;

    @Override
    public Usuario leerUsuario() {
        return null;
    }

    @Override
    public Usuario crearUsuario(UsuarioDTO personaDTO) {
        return null;
    }

    @Override
    public boolean UsuarioExistente(String correo) {


        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreo(correo);

        if(usuarioOptional.isPresent()){
            return true;
        }

        return false;
    }
}
