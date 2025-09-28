package com.example.wbm.ccontroller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingresar")
public class loginController {

    @GetMapping("")
    public String login(){
        return "/iniciarSesion/index";
    }

}