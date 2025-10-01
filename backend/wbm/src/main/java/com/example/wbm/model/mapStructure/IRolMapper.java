package com.example.wbm.model.mapStructure;

import com.example.wbm.model.dto.RolDTO;
import com.example.wbm.model.entity.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IRolMapper {

    RolDTO toDto (Rol rol);

    Rol toEntity(RolDTO rolDTO);

}
