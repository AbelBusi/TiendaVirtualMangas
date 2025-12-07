package com.example.wbm.controller.admin;

import com.example.wbm.implementation.LibroServicioImpl;
import com.example.wbm.implementation.TipoLibroServicioImpl; // Asumiendo que este es tu servicio
import com.example.wbm.model.dto.LibroDTO;
import com.example.wbm.model.dto.TipoLibroDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        if (!model.containsAttribute("guardarLibro")) {
            LibroDTO guardarLibro = LibroDTO.builder()
                    .estado(1)
                    .fechaPublicacion(LocalDateTime.now())
                    .build();
            model.addAttribute("guardarLibro", guardarLibro);
        }

        LibroDTO editarLibro = LibroDTO.builder().build();
        model.addAttribute("editarLibro", editarLibro);

        List<LibroDTO> libros = libroServicio.leerLibros();
        model.addAttribute("libros", libros);

        List<TipoLibroDTO> tiposLibro = tipoLibroServicio.leerTiposLibro();
        model.addAttribute("tiposLibro", tiposLibro); // Usado para el select

        return "/administrador/admin-libro";
    }

    @PostMapping("/libros/guardar")
    public String guardarLibro(
            @Valid @ModelAttribute("guardarLibro") LibroDTO libroDTO,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file,
            Model model,
            RedirectAttributes ra) {

        if (bindingResult.hasErrors()) {

            List<LibroDTO> libros = libroServicio.leerLibros();
            model.addAttribute("libros", libros);

            List<TipoLibroDTO> tiposLibro = tipoLibroServicio.leerTiposLibro();
            model.addAttribute("tiposLibro", tiposLibro);

            LibroDTO editarLibro = LibroDTO.builder().build();
            model.addAttribute("editarLibro", editarLibro);

            return "/administrador/admin-libro";
        }

        try {

            libroServicio.guardarLibroConImagen(libroDTO, file);
            ra.addFlashAttribute("alerta_exito", "Libro agregado exitosamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("alerta_error", "Error al guardar el libro: " + e.getMessage());
        }

        return "redirect:/administrador/libros";
    }


    @PostMapping("/libros/editar")
    public String editarLibro(
            @Valid @ModelAttribute("editarLibro") LibroDTO libroDTO,
            BindingResult bindingResult,
            @RequestParam(value = "file", required = false) MultipartFile file,
            Model model,
            RedirectAttributes ra) {



        libroServicio.editarLibroConImagen(libroDTO, file);

        return "redirect:/administrador/libros";
    }
    @PostMapping("/libros/cambiarEstado")
    public String cambiarEstadoLibro(@RequestParam("idLibro") Integer idLibro) {

        libroServicio.cambiarEstadoLibro(idLibro);

        return "redirect:/administrador/libros";
    }

}
