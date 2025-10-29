package com.example.wbm.repository;

import com.example.wbm.model.entity.Venta;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVentaRepositorio extends JpaRepository<Venta,Integer> {

    @Query("SELECT v FROM Venta v JOIN FETCH v.usuario u JOIN FETCH u.persona p")
    List<Venta> findAllConUsuarioYPersona();


}
