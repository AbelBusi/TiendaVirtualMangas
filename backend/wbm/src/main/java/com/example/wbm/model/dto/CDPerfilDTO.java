package com.example.wbm.model.dto;

import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CDPerfilDTO {

    private Integer idPerfil;

    // Datos del usuario
    private Integer idUsuario;
    private String nombreUsuario;
    private String correoUsuario;

    // Datos de la persona asociada al usuario
    private Integer idPersona;
    private String dni;
    private String nombrePersona;
    private String apellidos;
    private LocalDate edad;
    private String direccion;
    private String telefono;
    private String pais;
    private Integer estadoPersona;

    // Datos del rol
    private Integer idRol;
    private String nombreRol;
    private String descripcionRol;
    private Integer estadoRol;

    // Datos del permiso
    private Integer idPermiso;
    private String nombrePermiso;
    private String descripcionPermiso;
    private Integer estadoPermiso;

    // Otros campos
    private LocalDateTime fechaAsignacion;
    private Integer estado;

}

