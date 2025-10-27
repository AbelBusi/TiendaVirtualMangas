package com.example.wbm.model.mapStructure;

import com.example.wbm.model.dto.VentaDTO;
import com.example.wbm.model.entity.Venta;
import org.mapstruct.Mapper;
import com.example.wbm.model.entity.TipoLibro;

@Mapper(componentModel = "spring")
public interface VentaMapper {

    VentaDTO toDto(Venta venta);

    Venta toEntity(VentaDTO ventaDTO);

    // 👇 Métodos personalizados para mapear TipoLibro <-> Integer
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

