package com.example.wbm.repository;


import com.example.wbm.model.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPerfilRepository extends JpaRepository<Perfil, Integer> {
    // Puedes agregar consultas personalizadas si lo necesitas
}
