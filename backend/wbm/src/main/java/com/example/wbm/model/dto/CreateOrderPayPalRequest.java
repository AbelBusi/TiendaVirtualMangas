package com.example.wbm.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
public class CreateOrderPayPalRequest {

    private BigDecimal amount;
    private String currency;
    private String description;
    private String returnUrl;
    private String cancelUrl;

}