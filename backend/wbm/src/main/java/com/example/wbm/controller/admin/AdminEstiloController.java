package com.example.wbm.controller.admin;

import com.example.wbm.model.CarruselItemText;
import com.example.wbm.security.IStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administrador")
@RequiredArgsConstructor
public class AdminEstiloController {

    private final IStorageService storageService;

    // MOCK DE DATOS - Simulación de persistencia de texto y extensiones
    private final List<CarruselItemText> carruselTexts = new ArrayList<>(List.of(
            // Item 1 (Se inicializa con una extensión por defecto si no hay DB)
            new CarruselItemText("El alpinista", "Accion", "One Piece es el manga más vendido... (Desc. 1)", "png", "jpg"),
            // Item 2
            new CarruselItemText("One Piece", "Aventura", "One Piece es el manga más vendido... (Desc. 2)", "jpg", "webp"),
            // Item 3
            new CarruselItemText("ONE PIECE", "PLANT", "One Piece es el manga más vendido... (Desc. 3)", "png", "jpg"),
            // Item 4
            new CarruselItemText("ONE PIECE", "NATURE", "One Piece es el manga más vendido... (Desc. 4)", "png", "webp")
    ));

    @GetMapping("/configuracion")
    public String home (Model model){
        model.addAttribute("carruselTexts", carruselTexts);
        // Aquí se pueden añadir flash messages que vienen de un RedirectAttributes anterior
        return "/administrador/estiloWeb";
    }

    @PostMapping("/web/upload-logo")
    public String uploadLogo(@RequestParam("logoFile") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Error: No se seleccionó ningún archivo para el logo.");
        } else {
            try {
                // Se corrigió para manejar excepciones en el almacenamiento
                String fileName = storageService.store(file, "iconos", "current-logo");
                redirectAttributes.addFlashAttribute("message", "Logo subido correctamente: " + fileName);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Error al subir el logo: " + e.getMessage());
            }
        }
        return "redirect:/administrador/configuracion";
    }

    @PostMapping("/web/upload-sidebar-image")
    public String uploadSidebarImage(@RequestParam("sidebarFile") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Error: No se seleccionó ningún archivo para la imagen del sidebar.");
        } else {
            try {
                // Se añade esta funcionalidad que faltaba en el HTML original
                String fileName = storageService.store(file, "iconos", "current-sidebar-bg");
                redirectAttributes.addFlashAttribute("message", "Imagen de Sidebar subida correctamente: " + fileName);
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Error al subir la imagen del sidebar: " + e.getMessage());
            }
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

        if (index < 1 || index > carruselTexts.size()) {
            redirectAttributes.addFlashAttribute("error", "Error: Índice de carrusel inválido.");
            return "redirect:/administrador/configuracion";
        }

        try {
            CarruselItemText item = carruselTexts.get(index - 1);
            String message = "No se seleccionó ningún archivo.";
            boolean success = false;

            if ("principal".equals(type) && principalFile != null && !principalFile.isEmpty()) {
                String originalFilename = principalFile.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();

                String principalPrefix = "carrusel-" + index;
                storageService.store(principalFile, "slider", principalPrefix); // Guarda el archivo
                item.setPrincipalExtension(extension); // Actualiza la extensión en el modelo

                message = "Fondo Carrusel " + index + " subido correctamente.";
                success = true;

            } else if ("thumbnail".equals(type) && thumbnailFile != null && !thumbnailFile.isEmpty()) {
                String originalFilename = thumbnailFile.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();

                String thumbPrefix = "thumb-" + index;
                storageService.store(thumbnailFile, "sliderTomo", thumbPrefix); // Guarda el archivo
                item.setThumbnailExtension(extension); // Actualiza la extensión en el modelo

                message = "Thumbnail Carrusel " + index + " subido correctamente.";
                success = true;
            }

            if (success) {
                // TODO: (Opcional) Guardar los cambios del modelo a un archivo JSON o DB
                redirectAttributes.addFlashAttribute("message", message);
            } else {
                redirectAttributes.addFlashAttribute("error", "Error al subir la imagen. " + message);
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al subir la imagen del carrusel: " + e.getMessage());
        }

        return "redirect:/administrador/configuracion";
    }

    // MÉTODO NUEVO/ACTUALIZADO: Gestión de Texto del Carrusel
    @PostMapping("/web/update-carrusel-text")
    public String updateCarruselText(
            @RequestParam("carruselIndex") int index,
            @RequestParam("title") String title,
            @RequestParam("type") String type, // 🆕 Nuevo parámetro
            @RequestParam("description") String description,
            RedirectAttributes redirectAttributes) {

        if (index >= 1 && index <= carruselTexts.size()) {
            CarruselItemText item = carruselTexts.get(index - 1);
            item.setTitle(title);
            item.setType(type); // 🆕 Guardar el tipo/categoría
            item.setDescription(description);

            // TODO: (Opcional) Guardar estos cambios a un archivo JSON o DB

            redirectAttributes.addFlashAttribute("message", "Texto del Carrusel #" + index + " actualizado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Error: Índice de carrusel inválido para actualizar texto.");
        }

        return "redirect:/administrador/configuracion";
    }
}