package com.example.wbm.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "venta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @ManyToOne
    @JoinColumn(name = "usuario",nullable = false)
    private Usuario usuario;

    @Column(name = "sub_total")
    private Double subTotal;

    @Column(name = "igv")
    private Double igv;

    @Column(name = "total")
    private Double total;

    @CreationTimestamp
    @Column(name = "fecha_venta",nullable = false)
    private LocalDateTime fechaVenta;

    @Column(name = "numero_venta",unique = true,length = 100,nullable = false)
    private String numeroVenta;

    @Column(name = "estado")
    private Integer estado;

    @OneToMany(mappedBy = "venta",fetch = FetchType.LAZY)
    private List<DetalleVenta> detalleVentas;

}