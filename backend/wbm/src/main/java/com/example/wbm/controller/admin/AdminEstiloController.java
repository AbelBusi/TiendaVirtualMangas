package com.example.wbm.controller.admin;

import com.example.wbm.security.IStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/administrador")
@RequiredArgsConstructor
public class AdminEstiloController {

    private final IStorageService storageService;

    @GetMapping("/configuracion")
    public String home (Model model){
        return "/administrador/estiloWeb";
    }

    @PostMapping("/web/upload-logo")
    public String uploadLogo(@RequestParam("logoFile") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (!file.isEmpty()) {
            String fileName = storageService.store(file, "iconos", "current-logo");
            redirectAttributes.addFlashAttribute("message", "Logo subido correctamente: " + fileName);
        }
        return "redirect:/administrador/configuracion";
    }

    @PostMapping("/web/upload-sidebar-image")
    public String uploadSidebarImage(@RequestParam("sidebarFile") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (!file.isEmpty()) {
            String fileName = storageService.store(file, "iconos", "current-sidebar-bg");
            redirectAttributes.addFlashAttribute("message", "Imagen de Sidebar subida correctamente: " + fileName);
        }
        return "redirect:/administrador/configuracion";
    }

    @PostMapping("/web/upload-carrusel-image")
    public String uploadCarruselImage(
            @RequestParam(value = "carruselFile", required = false) MultipartFile principalFile,
            @RequestParam(value = "thumbnailFile", required = false) MultipartFile thumbnailFile,
            @RequestParam("carruselIndex") int index,
            @RequestParam("type") String type,
            RedirectAttributes redirectAttributes) {

        String message = "No se seleccionó ningún archivo.";
        boolean success = false;
        String fileName = "";

        if ("principal".equals(type) && principalFile != null && !principalFile.isEmpty()) {
            String principalPrefix = "carrusel-" + index;
            fileName = storageService.store(principalFile, "slider", principalPrefix);
            message = "Fondo Carrusel " + index + " subido correctamente: " + fileName;
            success = true;

        } else if ("thumbnail".equals(type) && thumbnailFile != null && !thumbnailFile.isEmpty()) {
            String thumbPrefix = "thumb-" + index;
            fileName = storageService.store(thumbnailFile, "sliderTomo", thumbPrefix);
            message = "Thumbnail Carrusel " + index + " subido correctamente: " + fileName;
            success = true;
        }

        if (success) {
            redirectAttributes.addFlashAttribute("message", message);
        } else {
            redirectAttributes.addFlashAttribute("error", "Error al subir la imagen. " + message);
        }

        return "redirect:/administrador/configuracion";
    }
}