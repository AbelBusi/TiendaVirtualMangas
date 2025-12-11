package com.example.wbm.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
@RequiredArgsConstructor
public class AdminServicios {

    @GetMapping("/revista")
    public String revistaServicio (){
        return "/administrador/adminServerRevista"; // O /administrador/mangas
    }

    @GetMapping("/revista/hero")
    public String revistaServicioHero (){
        return "/administrador/hero";
    }

    @GetMapping("/revista/about")
    public String revistaServicioAbout (){
        return "/administrador/about";
    }

    @GetMapping("/revista/posts")
    public String revistaServicioPosts (){
        return "/administrador/posts";
    }

}