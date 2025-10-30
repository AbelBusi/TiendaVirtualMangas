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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Transactional(readOnly = true)
    public List<DetalleVentaDTO> leerDetalleVentas(Integer idVenta) {

        // 💡 USAR el método con JOIN FETCH
        List<DetalleVenta> detalleVentas = detalleVentaRepositorio.findByVenta_IdVentaWithLibro(idVenta);

        if (detalleVentas == null || detalleVentas.isEmpty()) {
            return new ArrayList<>();
        }
        // El mapeo se realiza ahora que 'libro' está cargado.
        return detalleVentas.stream()
                .map(detalleVentaMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true) // Es solo lectura, optimiza la transacción
    public List<DetalleVentaDTO> leerTodosLosDetalles() {
        // 1. Obtener todas las entidades DetalleVenta de la base de datos
        List<DetalleVenta> detalleVentas = detalleVentaRepositorio.findAll();

        // 2. Mapear la lista de Entidades a DTOs
        if (detalleVentas == null || detalleVentas.isEmpty()) {
            return new ArrayList<>();
        }

        return detalleVentas.stream()
                .map(detalleVentaMapper::toDto)
                .toList();
    }
}