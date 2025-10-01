package com.example.wbm.model.mapStructure;

import com.example.wbm.model.dto.PermisoDTO;
import com.example.wbm.model.dto.RolDTO;
import com.example.wbm.model.entity.Permiso;
import com.example.wbm.model.entity.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IPermisoMapper {

    PermisoDTO toDto (Permiso permiso);

    Permiso toEntity(PermisoDTO permisoDTO);

}
