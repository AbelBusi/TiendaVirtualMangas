package com.example.wbm.services;

import com.example.wbm.model.dto.FormResponseSuccessDTO;
import com.example.wbm.model.dto.RolDTO;
import com.example.wbm.model.dto.TipoLibroDTO;

import java.util.List;

public interface ITipoLibroServicio {

    List<TipoLibroDTO> leerTiposLibro ();

    FormResponseSuccessDTO guardarTipoLibro(TipoLibroDTO tipoLibroDTO);

    FormResponseSuccessDTO editarTipoLibro(TipoLibroDTO tipoLibroDTO);


    boolean tipoLibroExistente(String tipoLibro);

    FormResponseSuccessDTO cambiarEStadoTipoLibro(Integer idTipoLibro);



}