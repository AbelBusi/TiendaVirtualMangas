package com.example.wbm.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "persona")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "dni",length = 8,nullable = false,unique = true)
    private String dni;

    @Column(name = "nombre",length = 45,nullable = false)
    private String nombre;

    @Column(name = "apellidos",length = 45,nullable = false)
    private String apellidos;

    @Column(name = "edad",nullable = false)
    private LocalDate edad;

    @Column(name = "direccion",length = 45,nullable = false)
    private String direccion;

    @Column(name = "telefono",length = 20,nullable = false)
    private String telefono;

    @Column(name = "pais",length = 45,nullable = false)
    private String pais;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fecha_creacion;

    @Column(name = "estado",nullable = false)
    private int estado;

    @ToString.Exclude
    @OneToOne(mappedBy = "persona",fetch = FetchType.LAZY)
    private Usuario usuario;

}