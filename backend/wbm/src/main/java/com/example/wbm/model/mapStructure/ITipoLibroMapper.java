package com.example.wbm.model.mapStructure;

import com.example.wbm.model.dto.RolDTO;
import com.example.wbm.model.dto.TipoLibroDTO;
import com.example.wbm.model.entity.Rol;
import com.example.wbm.model.entity.TipoLibro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITipoLibroMapper {

    TipoLibroDTO toDto (TipoLibro tipoLibro);

    TipoLibro toEntity(TipoLibroDTO tipoLibroDTO);

}
