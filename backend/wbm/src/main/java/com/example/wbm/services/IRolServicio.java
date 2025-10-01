package com.example.wbm.services;

import com.example.wbm.model.dto.FormResponseSuccessDTO;
import com.example.wbm.model.dto.RolDTO;

import java.util.List;

public interface IRolServicio {

    List<RolDTO> leerRoles ();

    FormResponseSuccessDTO guardarRol(RolDTO rolDTO);

    FormResponseSuccessDTO editarRol(RolDTO rolDTO);


    boolean rolExistente(String rol);

    FormResponseSuccessDTO cambiarEStadoRol(Integer idRol);



}