package com.example.wbm.security;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageService implements IStorageService {

    // Directorio base donde se guardarán todas las subidas
    private final Path rootLocation = Paths.get("uploads/images");

    @Override
    public String store(MultipartFile file, String targetDirectory, String fileNamePrefix) {
        if (file.isEmpty()) {
            throw new RuntimeException("Fallo al almacenar un archivo vacío.");
        }

        try {
            // 1. Determinar la ubicación final: uploads/images/{targetDirectory}
            Path directoryPath = this.rootLocation.resolve(targetDirectory);
            Files.createDirectories(directoryPath); // Crea la carpeta si no existe

            // 2. Obtener la extensión original del archivo
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.lastIndexOf(".") != -1) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            // 3. Crear el nuevo nombre del archivo (ej: logo.png, carrusel-1.jpg)
            String newFileName = fileNamePrefix + fileExtension;
            Path destinationFile = directoryPath.resolve(newFileName);

            // 4. Copiar y reemplazar el archivo
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            // Devolvemos el nombre del archivo para referencia
            return newFileName;

        } catch (IOException e) {
            throw new RuntimeException("Fallo al almacenar el archivo: " + file.getOriginalFilename(), e);
        }
    }
}