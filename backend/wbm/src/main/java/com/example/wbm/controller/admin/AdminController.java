package com.example.wbm.controller.admin;

import com.example.wbm.implementation.RolServicioImpl;
import com.example.wbm.model.dto.RolDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administrador")
@RequiredArgsConstructor
public class AdminController {

    private final RolServicioImpl rolServicio;

    @GetMapping("")
    public String home (){
        return "/administrador/index";
    }

    @GetMapping("/usuarios")
    public String usuarios (){
        return "/administrador/table";
    }

    @GetMapping("/roles")
    public String roless(Model model) {

        RolDTO rolDTO = RolDTO.builder()
                .estado(1) // inicializamos en 1
                .build();
        model.addAttribute("guardarRol", rolDTO);

        List<RolDTO> roles = rolServicio.leerRoles();
        model.addAttribute("roles", roles);

        RolDTO rolEditar =RolDTO.builder()
                .estado(1)
                .build();
        model.addAttribute("editarRol",rolEditar);

        return "/administrador/admin-roles";
    }


    @PostMapping("/roles/guardarRoles")
    public String guardarRoles(@Valid @ModelAttribute("guardarRol") RolDTO rolDTO){

        rolServicio.guardarRol(rolDTO);

        return "redirect:/administrador/roles";
    }

    @PostMapping("/roles/editarRoles")
    public String editarRoles(@Valid @ModelAttribute("editarRol") RolDTO rolDTO){

        rolServicio.editarRol(rolDTO);

        return "redirect:/administrador/roles";
    }



}

