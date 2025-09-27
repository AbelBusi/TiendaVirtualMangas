package com.example.wbm.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "libro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Integer idLibro;

    @Column(nullable = false, unique = true, length = 45)
    private String titulo;

    @Column(nullable = false, length = 45)
    private String autor;

    @Column(nullable = false, length = 400)
    private String descripcion;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDateTime fechaPublicacion;

    @Column(nullable = false, length = 45)
    private String editorial;

    @Column(nullable = false, length = 45)
    private String genero;

    @ManyToOne
    @JoinColumn(name = "tipo_libro", nullable = false)
    private TipoLibro tipoLibro;

    @Column(name = "n_paginas", nullable = false)
    private Integer nPaginas;

    @Column(nullable = false, length = 45)
    private String isbn;

    @Column(nullable = false, length = 45)
    private String idioma;

    @Column(name = "portada_url", nullable = false, length = 45)
    private String portadaUrl;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer estado;
}
