package com.example.wbm.model.mapStructure;

import com.example.wbm.model.dto.VentaDTO;
import com.example.wbm.model.entity.Venta;
import com.example.wbm.model.entity.TipoLibro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VentaMapper {

    // Ignora los detalles para evitar LazyInitializationException
    @Mapping(target = "detalleVentas", ignore = true)
    VentaDTO toDto(Venta venta);

    Venta toEntity(VentaDTO ventaDTO);

    // Métodos personalizados (por si aún los usas)
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

