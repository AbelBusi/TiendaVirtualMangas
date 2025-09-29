package com.example.wbm.controller.home;

import com.example.wbm.implementation.PersonaServicioImpl;
import com.example.wbm.implementation.UsuarioServicioImpl;
import com.example.wbm.model.entity.Persona;
import com.example.wbm.model.mapStructure.PersonaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingresar")
@RequiredArgsConstructor
public class IngresarController {

    private PersonaServicioImpl personaServicio;
    private UsuarioServicioImpl usuarioServicio;
    private PersonaMapper personaMapper;

    @GetMapping("")
    public String login(){
        return "/iniciarSesion/index";
    }

    @GetMapping("/registrarme")
    public String register(){
        return "/iniciarSesion/registrarse";
    }

    @PostMapping("/guardarPersona")
    public String guardarPersona(@Valid @ModelAttribute Persona persona, Model model){



        return "";
    }


}