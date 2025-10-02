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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibroServicioImpl implements ILibroServicio {

    private final ILibroRepository libroRepository;
    private final ILibroMapper libroMapper;
    private final ITipoLibroRepository tipoLibroRepository; // Inyectar Repositorio de la FK

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
    public FormResponseSuccessDTO guardarLibro(LibroDTO libroDTO) {
        if(tituloExistente(libroDTO.getTitulo())){
            return new FormResponseSuccessDTO("El título del libro ya existe", false);
        }

        // 1. Convertir DTO a Entidad
        Libro libro = libroMapper.toEntity(libroDTO);

        // 2. Manejar la relación TipoLibro (FK)
        Optional<TipoLibro> tipoLibroOpt = tipoLibroRepository.findById(libroDTO.getTipoLibro());
        if (tipoLibroOpt.isEmpty()) {
            return new FormResponseSuccessDTO("El Tipo de Libro especificado no existe. ERROR", false);
        }
        libro.setTipoLibro(tipoLibroOpt.get());

        // 3. Establecer estado inicial y guardar
        libro.setEstado(1);
        libroRepository.save(libro);

        return new FormResponseSuccessDTO("El Libro fue creado con éxito", true );
    }

    @Transactional
    @Override
    public FormResponseSuccessDTO editarLibro(LibroDTO libroDTO) {
        Optional<Libro> libroOpt = libroRepository.findById(libroDTO.getIdLibro());

        if(libroOpt.isEmpty()){
            return new FormResponseSuccessDTO("No se puede guardar un libro que no existe. ERROR", false);
        }

        // 1. Convertir DTO a Entidad
        Libro libroGuardar = libroMapper.toEntity(libroDTO);

        // 2. Manejar la relación TipoLibro (FK)
        Optional<TipoLibro> tipoLibroOpt = tipoLibroRepository.findById(libroDTO.getTipoLibro());
        if (tipoLibroOpt.isEmpty()) {
            return new FormResponseSuccessDTO("El Tipo de Libro especificado no existe. ERROR al editar", false);
        }
        libroGuardar.setTipoLibro(tipoLibroOpt.get());

        // 3. Guardar cambios
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
}