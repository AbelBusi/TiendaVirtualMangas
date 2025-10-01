package com.example.wbm.services;

import com.example.wbm.model.dto.CDPerfilDTO;
import com.example.wbm.model.dto.FormResponseSuccessDTO;

import java.util.List;

public interface IPerfilServicio {

    List<CDPerfilDTO> leerPerfiles();

    FormResponseSuccessDTO crearPerfil(CDPerfilDTO cdPerfilDTO);

    FormResponseSuccessDTO editarPerfil(CDPerfilDTO cdPerfilDTO);

    FormResponseSuccessDTO cambiarEstadoPerfil(Integer idPerfil);

    CDPerfilDTO leerPerfilPorId(Integer idPerfil);

}
