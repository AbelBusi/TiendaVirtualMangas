package com.example.wbm.services;

import com.example.wbm.model.dto.CDPersonaDTO;
import com.example.wbm.model.dto.CDUsuarioDTO;
import com.example.wbm.model.dto.FormResponseSuccessDTO;
import com.example.wbm.model.dto.UsuarioDTO;
import com.example.wbm.model.entity.Usuario;

import java.util.List;

public interface IUsuarioServicio {

    Usuario leerUsuario();

    Usuario crearUsuario(UsuarioDTO usuarioDTO);

    boolean UsuarioExistente(String correo);

    List<CDUsuarioDTO> leerUsuarios();


    FormResponseSuccessDTO crear(CDUsuarioDTO cdUsuarioDTO);

    FormResponseSuccessDTO editarUsuario(CDUsuarioDTO cdUsuarioDTO);

    FormResponseSuccessDTO cambiarEstadoUsuario(Integer idUsuario);


}
