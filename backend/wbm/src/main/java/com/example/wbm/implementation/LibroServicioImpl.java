package com.example.wbm.implementation;

import com.example.wbm.model.dto.FormResponseSuccessDTO;
import com.example.wbm.model.dto.LibroDTO;
import com.example.wbm.model.entity.Libro;
import com.example.wbm.model.entity.TipoLibro;
import com.example.wbm.model.mapStructure.ILibroMapper;
import com.example.wbm.repository.ILibroRepository;
import com.example.wbm.repository.ITipoLibroRepository; // Necesario para la FK
import com.example.wbm.services.ILibroServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile; // Importante para la gestión de imágenes

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LibroServicioImpl implements ILibroServicio {

    private final ILibroRepository libroRepository;
    private final ILibroMapper libroMapper;
    private final ITipoLibroRepository tipoLibroRepository;

    // CAMBIO CLAVE EN EL SERVICIO
    private static final String UPLOAD_DIR = "uploads/images/store/";// Inyectar Repositorio de la FK

    @Transactional(readOnly = true)
    @Override
    public List<LibroDTO> leerLibros() {
        List<Libro> libros = libroRepository.findAll();

        if (libros.isEmpty()){
            return Collections.emptyList();
        }
        return libros.stream()
                .map(libroMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public LibroDTO leerLibroPorId(Integer idLibro) {
        return libroRepository.findById(idLibro)
                .map(libroMapper::toDto)
                .orElse(null);
    }



    @Transactional
    @Override
    public FormResponseSuccessDTO guardarLibroConImagen(LibroDTO libroDTO, MultipartFile file) {

        // 1. Manejo del Archivo (Imagen)
        try {
            // Generar nombre único para evitar conflictos
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + extension;

            // Ruta completa donde se guardará
            Path copyLocation = Paths.get(UPLOAD_DIR + uniqueFilename);

            // Guardar el archivo físicamente
            Files.copy(file.getInputStream(), copyLocation);

            // Guardar la URL relativa en el DTO/Entidad
            libroDTO.setPortadaUrl(uniqueFilename);

        } catch (IOException e) {
            // Manejo de error si la subida falla
            e.printStackTrace();
            return new FormResponseSuccessDTO("Error al guardar la imagen. Libro no creado.", false);
        }

        // 2. Lógica de negocio (igual a la anterior)
        if(tituloExistente(libroDTO.getTitulo())){
            return new FormResponseSuccessDTO("El título del libro ya existe", false);
        }

        Libro libro = libroMapper.toEntity(libroDTO);

        Optional<TipoLibro> tipoLibroOpt = tipoLibroRepository.findById(libroDTO.getTipoLibro());
        if (tipoLibroOpt.isEmpty()) {
            return new FormResponseSuccessDTO("El Tipo de Libro especificado no existe. ERROR", false);
        }
        libro.setTipoLibro(tipoLibroOpt.get());

        libro.setEstado(1);
        libroRepository.save(libro);

        return new FormResponseSuccessDTO("El Libro fue creado con éxito", true );
    }

    @Transactional
    @Override
    public FormResponseSuccessDTO editarLibroConImagen(LibroDTO libroDTO, MultipartFile file) {
        Optional<Libro> libroOpt = libroRepository.findById(libroDTO.getIdLibro());

        if(libroOpt.isEmpty()){
            return new FormResponseSuccessDTO("No se puede guardar un libro que no existe. ERROR", false);
        }

        Libro libroExistente = libroOpt.get();

        // 1. Manejo del Archivo (Imagen)
        if (file != null && !file.isEmpty()) {
            try {
                // Generar nombre único y guardar nuevo archivo
                String originalFilename = file.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String uniqueFilename = UUID.randomUUID().toString() + extension;

                Path copyLocation = Paths.get(UPLOAD_DIR + uniqueFilename);
                Files.copy(file.getInputStream(), copyLocation, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                // Actualizar la URL de la portada con la nueva
                libroDTO.setPortadaUrl(uniqueFilename);

                // OPCIONAL: Lógica para eliminar el archivo viejo (se recomienda implementar esto)

            } catch (IOException e) {
                e.printStackTrace();
                return new FormResponseSuccessDTO("Error al actualizar la imagen.", false);
            }
        } else {
            // Si NO se sube un nuevo archivo, mantener la URL que ya existía en la base de datos
            libroDTO.setPortadaUrl(libroExistente.getPortadaUrl());
        }

        // 2. Lógica de negocio para el resto de la edición
        Libro libroGuardar = libroMapper.toEntity(libroDTO);

        Optional<TipoLibro> tipoLibroOpt = tipoLibroRepository.findById(libroDTO.getTipoLibro());
        if (tipoLibroOpt.isEmpty()) {
            return new FormResponseSuccessDTO("El Tipo de Libro especificado no existe. ERROR al editar", false);
        }
        libroGuardar.setTipoLibro(tipoLibroOpt.get());

        libroRepository.save(libroGuardar);

        return new FormResponseSuccessDTO("Se modificó el libro de forma correcta", true);
    }
    @Transactional(readOnly = true)
    @Override
    public boolean tituloExistente(String titulo) {
        return libroRepository.findByTitulo(titulo).isPresent();
    }

    @Transactional
    @Override
    public FormResponseSuccessDTO cambiarEstadoLibro(Integer idLibro) {
        Optional<Libro> libroOpt = libroRepository.findById(idLibro);

        if (libroOpt.isEmpty()) {
            return new FormResponseSuccessDTO("No se puede cambiar el estado del libro. ERROR", false);
        }

        Libro libro = libroOpt.get();
        if (libro.getEstado() == 1) {
            libro.setEstado(0);
            libroRepository.save(libro);
            return new FormResponseSuccessDTO("El estado del libro pasó a Inactivo", true);
        } else {
            libro.setEstado(1);
            libroRepository.save(libro);
            return new FormResponseSuccessDTO("El estado del libro pasó a Activo", true);
        }
    }

    @Override
    public List<Libro> leerLibrosActivos() {
        // Llama al método del repositorio para obtener solo los libros con estado = 1
        return libroRepository.findByEstadoIsTrue();
    }

}