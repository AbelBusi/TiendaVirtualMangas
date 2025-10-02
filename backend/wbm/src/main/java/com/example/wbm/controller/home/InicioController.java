package com.example.wbm.controller.home; // O el paquete correcto

import com.example.wbm.services.ILibroServicio; // Importa tu servicio
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
@RequiredArgsConstructor // Para inyectar el servicio automáticamente
public class InicioController {

    // Inyecta el servicio de libros
    private final ILibroServicio libroServicio;

    @GetMapping("")
    public String home (Model model){
        // 1. Obtiene la lista de libros
        // Nota: Si solo quieres libros "Activos" (estado = 1), debes crear un método en tu servicio para eso.
        model.addAttribute("libros", libroServicio.leerLibrosActivos());

        return "index";
    }
}
