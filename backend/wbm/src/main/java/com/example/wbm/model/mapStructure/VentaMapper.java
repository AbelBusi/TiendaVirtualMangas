package com.example.wbm.model.mapStructure;

import com.example.wbm.model.dto.UsuarioDTO;
import com.example.wbm.model.dto.VentaDTO;
import com.example.wbm.model.entity.Usuario;
import com.example.wbm.model.entity.Venta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VentaMapper {

    VentaDTO toDto (Venta venta);

    Venta toEntity(VentaDTO ventaDTO);

}
