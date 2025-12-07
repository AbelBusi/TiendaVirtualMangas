package com.example.wbm.services;

import com.example.wbm.model.dto.FormResponseSuccessDTO;
import com.example.wbm.model.dto.LibroDTO;
import com.example.wbm.model.entity.Libro;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ILibroServicio {

    List<LibroDTO> leerLibros();

    LibroDTO leerLibroPorId(Integer idLibro);

    FormResponseSuccessDTO guardarLibroConImagen(LibroDTO libroDTO, MultipartFile file);

    FormResponseSuccessDTO editarLibroConImagen(LibroDTO libroDTO, MultipartFile file);

    FormResponseSuccessDTO cambiarEstadoLibro(Integer idLibro);

    boolean tituloExistente(String titulo);

    List<Libro> leerLibrosActivos();

}