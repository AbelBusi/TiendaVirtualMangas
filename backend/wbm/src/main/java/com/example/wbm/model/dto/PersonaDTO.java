package com.example.wbm.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PersonaDTO {

    @NotBlank(message = "Se debe ingresar un dni existente.")
    @Size(min = 8,max = 8,message = "El DNI debe tener 8 digitos de forma obligatoria.")
    private String dni;

    @NotBlank(message = "Se debe ingresar el nombre de la persona.")
    @Size(min = 1,max = 45,message = "El nombre de la persona no debe ser mayor a 45 ni menor a 0.")
    private String nombre;

    @NotBlank(message = "Se debe ingresar los apellidos de la persona.")
    @Size(min = 1,max = 45,message = "Los apellidos de la persona no debe ser mayor a 45 ni menor a 0.")
    private String apellidos;

    @NotNull(message = "Se debe ingresar la edad de la persona.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate edad;

    @NotBlank(message = "Se debe ingresar la direccion de la persona.")
    @Size(min = 1,max = 45,message = "La direccion de la persona no debe ser mayor a 45 ni menor a 0.")
    private String direccion;

    @NotBlank(message = "Se debe ingresar el telefono de la persona.")
    @Size(min = 1,max = 20,message = "El telefono de la persona no debe ser mayor a 20 ni menor a 0.")
    private String telefono;

    @NotBlank(message = "Se debe ingresar el pais de la persona.")
    @Size(min = 1,max = 45,message = "El pais de la persona no debe ser mayor a 45 ni menor a 0.")
    private String pais;


    @NotNull(message = "Se debe ingresar el estado de la persona con relacion al sistema.")
    private Integer estado;

}