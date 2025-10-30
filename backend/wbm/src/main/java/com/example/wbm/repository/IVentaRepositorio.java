package com.example.wbm.repository;

import com.example.wbm.model.entity.Venta;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IVentaRepositorio extends JpaRepository<Venta,Integer> {

    @Query("SELECT v FROM Venta v JOIN FETCH v.usuario u JOIN FETCH u.persona p")
    List<Venta> findAllConUsuarioYPersona();

    @Query("SELECT v FROM Venta v " +
            "JOIN FETCH v.detalleVentas dv " +
            "JOIN FETCH dv.libro l " + // 👈 ESTE FALTA
            "JOIN FETCH v.usuario u " +
            "JOIN FETCH u.persona p " +
            "WHERE v.idVenta = :idVenta")
    Optional<Venta> findByIdWithDetailsAndUser(@Param("idVenta") Integer idVenta);

}
