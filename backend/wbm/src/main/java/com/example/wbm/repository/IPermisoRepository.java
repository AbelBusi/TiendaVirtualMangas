package com.example.wbm.repository;


import com.example.wbm.model.entity.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPermisoRepository extends JpaRepository<Permiso, Integer> {
    // Ejemplo: buscar permiso por nombre
    Permiso findByNombre(String nombre);
}
