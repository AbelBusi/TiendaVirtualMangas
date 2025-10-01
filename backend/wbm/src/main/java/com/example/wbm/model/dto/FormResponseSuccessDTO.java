package com.example.wbm.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FormResponseSuccessDTO {
    private String mensaje;
    private boolean exito;

}
