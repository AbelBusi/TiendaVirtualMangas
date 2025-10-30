package com.example.wbm.services;


import com.example.wbm.model.dto.DetalleVentaDTO;
import com.example.wbm.model.dto.VentaDTO;
import com.example.wbm.model.entity.DetalleVenta;
import com.example.wbm.model.entity.Venta;

import java.util.List;

public interface IDetalleVentaServicio {

    DetalleVenta crearDetalleVenta(DetalleVentaDTO detalleVentaDTO);

    boolean detalleVentaExistente(String detalleVenta);

    List<DetalleVentaDTO> leerDetalleVentas( Integer idDetalleVenta);

}
