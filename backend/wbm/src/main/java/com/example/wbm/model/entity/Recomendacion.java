package com.example.wbm.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recomendacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "thumbnail_url", nullable = false)
    private String thumbnailUrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "button_url", nullable = false)
    private String buttonUrl;
}
