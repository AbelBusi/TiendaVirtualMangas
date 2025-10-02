package com.example.wbm.controller.admin;

import com.example.wbm.implementation.LibroServicioImpl;
import com.example.wbm.implementation.TipoLibroServicioImpl; // Asumiendo que este es tu servicio
import com.example.wbm.model.dto.LibroDTO;
import com.example.wbm.model.dto.TipoLibroDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/administrador")
@RequiredArgsConstructor
public class librosController {

    private final TipoLibroServicioImpl tipoLibroServicio;
    private final LibroServicioImpl libroServicio;

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

    @GetMapping("/libros")
    public String libros(Model model){
        // 1. DTOs para el Formulario de AGREGAR
        LibroDTO guardarLibro = LibroDTO.builder()
                .estado(1)
                // Se establece una fecha de publicación por defecto para evitar errores de null
                .fechaPublicacion(LocalDateTime.now())
                .build();
        model.addAttribute("guardarLibro", guardarLibro);

        // 2. DTOs para el Formulario de EDITAR
        LibroDTO editarLibro = LibroDTO.builder().build();
        model.addAttribute("editarLibro", editarLibro);

        // 3. Listas para la tabla y Selects
        List<LibroDTO> libros = libroServicio.leerLibros();
        model.addAttribute("libros", libros);

        List<TipoLibroDTO> tiposLibro = tipoLibroServicio.leerTiposLibro();
        model.addAttribute("tiposLibro", tiposLibro); // Usado para el select

        return "/administrador/admin-libro";
    }

    @PostMapping("/libros/guardar")
    public String guardarLibro(@Valid @ModelAttribute("guardarLibro") LibroDTO libroDTO){

        // La lógica de subir la imagen se manejaría aquí o en el servicio.
        // Por simplicidad, asumimos que portadaUrl ya viene poblado (ej: con el nombre del archivo).
        libroServicio.guardarLibro(libroDTO);

        return "redirect:/administrador/libros";
    }

    @PostMapping("/libros/editar")
    public String editarLibro(@Valid @ModelAttribute("editarLibro") LibroDTO libroDTO){

        // La lógica de edición de imagen también iría aquí si fuera necesario.
        libroServicio.editarLibro(libroDTO);

        return "redirect:/administrador/libros";
    }

    @PostMapping("/libros/cambiarEstado")
    public String cambiarEstadoLibro(@RequestParam("idLibro") Integer idLibro) {

        libroServicio.cambiarEstadoLibro(idLibro);

        return "redirect:/administrador/libros";
    }

}
