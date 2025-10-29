package com.example.wbm.services;


import com.example.wbm.model.dto.VentaDTO;
import com.example.wbm.model.entity.Venta;

import java.util.List;

public interface IVentaServicio {

    VentaDTO crearVenta(VentaDTO ventaDTO);

    boolean ventaExistente(String venta);

    List<VentaDTO> leerVentas();

}