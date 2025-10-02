package com.example.wbm.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contactanos")
public class ContactanosController {

    @GetMapping("")
    public String contactanos(){
        return "/contactanos/index";
    }

}
