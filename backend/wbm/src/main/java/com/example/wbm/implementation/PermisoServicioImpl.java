package com.example.wbm.implementation;

import com.example.wbm.model.dto.PermisoDTO;
import com.example.wbm.model.entity.Permiso;
import com.example.wbm.model.mapStructure.IPermisoMapper;
import com.example.wbm.repository.IPermisoRepository;
import com.example.wbm.services.IPermisoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PermisoServicioImpl implements IPermisoServicio {

    private final IPermisoRepository permisoRepository;
    private final IPermisoMapper permisoMapper;

    @Transactional(readOnly = true)
    @Override
    public List<PermisoDTO> leerPermisos() {

        List<Permiso> permisos = permisoRepository.findAll();

        if (permisos.size()==0){
            return Collections.emptyList();
        }
        List<PermisoDTO> permisoDTOS= permisos.stream()
                .map(permisoMapper::toDto)
                .toList();

        return permisoDTOS;
    }
}
