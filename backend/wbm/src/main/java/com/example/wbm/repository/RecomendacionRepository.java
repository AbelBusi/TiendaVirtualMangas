package com.example.wbm.repository;


import com.example.wbm.model.entity.Recomendacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecomendacionRepository extends JpaRepository<Recomendacion, Integer> {
}
