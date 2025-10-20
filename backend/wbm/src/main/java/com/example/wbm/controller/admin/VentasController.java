package com.example.wbm.controller.admin;

import com.example.wbm.model.dto.TipoLibroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administrador/ventas")
@RequiredArgsConstructor
public class VentasController {

    @GetMapping("")
    public String tiposLibro(Model model) {


        return "/administrador/admin-ventas";
    }

    @GetMapping("/detalles")
    public String detalles(Model model) {


        return "/administrador/detalle-venta";
    }
}
