package com.example.wbm.controller.home; // O el paquete correcto

import com.example.wbm.implementation.LibroServicioImpl;
import com.example.wbm.model.entity.Usuario;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
@RequiredArgsConstructor // Para inyectar el servicio automáticamente
public class InicioController {


    private final LibroServicioImpl libroServicio;

    @GetMapping("")
    public String home (Model model, HttpSession session){

        /*Usuario usuario = Usuario.builder().build();*/

        model.addAttribute("libros", libroServicio.leerLibrosActivos());
/*
        usuario = (Usuario) session.getAttribute("idUsuario");

        model.addAttribute("sesion",usuario);*/
        return "index";
    }
}
