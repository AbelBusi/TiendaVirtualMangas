package com.example.wbm.controller.store;

import com.example.wbm.implementation.LibroServicioImpl;
import com.example.wbm.model.dto.DetalleVentaDTO;
import com.example.wbm.model.dto.LibroDTO;
import com.example.wbm.model.dto.VentaDTO;
import com.example.wbm.model.entity.Libro;
import com.example.wbm.model.mapStructure.ILibroMapper;
import com.example.wbm.services.ILibroServicio; // Importa tu servicio
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Necesario para pasar datos a Thymeleaf
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tienda")
@RequiredArgsConstructor // Para inyectar el servicio
public class TiendaController {

    private final LibroServicioImpl libroServicioImpl;
    private final ILibroMapper libroMapper;
    private final Logger loggger = LoggerFactory.getLogger(TiendaController.class);

    List<DetalleVentaDTO> detalles = new ArrayList<>();
    VentaDTO pedido = new VentaDTO();

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


    @GetMapping("libros/{id}")
    public String libro(@PathVariable Integer id, Model model){


        LibroDTO libroDTO= libroServicioImpl.leerLibroPorId(id);

        model.addAttribute("libro", libroDTO);


        List<Libro> librosActivos = libroServicioImpl.leerLibrosActivos();

        List<Libro> librosCategoria = librosActivos.subList(0,5);

        model.addAttribute("librosActivos", librosCategoria);

        return "/store/libro";
    }

    @GetMapping("/pagar")
    public String venta(Model model){


        return "/store/pagando";
    }

    @PostMapping("/carrito")
    public String addCarrito(@RequestParam Integer idLibro, @RequestParam Integer cantidad, Model model) {

        DetalleVentaDTO detalleOrden = new DetalleVentaDTO();
        LibroDTO producto = new LibroDTO();
        double sumaTotal = 0;
        LibroDTO libroTRaido = libroServicioImpl.leerLibroPorId(idLibro);

        loggger.info("cantidad: {}", cantidad);

        producto =libroTRaido;
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio().doubleValue());
        detalleOrden.setNombreLibro(producto.getTitulo());
        detalleOrden.setPortada(producto.getPortadaUrl());
        detalleOrden.setTotal(producto.getPrecio().doubleValue() * cantidad);
        detalleOrden.setLibro(producto);

        //Validacion para que el producto no se sobrecargue muchas veces
        Integer idproducto = producto.getIdLibro();

        boolean ingresar = detalles.stream().anyMatch(p -> p.getLibro().getIdLibro() == idproducto);
        if (!ingresar) {

            detalles.add(detalleOrden);

        }

        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        pedido.setTotal(sumaTotal);
        model.addAttribute("carrito", detalles);
        model.addAttribute("pedido", pedido);

        return "/carrito/index";
    }



}
