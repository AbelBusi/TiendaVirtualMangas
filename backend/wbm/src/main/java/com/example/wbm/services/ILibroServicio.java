package com.example.wbm.services;

import com.example.wbm.model.dto.FormResponseSuccessDTO;
import com.example.wbm.model.dto.LibroDTO;

import java.util.List;

public interface ILibroServicio {

    List<LibroDTO> leerLibros();

    LibroDTO leerLibroPorId(Integer idLibro);

    // Se recomienda usar String para la URL o un objeto MultipartFile para el archivo
    FormResponseSuccessDTO guardarLibro(LibroDTO libroDTO);

    FormResponseSuccessDTO editarLibro(LibroDTO libroDTO);

    FormResponseSuccessDTO cambiarEstadoLibro(Integer idLibro);

    boolean tituloExistente(String titulo);
}