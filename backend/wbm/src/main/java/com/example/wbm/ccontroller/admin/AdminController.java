package com.example.wbm.ccontroller.admin;

import com.example.wbm.model.entity.Libro;
import com.example.wbm.model.entity.TipoLibro;
import com.example.wbm.repository.LibroRepository;
import com.example.wbm.repository.TipoLibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/administrador")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("")
    public String home (){
        return "/administrador/index";
    }

    @GetMapping("/usuarios")
    public String usuarios (){
        return "/administrador/table";
    }

}

