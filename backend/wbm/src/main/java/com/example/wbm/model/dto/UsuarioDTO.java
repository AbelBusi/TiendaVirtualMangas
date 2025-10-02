package com.example.wbm.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UsuarioDTO {

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
    @Size(min = 8, max = 300, message = "La clave debe tener entre 8 y 45 caracteres.")
    private String password;

    @NotNull(message = "Se debe ingresar el estado de la persona con relacion al sistema.")
    private Integer estado;
}
