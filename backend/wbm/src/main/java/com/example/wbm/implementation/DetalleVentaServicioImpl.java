package com.example.wbm.implementation;

import com.example.wbm.model.dto.DetalleVentaDTO;
import com.example.wbm.model.dto.VentaDTO;
import com.example.wbm.model.entity.DetalleVenta;
import com.example.wbm.model.entity.Venta;
import com.example.wbm.model.mapStructure.DetalleVentaMapper;
import com.example.wbm.repository.IDetalleVentaRepositorio;
import com.example.wbm.repository.IVentaRepositorio;
import com.example.wbm.services.IDetalleVentaServicio;
import com.example.wbm.services.IVentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleVentaServicioImpl implements IDetalleVentaServicio {

    private final IDetalleVentaRepositorio detalleVentaRepositorio;
    private final DetalleVentaMapper detalleVentaMapper;

    @Override
    public DetalleVenta crearDetalleVenta(DetalleVentaDTO detalleVentaDTO) {

        DetalleVenta detalleVenta = detalleVentaMapper.toEntity(detalleVentaDTO);
        detalleVenta.setEstado(1);

        return detalleVentaRepositorio.save(detalleVenta);

    }

    @Override
    public boolean detalleVentaExistente(String detalleVenta) {
        return false;
    }

    @Override
    public List<DetalleVentaDTO> leerDetalleVentas() {
        return null;
    }
}
