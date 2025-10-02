package com.example.wbm.repository;

import com.example.wbm.model.entity.Libro;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILibroRepository extends JpaRepository<Libro, Integer> {

    // Método para verificar la existencia por título, útil para guardar.
    Optional<Libro> findByTitulo(String titulo);

    // Método para verificar la existencia por ISBN.
    Optional<Libro> findByIsbn(String isbn);

    List<Libro> findByEstado(Integer estado);

    @EntityGraph(attributePaths = {"tipoLibro"})
    List<Libro> findByEstadoIsTrue();

}