package com.example.wbm.controller.admin;

import com.example.wbm.model.entity.Recomendacion;
import com.example.wbm.services.RecomendacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/administrador/recomendaciones")
@RequiredArgsConstructor
public class RecomendacionController {

    private final RecomendacionService service;
    private static final String UPLOAD_DIR = "backend/wbm/uploads/images";
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("recomendaciones", service.listar());
        return "recomendacion/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("recomendacion", new Recomendacion());
        return "recomendacion/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Recomendacion r,
                          @RequestParam("imagenSliderFile") MultipartFile imagenSliderFile,
                          @RequestParam("imagenThumbnailFile") MultipartFile imagenThumbnailFile) throws IOException {

        // Imagen principal
        if (!imagenSliderFile.isEmpty()) {
            String nombreArchivo = imagenSliderFile.getOriginalFilename();
            Path destino = Paths.get(UPLOAD_DIR + "slider/" + nombreArchivo);
            Files.createDirectories(destino.getParent()); // asegura que exista la carpeta
            Files.copy(imagenSliderFile.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

            r.setImageUrl(nombreArchivo);
        }

        // Thumbnail
        if (!imagenThumbnailFile.isEmpty()) {
            String nombreArchivo = imagenThumbnailFile.getOriginalFilename();
            Path destino = Paths.get(UPLOAD_DIR + "sliderTomo/" + nombreArchivo);
            Files.createDirectories(destino.getParent());
            Files.copy(imagenThumbnailFile.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

            r.setThumbnailUrl(nombreArchivo);
        }

        service.guardar(r);
        return "redirect:/administrador/recomendaciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Recomendacion r = service.buscarPorId(id);
        model.addAttribute("recomendacion", r);
        return "recomendacion/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return "redirect:/administrador/recomendaciones";
    }
}
