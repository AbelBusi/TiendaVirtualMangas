package com.example.wbm.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tipo_libro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer idTipo;

    @Column(nullable = false, unique = true, length = 45)
    private String nombre;

    @Column(nullable = false, length = 400)
    private String descripcion;

    @Column(nullable = false)
    private Integer estado;

    @OneToMany(mappedBy = "tipoLibro",fetch = FetchType.LAZY)
    List<Libro> libros;

}
