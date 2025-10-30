package com.example.wbm.controller.admin;

import com.example.wbm.implementation.DetalleVentaServicioImpl;
import com.example.wbm.implementation.LibroServicioImpl;
import com.example.wbm.implementation.VentaServicioImpl;
import com.example.wbm.model.dto.DetalleVentaDTO;
import com.example.wbm.model.dto.LibroDTO;
import com.example.wbm.model.dto.VentaDTO;
import com.example.wbm.services.IDetalleVentaServicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/administrador/ventas")
@RequiredArgsConstructor
public class VentasController {

    private final LibroServicioImpl libroServicio;
    private final VentaServicioImpl ventaServicio;
    private final DetalleVentaServicioImpl detalleVentaServicio;


    // Instancia de ObjectMapper para convertir Java Object a JSON String
    private final ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping("")
    public String tiposLibro(Model model) throws JsonProcessingException {
        // 1. Cargar datos principales
        List<LibroDTO> libros = libroServicio.leerLibros();
        model.addAttribute("libros", libros);

        List<Object[]> clientes = ventaServicio.mostrarClientes();
        model.addAttribute("clientes", clientes);

        List<VentaDTO> ventas = ventaServicio.leerVentasPersonas();
        model.addAttribute("ventas", ventas);


        return "administrador/admin-ventas";
    }

// Controlador VentasController.java

    @GetMapping("/detalle/{idVenta}")
    public String verDetalleVenta(Model model, @PathVariable Integer idVenta) {
        VentaDTO venta = ventaServicio.obtenerVentaPorId(idVenta);

        // 💡 SOLUCIÓN ALTERNATIVA: Usar el servicio de detalles para cargar los datos explícitamente
        List<DetalleVentaDTO> detalles = detalleVentaServicio.leerDetalleVentas(idVenta);

        model.addAttribute("venta", venta);
        model.addAttribute("detalles", detalles); // <-- Carga los detalles usando el servicio

        return "administrador/detalle-venta";
    }


}