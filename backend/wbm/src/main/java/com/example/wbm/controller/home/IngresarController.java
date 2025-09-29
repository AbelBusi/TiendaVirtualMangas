package com.example.wbm.controller.home;

import com.example.wbm.implementation.PersonaServicioImpl;
import com.example.wbm.implementation.UsuarioServicioImpl;
import com.example.wbm.model.dto.FormIngresarDTO;
import com.example.wbm.model.mapStructure.PersonaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    //Se usara el Logger para poder darle un seguimiento y debug
    private final Logger logger = LoggerFactory.getLogger(IngresarController.class);

    @GetMapping("")
    public String login(){
        return "/iniciarSesion/index";
    }

    @GetMapping("/registrarme")
    public String register(Model formPersonaUsuario,@Valid FormIngresarDTO ingresarDTO){

        formPersonaUsuario.addAttribute("ingresarPersonaUsuario",ingresarDTO);

        return "/iniciarSesion/registrarse";
    }

    @PostMapping("/guardarPersona")
    public String guardarPersona(@Valid @ModelAttribute(name = "ingresarPersonaUsuario") FormIngresarDTO ingresarDTO){

        //personaServicio.crearPersona(ingresarDTO.getPersonaDTO());
        logger.info("Persona: {}",ingresarDTO);
        return "redirect:/ingresar";

    }
}