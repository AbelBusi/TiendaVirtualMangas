package com.example.wbm.model.mapStructure;

import com.example.wbm.model.dto.DetalleVentaDTO;
import com.example.wbm.model.dto.VentaDTO;
import com.example.wbm.model.entity.DetalleVenta;
import com.example.wbm.model.entity.Venta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetalleVentaMapper {

    DetalleVentaDTO toDto (DetalleVenta detalleVenta);

    DetalleVenta toEntity(DetalleVentaDTO detalleVentaDTO);

}
