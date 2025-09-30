package com.example.wbm.services;

import com.example.wbm.model.dto.FormIngresarDTO;
import com.example.wbm.model.dto.FormIngresarResponseDTO;

public interface IRegistrarUsuarioServicio {

    FormIngresarResponseDTO guardarUsuario(FormIngresarDTO formIngresarDTO);

}
