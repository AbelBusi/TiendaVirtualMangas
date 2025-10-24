package com.example.wbm.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Integer idLibro;

    @Column(name = "titulo", nullable = false, unique = true, length = 45)
    private String titulo;

    @Column(name = "autor", nullable = false, length = 45)
    private String autor;

    @Column(name = "descripcion", nullable = false, length = 400)
    private String descripcion;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDateTime fechaPublicacion;

    @Column(name = "editorial", nullable = false, length = 45)
    private String editorial;

    @Column(name = "genero", nullable = false, length = 45)
    private String genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_libro", nullable = false)
    private TipoLibro tipoLibro; // Relación con la entidad TipoLibro

    @Column(name = "n_paginas", nullable = false)
    private Integer numPaginas;

    @Column(name = "isbn", nullable = false, length = 45)
    private String isbn;

    @Column(name = "idioma", nullable = false, length = 45)
    private String idioma;

    // Ruta de la imagen, ej: /media/images/store/nombre.jpg
    @Column(name = "portada_url", nullable = false, length = 45)
    private String portadaUrl;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name="stock")
    private Integer stock;

    @OneToMany(mappedBy = "libro",fetch = FetchType.LAZY)
    private List<DetalleVenta> detalles;
}