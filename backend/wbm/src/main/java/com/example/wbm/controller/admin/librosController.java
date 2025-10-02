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

    // ... (Métodos para tiposLibro: tiposLibro, guardarTipoLibro, editarTipoLibro, cambiarEstadoTipoLibro - se omiten por brevedad) ...

    @GetMapping("/libros")
    public String libros(Model model){
        // 1. DTOs para el Formulario de AGREGAR (Asegúrate de que 'guardarLibro' siempre esté en el modelo)
        if (!model.containsAttribute("guardarLibro")) {
            LibroDTO guardarLibro = LibroDTO.builder()
                    .estado(1)
                    .fechaPublicacion(LocalDateTime.now())
                    .build();
            model.addAttribute("guardarLibro", guardarLibro);
        }

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

    // ----------------------------------------------------
    // MÉTODO MODIFICADO PARA MANEJAR VALIDACIÓN
    // ----------------------------------------------------
    @PostMapping("/libros/guardar")
    public String guardarLibro(
            @Valid @ModelAttribute("guardarLibro") LibroDTO libroDTO,
            BindingResult bindingResult, // <--- 1. Capturamos los errores de validación
            @RequestParam("file") MultipartFile file,
            Model model, // Usamos Model si volvemos a la vista (errores)
            RedirectAttributes ra) { // Usamos RedirectAttributes si redirigimos (éxito)

        // 2. Comprobamos si hay errores de validación
        if (bindingResult.hasErrors()) {

            // Si hay errores, debemos recargar todas las listas y DTOs necesarias
            // para que la página 'admin-libro' no se caiga.
            List<LibroDTO> libros = libroServicio.leerLibros();
            model.addAttribute("libros", libros);

            List<TipoLibroDTO> tiposLibro = tipoLibroServicio.leerTiposLibro();
            model.addAttribute("tiposLibro", tiposLibro);

            // Recargamos el DTO de editar, ya que no tiene relación con el error
            LibroDTO editarLibro = LibroDTO.builder().build();
            model.addAttribute("editarLibro", editarLibro);

            // El objeto 'guardarLibro' (con los errores) ya está en el modelo
            // y se puede acceder desde Thymeleaf.

            // Retorna a la misma vista. El script SweetAlert2 de tu HTML se encargará de mostrar los errores.
            return "/administrador/admin-libro";
        }

        // 3. Lógica para guardar (si no hay errores)
        try {
            // Aquí puedes añadir validación para la imagen si es necesaria
            // (Ej: if (file.isEmpty()) { ... })
            libroServicio.guardarLibroConImagen(libroDTO, file);
            ra.addFlashAttribute("alerta_exito", "Libro agregado exitosamente.");
        } catch (Exception e) {
            ra.addFlashAttribute("alerta_error", "Error al guardar el libro: " + e.getMessage());
        }

        // Redirecciona si todo fue exitoso
        return "redirect:/administrador/libros";
    }


    // ----------------------------------------------------
    // Método de EDITAR (Con Imagen opcional) - Se recomienda aplicar validación aquí también
    // ----------------------------------------------------
    @PostMapping("/libros/editar")
    public String editarLibro(
            @Valid @ModelAttribute("editarLibro") LibroDTO libroDTO,
            BindingResult bindingResult, // <--- Añadido BindingResult
            @RequestParam(value = "file", required = false) MultipartFile file, // <-- Archivo opcional
            Model model,
            RedirectAttributes ra) {

        // Se omite la lógica de manejo de errores de editar por ser similar,
        // pero DEBERÍAS implementarla.

        libroServicio.editarLibroConImagen(libroDTO, file);

        return "redirect:/administrador/libros";
    }
    @PostMapping("/libros/cambiarEstado")
    public String cambiarEstadoLibro(@RequestParam("idLibro") Integer idLibro) {

        libroServicio.cambiarEstadoLibro(idLibro);

        return "redirect:/administrador/libros";
    }

}
