package com.example.wbm.repository;


import com.example.wbm.model.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPerfilRepository extends JpaRepository<Perfil, Integer> {

    @Query(value = "SELECT pe.dni, pe.nombre " +
            "FROM perfil p " +
            "INNER JOIN usuario u ON p.usuario = u.id_usuario " +
            "INNER JOIN persona pe ON u.persona = pe.id_persona " +
            "WHERE p.rol = 2",
            nativeQuery = true)
    List<Object[]> obtenerDatosPersona();

}
