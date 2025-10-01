package com.example.wbm.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CDUsuarioDTO {

    private Integer idUsuario;

    @NotBlank(message = "Debes ingresar un nombre de usuario y no algo vacío.")
    @Size(min = 8, max = 45, message = "El usuario debe tener entre 8 y 45 caracteres.")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio.")
    @Email(message = "Debe ser un correo con formato válido.")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "El correo debe incluir un dominio válido (ej: usuario@dominio.com)."
    )
    private String correo;

    @NotBlank(message = "Debes ingresar una clave y no algo vacío.")
    @Size(min = 8, max = 45, message = "La clave debe tener entre 8 y 45 caracteres.")
    private String password;

    private Integer estado;

    private Integer idPersona;
    private String dni;
    private String nombrePersona;
    private String apellidos;
    private LocalDate edad;
    private String direccion;
    private String telefono;
    private String pais;
    private Integer estadoPersona;

}
