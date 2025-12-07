package com.example.wbm.implementation;

import com.example.wbm.model.dto.FormResponseSuccessDTO;
import com.example.wbm.model.dto.TipoLibroDTO;
import com.example.wbm.model.entity.TipoLibro;
import com.example.wbm.model.mapStructure.ITipoLibroMapper;
import com.example.wbm.repository.ITipoLibroRepository;
import com.example.wbm.services.ITipoLibroServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoLibroServicioImpl implements ITipoLibroServicio {

    private final ITipoLibroRepository tipoLibroRepository;
    private final ITipoLibroMapper tipoLibroMapper;

    @Transactional(readOnly = true)
    @Override
    public List<TipoLibroDTO> leerTiposLibro() {
        List<TipoLibro> tipoLibros = tipoLibroRepository.findAll();

        if (tipoLibros.isEmpty()){
            return Collections.emptyList();
        }
        List<TipoLibroDTO> tipoLibroDTOS= tipoLibros.stream()
                .map(tipoLibroMapper::toDto)
                .toList();

        return tipoLibroDTOS;
    }

    @Transactional
    @Override
    public FormResponseSuccessDTO guardarTipoLibro(TipoLibroDTO tipoLibroDTO) {

        TipoLibro tipoLibro = tipoLibroMapper.toEntity(tipoLibroDTO);

        if(tipoLibroExistente(tipoLibro.getNombre())){
            return new FormResponseSuccessDTO("El tipo de libro ya existe",false);
        }
        tipoLibro.setEstado(1);
        tipoLibroRepository.save(tipoLibro);

        return new FormResponseSuccessDTO("El Tipo de Libro fue creado con exito",true );
    }

    @Transactional
    @Override
    public FormResponseSuccessDTO editarTipoLibro(TipoLibroDTO tipoLibroDTO) {

        Optional<TipoLibro> tipoLibroOpt = tipoLibroRepository.findById(tipoLibroDTO.getIdTipoLibro());

        if(!tipoLibroOpt.isPresent()){
            return new FormResponseSuccessDTO("No se puede guardar un Tipo de Libro que no existe. ERROR",false);
        }

        TipoLibro guardarTipoLibro = tipoLibroMapper.toEntity(tipoLibroDTO);
        tipoLibroRepository.save(guardarTipoLibro);

        return new FormResponseSuccessDTO("Se modificó el Tipo de Libro de forma correcta",true);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean tipoLibroExistente(String nombreTipoLibro) {
        Optional<TipoLibro> tipoLibroVerificar =tipoLibroRepository.findByNombre(nombreTipoLibro);

        return tipoLibroVerificar.isPresent();
    }

    @Transactional
    @Override
    public FormResponseSuccessDTO cambiarEStadoTipoLibro(Integer idTipoLibro) {
        Optional<TipoLibro> tipoLibroOpt = tipoLibroRepository.findById(idTipoLibro);

        if (!tipoLibroOpt.isPresent()) {
            return new FormResponseSuccessDTO("No se puede cambiar el estado del Tipo de Libro. ERROR", false);
        }

        TipoLibro tipoLibro = tipoLibroOpt.get();
        if (tipoLibro.getEstado() == 1) {
            tipoLibro.setEstado(0);
            tipoLibroRepository.save(tipoLibro);
            return new FormResponseSuccessDTO("El estado del Tipo de Libro pasó a Inactivo", true);
        } else {
            tipoLibro.setEstado(1);
            tipoLibroRepository.save(tipoLibro);
            return new FormResponseSuccessDTO("El estado del Tipo de Libro pasó a Activo", true);
        }
    }
}