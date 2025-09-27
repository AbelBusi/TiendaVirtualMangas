package com.example.wbm.repository;

// TipoLibroRepository.java

import com.example.wbm.model.entity.TipoLibro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoLibroRepository extends JpaRepository<TipoLibro, Integer> {
}

