package com.example.wbm.services;

import com.example.wbm.model.dto.FormResponseSuccessDTO;
import com.example.wbm.model.dto.LibroDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ILibroServicio {

    List<LibroDTO> leerLibros();

    LibroDTO leerLibroPorId(Integer idLibro);

    // Se recomienda usar String para la URL o un objeto MultipartFile para el archivo
// Dentro de ILibroServicio.java

// ... otros métodos

    FormResponseSuccessDTO guardarLibroConImagen(LibroDTO libroDTO, MultipartFile file);

    FormResponseSuccessDTO editarLibroConImagen(LibroDTO libroDTO, MultipartFile file);

    FormResponseSuccessDTO cambiarEstadoLibro(Integer idLibro);

    boolean tituloExistente(String titulo);
}