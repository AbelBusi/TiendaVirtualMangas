package com.example.wbm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarruselItemText {
    private String title;
    private String type;
    private String description;

    private String principalExtension;

    private String thumbnailExtension;

    public CarruselItemText(String title, String type, String description) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.principalExtension = "png";
        this.thumbnailExtension = "jpg";
    }
}