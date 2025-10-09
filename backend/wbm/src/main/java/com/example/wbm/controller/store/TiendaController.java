package com.example.wbm.controller.store;

import com.example.wbm.implementation.LibroServicioImpl;
import com.example.wbm.model.dto.LibroDTO;
import com.example.wbm.model.entity.Libro;
import com.example.wbm.model.mapStructure.ILibroMapper;
import com.example.wbm.services.ILibroServicio; // Importa tu servicio
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Necesario para pasar datos a Thymeleaf
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tienda")
@RequiredArgsConstructor // Para inyectar el servicio
public class TiendaController {

    private final LibroServicioImpl libroServicioImpl;
    private final ILibroMapper libroMapper;

    @GetMapping("")
    public String inicio(Model model){

        List<Libro> librosEntidades = libroServicioImpl.leerLibrosActivos();

        // 2. Mapear la lista de Entidades a una lista de DTOs
        List<LibroDTO> librosDTO = librosEntidades.stream()
                .map(libroMapper::toDto) // Usamos el mapper con la lógica anti-null
                .collect(Collectors.toList());

        // 3. Pasar la lista de DTOs al modelo
        model.addAttribute("librosActivos", librosDTO);

        return "/store/index";
    }
}
