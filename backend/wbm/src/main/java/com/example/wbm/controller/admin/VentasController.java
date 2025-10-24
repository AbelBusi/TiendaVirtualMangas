package com.example.wbm.controller.admin;

import com.example.wbm.implementation.LibroServicioImpl;
import com.example.wbm.implementation.VentaServicioImpl;
import com.example.wbm.model.dto.LibroDTO;
import com.example.wbm.model.dto.TipoLibroDTO;
import com.example.wbm.model.dto.VentaDTO;
import com.example.wbm.model.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administrador/ventas")
@RequiredArgsConstructor
public class VentasController {

    private final LibroServicioImpl libroServicio;
    private final VentaServicioImpl ventaServicio;


    @GetMapping("")
    public String tiposLibro(Model model) {
        List<LibroDTO> libros = libroServicio.leerLibros();
        model.addAttribute("libros", libros);

        List<Object[]> clientes = ventaServicio.mostrarClientes();
        model.addAttribute("clientes", clientes);

        VentaDTO venta = new VentaDTO();
        venta.setUsuario(new Usuario()); // ✅ Evita el null pointer de Thymeleaf
        model.addAttribute("venta", venta);

        return "administrador/admin-ventas";
    }
/*

    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute VentaDTO ventaDTO, Model model) {
        try {
            ventaServicio.registrarVentaConDetalles(ventaDTO);
            model.addAttribute("exito", "Venta registrada correctamente.");
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar la venta: " + e.getMessage());
        }

        model.addAttribute("libros", libroServicio.leerLibros());
        model.addAttribute("clientes", ventaServicio.mostrarClientes());
        model.addAttribute("venta", new VentaDTO());
        return "/administrador/admin-ventas";
    }

*/

    @GetMapping("/detalles")
    public String detalles(Model model) {


        return "/administrador/detalle-venta";
    }
}
