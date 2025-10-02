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

            // Mapeo especial para la FK
            @Mapping(source = "tipoLibro.idTipoLibro", target = "tipoLibro"),

            // CORRECCIÓN APLICADA AQUÍ (Usando el nombre exacto del campo: nPaginas)
            @Mapping(source = "numPaginas", target = "numPaginas"),

            @Mapping(source = "isbn", target = "isbn"),
            @Mapping(source = "idioma", target = "idioma"),

            // CORRECCIÓN APLICADA AQUÍ (Usando el nombre exacto del campo: portadaUrl)
            @Mapping(source = "portadaUrl", target = "portadaUrl"),

            @Mapping(source = "precio", target = "precio"),
            @Mapping(source = "estado", target = "estado")
    })
    LibroDTO toDto(Libro libro);

    @InheritInverseConfiguration
    @Mapping(target = "tipoLibro", ignore = true)
    Libro toEntity(LibroDTO libroDTO);
}