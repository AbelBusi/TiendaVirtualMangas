package com.example.wbm.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsuarioSesionDTO {

    private Integer idUsuario;

    private String nombre;

    private String correo;

    private String password;

    private Integer estado;
}
