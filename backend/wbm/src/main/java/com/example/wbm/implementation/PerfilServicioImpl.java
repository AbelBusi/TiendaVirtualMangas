package com.example.wbm.implementation;

import com.example.wbm.model.dto.CDPerfilDTO;
import com.example.wbm.model.dto.FormResponseSuccessDTO;
import com.example.wbm.model.entity.Perfil;
import com.example.wbm.model.entity.Permiso;
import com.example.wbm.model.entity.Rol;
import com.example.wbm.model.mapStructure.IPerfilMapper;
import com.example.wbm.repository.IPerfilRepository;
import com.example.wbm.repository.IPermisoRepository;
import com.example.wbm.repository.IRolRepository;
import com.example.wbm.services.IPerfilServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerfilServicioImpl implements IPerfilServicio {

    private final IPerfilRepository perfilRepository;
    private final IPerfilMapper perfilMapper;
    private final IRolRepository rolRepository;
    private final IPermisoRepository permisoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CDPerfilDTO> leerPerfiles() {
        List<Perfil> perfiles = perfilRepository.findAll();
        if (perfiles.isEmpty()) {
            return Collections.emptyList();
        }
        return perfiles.stream()
                .map(perfilMapper::toCDDto)
                .toList();
    }

    @Transactional
    @Override
    public FormResponseSuccessDTO crearPerfil(CDPerfilDTO cdPerfilDTO) {
        Perfil perfil = perfilMapper.toEntity(cdPerfilDTO);
        perfilRepository.save(perfil);
        return new FormResponseSuccessDTO("Perfil creado con éxito", true);
    }

    @Transactional
    @Override
    public FormResponseSuccessDTO editarPerfil(CDPerfilDTO cdPerfilDTO) {
        Optional<Perfil> perfilOpt = perfilRepository.findById(cdPerfilDTO.getIdPerfil());
        if (perfilOpt.isEmpty()) {
            return new FormResponseSuccessDTO("No se puede editar un perfil que no existe", false);
        }

        Perfil perfil = perfilOpt.get();

        Optional<Rol> rolOpt = rolRepository.findById(cdPerfilDTO.getIdRol());
        if (rolOpt.isEmpty()) {
            return new FormResponseSuccessDTO("Rol ID " + cdPerfilDTO.getIdRol() + " no encontrado", false);
        }
        perfil.setRol(rolOpt.get());

        Optional<Permiso> permisoOpt = permisoRepository.findById(cdPerfilDTO.getIdPermiso());
        if (permisoOpt.isEmpty()) {
            return new FormResponseSuccessDTO("Permiso ID " + cdPerfilDTO.getIdPermiso() + " no encontrado", false);
        }
        perfil.setPermiso(permisoOpt.get());

        perfil.setEstado(cdPerfilDTO.getEstado());


        perfilRepository.save(perfil);

        return new FormResponseSuccessDTO("Perfil editado correctamente", true);
    }

    @Transactional
    @Override
    public FormResponseSuccessDTO cambiarEstadoPerfil(Integer idPerfil) {
        Optional<Perfil> perfilOpt = perfilRepository.findById(idPerfil);
        if (perfilOpt.isEmpty()) {
            return new FormResponseSuccessDTO("No se puede cambiar el estado del perfil", false);
        }

        Perfil perfil = perfilOpt.get();
        perfil.setEstado(perfil.getEstado() == 1 ? 0 : 1);
        perfilRepository.save(perfil);

        return new FormResponseSuccessDTO("Estado del perfil actualizado correctamente", true);
    }

    @Transactional(readOnly = true)
    @Override
    public CDPerfilDTO leerPerfilPorId(Integer idPerfil) {
        Perfil perfil = perfilRepository.findById(idPerfil)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
        return perfilMapper.toCDDto(perfil);
    }
}
