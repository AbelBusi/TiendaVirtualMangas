package com.example.wbm.model.mapStructure;

import com.example.wbm.model.dto.LibroDTO;
import com.example.wbm.model.entity.Libro;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ILibroMapper {

    @Mappings({
            @Mapping(source = "idLibro", target = "idLibro"),
            @Mapping(source = "titulo", target = "titulo"),
            @Mapping(source = "autor", target = "autor"),
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "fechaPublicacion", target = "fechaPublicacion"),
            @Mapping(source = "editorial", target = "editorial"),
            @Mapping(source = "genero", target = "genero"),

            @Mapping(source = "tipoLibro.idTipoLibro", target = "tipoLibro"),

            @Mapping(target = "nombreTipoLibro",
                    expression = "java(libro.getTipoLibro() != null ? libro.getTipoLibro().getNombre() : \"Sin Categoría\")"),

            @Mapping(source = "numPaginas", target = "numPaginas"),
            @Mapping(source = "isbn", target = "isbn"),
            @Mapping(source = "idioma", target = "idioma"),
            @Mapping(source = "portadaUrl", target = "portadaUrl"),
            @Mapping(source = "precio", target = "precio"),
            @Mapping(source = "estado", target = "estado")
    })
    LibroDTO toDto(Libro libro);

    @InheritInverseConfiguration
    @Mapping(target = "tipoLibro", ignore = true)
    Libro toEntity(LibroDTO libroDTO);
}