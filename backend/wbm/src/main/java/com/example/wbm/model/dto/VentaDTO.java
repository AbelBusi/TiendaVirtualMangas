package com.example.wbm.model.dto;

import com.example.wbm.model.entity.Usuario;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class VentaDTO {

    private Integer idVenta;

    private Usuario usuario;

    private Double subTotal;

    private Double igv;

    private Double total;

    private LocalDateTime fechaVenta;

    private String numeroVenta;

    private Integer estado;

    private List<DetalleVentaDTO> detalleVentas;

}