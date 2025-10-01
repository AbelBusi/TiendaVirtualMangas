package com.example.wbm.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "permiso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso",nullable = false)
    private Integer idPermiso;

    @Column(name = "nombre", length = 45,unique = true,nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 45,nullable = false)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @OneToMany(mappedBy = "permiso")
    private List<Perfil> permisos;

}
