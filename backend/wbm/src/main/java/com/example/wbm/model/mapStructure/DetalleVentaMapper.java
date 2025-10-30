package com.example.wbm.model.mapStructure;

import com.example.wbm.model.dto.DetalleVentaDTO;
import com.example.wbm.model.entity.DetalleVenta;
import com.example.wbm.model.entity.TipoLibro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleVentaMapper {

    @Mapping(source = "venta.idVenta", target = "venta.idVenta")

    @Mapping(target = "venta.detalleVentas", ignore = true)
    DetalleVentaDTO toDto(DetalleVenta detalleVenta);

    DetalleVenta toEntity(DetalleVentaDTO detalleVentaDTO);

    // 👇 Métodos auxiliares para mapear TipoLibro ↔ Integer
    default Integer map(TipoLibro tipoLibro) {
        return tipoLibro != null ? tipoLibro.getIdTipoLibro() : null;
    }

    default TipoLibro map(Integer id) {
        if (id == null) return null;
        TipoLibro tipoLibro = new TipoLibro();
        tipoLibro.setIdTipoLibro(id);
        return tipoLibro;
    }
}

