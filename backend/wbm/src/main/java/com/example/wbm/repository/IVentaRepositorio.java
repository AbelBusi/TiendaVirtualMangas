package com.example.wbm.repository;

import com.example.wbm.model.entity.Venta;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepositorio extends JpaRepository<Venta,Integer> {
}
