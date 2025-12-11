package com.example.wbm.controller.home; // O el paquete correcto

import com.example.wbm.implementation.LibroServicioImpl;
import com.example.wbm.implementation.RecomendacionServiceImpl;
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
    private final RecomendacionServiceImpl recomendacionService;

    @GetMapping("")
    public String home(Model model, HttpSession session) {

        model.addAttribute("libros", libroServicio.leerLibrosActivos());

        // Usuario en sesión
        Usuario usuario = (Usuario) session.getAttribute("usuarioSesion");
        model.addAttribute("sesion", usuario);

        // Agregar las recomendaciones al index
        model.addAttribute("recomendaciones", recomendacionService.listar());

        return "index";
    }


    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession session){

        session.removeAttribute("usuarioSesion");

        return "redirect:/inicio";

    }

}
