package com.example.wbm.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingresar")
public class IngresarController {

    @GetMapping("")
    public String login(){
        return "/iniciarSesion/index";
    }

    @GetMapping("/registrarme")
    public String register(){
        return "/iniciarSesion/registrarse";
    }

}