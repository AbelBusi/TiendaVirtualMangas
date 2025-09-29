package com.example.wbm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormIngresarDTO {

    private PersonaDTO personaDTO;
    private UsuarioDTO usuarioDTO;

}