package com.example.wbm.repository;

import com.example.wbm.model.entity.Rol;
import com.example.wbm.model.entity.TipoLibro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITipoLibroRepository extends JpaRepository<TipoLibro,Integer> {

    Optional<TipoLibro> findByNombre(String nombre);

}