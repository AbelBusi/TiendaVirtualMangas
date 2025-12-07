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

    private final Path rootLocation = Paths.get("uploads/images");

    @Override
    public String store(MultipartFile file, String targetDirectory, String fileNamePrefix) {
        if (file.isEmpty()) {
            throw new RuntimeException("Fallo al almacenar un archivo vacío.");
        }

        try {
            Path directoryPath = this.rootLocation.resolve(targetDirectory);
            Files.createDirectories(directoryPath); // Crea la carpeta si no existe

            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.lastIndexOf(".") != -1) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String newFileName = fileNamePrefix + fileExtension;
            Path destinationFile = directoryPath.resolve(newFileName);

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return newFileName;

        } catch (IOException e) {
            throw new RuntimeException("Fallo al almacenar el archivo: " + file.getOriginalFilename(), e);
        }
    }
}