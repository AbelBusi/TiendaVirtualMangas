package com.example.wbm.repository;

import com.example.wbm.model.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona,Integer> {

    Optional<Persona> findByDni(String dni);

}