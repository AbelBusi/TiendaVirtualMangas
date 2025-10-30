package com.example.wbm.repository;

import com.example.wbm.model.entity.DetalleVenta;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDetalleVentaRepositorio extends JpaRepository<DetalleVenta,Integer> {

    List<DetalleVenta> findByVenta_IdVenta(Integer idVenta);

    @Query("SELECT dv FROM DetalleVenta dv JOIN FETCH dv.libro l WHERE dv.venta.idVenta = :idVenta")
    List<DetalleVenta> findByVenta_IdVentaWithLibro(@Param("idVenta") Integer idVenta);
}
