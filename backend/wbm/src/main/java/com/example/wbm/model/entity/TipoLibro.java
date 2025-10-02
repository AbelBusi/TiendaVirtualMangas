package com.example.wbm.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tipo_libro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo",nullable = false)
    private Integer idTipoLibro;

    @Column(name = "nombre", length = 45,unique = true,nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 400,nullable = false)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Integer estado;


}