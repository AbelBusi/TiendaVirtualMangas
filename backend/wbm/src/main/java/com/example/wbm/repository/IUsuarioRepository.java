package com.example.wbm.repository;

import com.example.wbm.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByPersona_IdPersona(Integer idPersona);

    @Query("SELECT u FROM Usuario u " +
            "JOIN FETCH u.perfiles p " +
            "JOIN FETCH p.rol " +
            "WHERE u.correo = :correo")
    Optional<Usuario> findByCorreoWithPerfiles(@Param("correo") String correo);



}
