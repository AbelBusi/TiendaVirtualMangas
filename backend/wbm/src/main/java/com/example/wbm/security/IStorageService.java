package com.example.wbm.security;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;

public interface IStorageService {

    /**
     * @param file El archivo a guardar.
     * @param targetDirectory La subcarpeta de destino (ej: "iconos", "slider", "sliderTomo").
     * @param fileNamePrefix El prefijo del nombre final del archivo (ej: "logo", "carrusel-1").
     * @return El nombre del archivo guardado (ej: "logo.png" o "carrusel-1.jpg").
     */
    String store(MultipartFile file, String targetDirectory, String fileNamePrefix);
}
