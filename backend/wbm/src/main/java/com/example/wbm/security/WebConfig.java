package com.example.wbm.security; // Asegúrate de usar tu paquete de configuración

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Esta ruta debe coincidir con el th:src de tu HTML: /media/images/store/**
    private static final String HANDLER_PATH = "/media/images/store/**";

    // Esta ruta debe coincidir con la UPLOAD_DIR de tu servicio (anteponiendo "file:./")
    // Esto apunta a la carpeta 'uploads/images/store' en la raíz de tu proyecto
    private static final String UPLOAD_DIR_FS_LOCATION = "file:./uploads/images/store/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mapea la URL pública a la ubicación física de los archivos
        registry.addResourceHandler(HANDLER_PATH)
                .addResourceLocations(UPLOAD_DIR_FS_LOCATION);
    }
}