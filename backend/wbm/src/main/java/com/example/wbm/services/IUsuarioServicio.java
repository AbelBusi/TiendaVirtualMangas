package com.example.wbm.services;

import com.example.wbm.model.dto.UsuarioDTO;
import com.example.wbm.model.entity.Usuario;

public interface IUsuarioServicio {

    Usuario leerUsuario();

    Usuario crearUsuario(UsuarioDTO personaDTO);

}
