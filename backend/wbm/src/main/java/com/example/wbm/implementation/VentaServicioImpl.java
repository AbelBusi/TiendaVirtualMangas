package com.example.wbm.implementation;

import com.example.wbm.model.dto.DetalleVentaDTO;
import com.example.wbm.model.dto.VentaDTO;
import com.example.wbm.model.entity.DetalleVenta;
import com.example.wbm.model.entity.Venta;
import com.example.wbm.model.mapStructure.VentaMapper;
import com.example.wbm.repository.IDetalleVentaRepositorio;
import com.example.wbm.repository.IPerfilRepository;
import com.example.wbm.repository.IVentaRepositorio;
import com.example.wbm.services.IVentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServicioImpl implements IVentaServicio {

    private final IVentaRepositorio ventaRepositorio;
    private final IPerfilRepository perfilRepository;
    private final IDetalleVentaRepositorio detalleVentaRepositorio;
    private final VentaMapper ventaMapper;

    public String generarNumeroPedido() {

        int numero = 0;

        String numeroConcatenado = "";

        List<VentaDTO> ventas = leerVentas();

        List<Integer> numeros = new ArrayList<Integer>();

        ventas.stream().forEach(p -> numeros.add(Integer.parseInt(p.getNumeroVenta())));

        if (ventas.isEmpty()) {

            numero = 1;

        } else {

            numero = numeros.stream().max(Integer::compare).get();
            numero++;

        }

        if (numero < 10) {

            numeroConcatenado = "000000000" + String.valueOf(numero);

        } else if (numero < 100) {

            numeroConcatenado = "00000000" + String.valueOf(numero);

        } else if (numero < 1000) {

            numeroConcatenado = "0000000" + String.valueOf(numero);

        } else if (numero < 10000) {

            numeroConcatenado = "000000" + String.valueOf(numero);

        }

        return numeroConcatenado;

    }


    @Override
    public VentaDTO crearVenta(VentaDTO ventaDTO) {
        Venta venta = ventaMapper.toEntity(ventaDTO);
        Venta guardada = ventaRepositorio.save(venta);
        return ventaMapper.toDto(guardada); // <- ahora devuelves la versión persistida
    }


    @Override
    public boolean ventaExistente(String venta) {
        return false;
    }

    @Override
    public List<VentaDTO> leerVentas() {
        List<Venta> ventas = ventaRepositorio.findAll();
        if (ventas == null || ventas.isEmpty()) {
            return new ArrayList<>();
        }
        return ventas.stream()
                .map(ventaMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<Object[]> mostrarClientes(){

        return perfilRepository.obtenerDatosPersona();

    }

    public List<VentaDTO> leerVentasPersonas() {
        List<Venta> ventas = ventaRepositorio.findAllConUsuarioYPersona();
        if (ventas == null || ventas.isEmpty()) {
            return new ArrayList<>();
        }
        return ventas.stream()
                .map(ventaMapper::toDto)
                .toList();
    }


}
