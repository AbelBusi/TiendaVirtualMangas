package com.example.wbm.repository;

import com.example.wbm.model.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolRepository extends JpaRepository<Rol,Integer> {

    Optional<Rol> findByNombre(String nombre);

}