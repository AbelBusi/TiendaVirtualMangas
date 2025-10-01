package com.example.wbm.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona", nullable = false)
    private Persona persona;

    @Column(name = "nombre",nullable = false,unique = true,length = 45)
    private String nombre;

    @Column(name = "correo",nullable = false,unique = true,length = 45)
    private String correo;

    @Column(name = "password",nullable = false,length = 45)
    private String password;

    @CreationTimestamp
    @Column(name = "fecha_creacion",nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "estado",nullable = false)
    private Integer estado;

}
