package com.example.wbm.model.dto;

import com.example.wbm.model.entity.Libro;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DetalleVentaDTO {

    private Integer idDetalle;
    private VentaDTO venta;
    private LibroDTO libro;
    private String nombreLibro;
    private String portada;
    private Integer cantidad;
    private Double precio;
    private Double total;
    private Integer estado;

}