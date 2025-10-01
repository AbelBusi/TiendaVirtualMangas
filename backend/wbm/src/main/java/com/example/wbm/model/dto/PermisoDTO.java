package com.example.wbm.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PermisoDTO {

    private Integer idPermiso;

    @NotBlank(message = "Debes ingresar un rol de usuario y no algo vacío.")
    @Size(min = 2, max = 45, message = "El rol debe tener entre 8 y 45 caracteres.")
    private String nombre;

    @NotBlank(message = "Debes ingresar una descripcion y no algo vacío.")
    @Size(min = 2, max = 400, message = "La descripcion debe tener entre 2 y 400 caracteres.")
    private String descripcion;

    private Integer estado;

}
