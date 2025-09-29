package com.example.wbm.implementation;

import com.example.wbm.model.dto.UsuarioDTO;
import com.example.wbm.model.entity.Usuario;
import com.example.wbm.repository.IUsuarioRepository;
import com.example.wbm.services.IUsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements IUsuarioServicio {

    private IUsuarioRepository usuarioRepository;

    @Override
    public Usuario leerUsuario() {
        return null;
    }

    @Override
    public Usuario crearUsuario(UsuarioDTO personaDTO) {
        return null;
    }
}
