package com.example.wbm.repository;

import com.example.wbm.model.entity.DetalleVenta;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleVentaRepositorio extends JpaRepository<DetalleVenta,Integer> {
}
