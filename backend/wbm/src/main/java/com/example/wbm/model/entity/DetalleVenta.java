package com.example.wbm.model.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.ToString;


@Entity
@Table(name = "detalle_venta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "venta",nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "libro",nullable = false)
    private Libro libro;

    @Column(name = "nombreLibro",nullable = false)
    private String nombreLibro;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "total")
    private Double total;

    @Column(name = "estado", nullable = false)
    private Integer estado;

}
