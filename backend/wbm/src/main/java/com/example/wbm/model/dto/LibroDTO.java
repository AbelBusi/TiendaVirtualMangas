package com.example.wbm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibroDTO {

    private Integer idLibro;
    private String titulo;
    private String autor;
    private String descripcion;
    private LocalDateTime fechaPublicacion;
    private String editorial;
    private String genero;
    private Integer tipoLibro; // FK: id_tipo
    private Integer numPaginas;
    private String isbn;
    private String idioma;
    private String portadaUrl; // Ruta de la imagen, ej: /media/images/store/portada_nombre.jpg
    private BigDecimal precio;
    private Integer estado;

}