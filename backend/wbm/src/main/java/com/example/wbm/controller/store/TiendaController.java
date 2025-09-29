package com.example.wbm.controller.store;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tienda")
public class TiendaController {

    @GetMapping("")
    public String inicio(){
        return "/store/index";
    }



}
