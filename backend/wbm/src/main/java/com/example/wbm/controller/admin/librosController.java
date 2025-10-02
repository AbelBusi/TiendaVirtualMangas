package com.example.wbm.controller.admin;

import com.example.wbm.implementation.TipoLibroServicioImpl; // Asumiendo que este es tu servicio
import com.example.wbm.model.dto.TipoLibroDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administrador")
@RequiredArgsConstructor
public class librosController {

    private final TipoLibroServicioImpl tipoLibroServicio;

    @GetMapping("/tiposLibro")
    public String tiposLibro(Model model) {

        TipoLibroDTO tipoLibroDTO = TipoLibroDTO.builder()
                .estado(1)
                .build();
        model.addAttribute("guardarTipoLibro", tipoLibroDTO);

        List<TipoLibroDTO> tiposLibro = tipoLibroServicio.leerTiposLibro();
        model.addAttribute("tiposLibro", tiposLibro);

        TipoLibroDTO tipoLibroEditar = TipoLibroDTO.builder().build();
        model.addAttribute("editarTipoLibro", tipoLibroEditar);

        return "/administrador/admin-categoriaLibros";
    }

    @PostMapping("/tiposLibro/guardar")
    public String guardarTipoLibro(@Valid @ModelAttribute("guardarTipoLibro") TipoLibroDTO tipoLibroDTO){

        tipoLibroServicio.guardarTipoLibro(tipoLibroDTO);

        return "redirect:/administrador/tiposLibro";
    }

    @PostMapping("/tiposLibro/editar")
    public String editarTipoLibro(@Valid @ModelAttribute("editarTipoLibro") TipoLibroDTO tipoLibroDTO){

        tipoLibroServicio.editarTipoLibro(tipoLibroDTO);

        return "redirect:/administrador/tiposLibro";
    }

    @PostMapping("/tiposLibro/cambiarEstado")
    public String cambiarEstadoTipoLibro(@RequestParam("idTipoLibro") Integer idTipoLibro) {

        tipoLibroServicio.cambiarEStadoTipoLibro(idTipoLibro);

        return "redirect:/administrador/tiposLibro";
    }
}
