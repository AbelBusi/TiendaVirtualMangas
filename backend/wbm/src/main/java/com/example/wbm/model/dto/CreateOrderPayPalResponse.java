package com.example.wbm.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderPayPalResponse {

    private String orderId;
    private String status;
    private String approvaUrl;

}
