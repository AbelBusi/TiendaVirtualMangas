package com.example.wbm.implementation;

import com.example.wbm.model.dto.FormResponseSuccessDTO;
import com.example.wbm.model.dto.RolDTO;
import com.example.wbm.model.entity.Rol;
import com.example.wbm.model.mapStructure.IRolMapper;
import com.example.wbm.repository.IRolRepository;
import com.example.wbm.services.IRolServicio;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolServicioImpl implements IRolServicio {

    private final IRolRepository rolRepository;
    private final IRolMapper rolMapper;

    @Transactional(readOnly = true)
    @Override
    public List<RolDTO> leerRoles() {

        List<Rol> roles = rolRepository.findAll();

        if (roles.size()==0){
            return Collections.emptyList();
        }
        List<RolDTO> rolesDto= roles.stream()
                .map(rolMapper::toDto)
                .toList();

        return rolesDto;
    }

    @Transactional
    @Override
    public FormResponseSuccessDTO guardarRol(RolDTO rolDTO) {

        Rol rol = rolMapper.toEntity(rolDTO);

        if(rolExistente(rol.getNombre())){
            return new FormResponseSuccessDTO("El rol ya existe",false);
        }
        rol.setEstado(1);
        rolRepository.save(rol);

        return new FormResponseSuccessDTO("El rol fue creado con exito",true );
    }

    @Override
    public FormResponseSuccessDTO editarRol(RolDTO rolDTO) {

        Optional<Rol> rol = rolRepository.findById(rolDTO.getIdRol());

        if(!rol.isPresent()){
            return new FormResponseSuccessDTO("No se puede guardar un rol que no existe. ERROR",false);
        }

        Rol guardarRol = rolMapper.toEntity(rolDTO);
        rolRepository.save(guardarRol);

        return new FormResponseSuccessDTO("Se modifico el rol de forma correcta",true);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean rolExistente(String rol) {

        Optional<Rol> rolVerificar =rolRepository.findByNombre(rol);

        if(rolVerificar.isPresent()){
            return true;
        }

        return false;
    }

    @Transactional
    @Override
    public FormResponseSuccessDTO cambiarEStadoRol(Integer idRol) {
        Optional<Rol> rolOpt = rolRepository.findById(idRol);

        if (!rolOpt.isPresent()) {
            return new FormResponseSuccessDTO("No se puede cambiar el estado del rol. ERROR", false);
        }

        Rol rol = rolOpt.get();
        if (rol.getEstado() == 1) {
            rol.setEstado(0);
            rolRepository.save(rol);
            return new FormResponseSuccessDTO("El estado del rol pasó a Inactivo", true);
        } else {
            rol.setEstado(1);
            rolRepository.save(rol);
            return new FormResponseSuccessDTO("El estado del rol pasó a Activo", true);
        }
    }


}
