package com.example.wbm.security; // Ajusta el paquete según tu proyecto

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String STORE_HANDLER_PATH = "/media/images/store/**";
    private static final String STORE_UPLOAD_DIR = "file:./uploads/images/store/";

    private static final String ICONOS_HANDLER_PATH = "/media/images/iconos/**";
    private static final String ICONOS_UPLOAD_DIR = "file:./uploads/images/iconos/";

    private static final String SLIDER_HANDLER_PATH = "/media/images/slider/**";
    private static final String SLIDER_UPLOAD_DIR = "file:./uploads/images/slider/";

    private static final String SLIDER_THUMB_HANDLER_PATH = "/media/images/sliderTomo/**";
    private static final String SLIDER_THUMB_UPLOAD_DIR = "file:./uploads/images/sliderTomo/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(STORE_HANDLER_PATH)
                .addResourceLocations(STORE_UPLOAD_DIR);

        registry.addResourceHandler(ICONOS_HANDLER_PATH)
                .addResourceLocations(ICONOS_UPLOAD_DIR);

        registry.addResourceHandler(SLIDER_HANDLER_PATH)
                .addResourceLocations(SLIDER_UPLOAD_DIR);

        registry.addResourceHandler(SLIDER_THUMB_HANDLER_PATH)
                .addResourceLocations(SLIDER_THUMB_UPLOAD_DIR);

        // Respaldo general para cualquier imagen dentro de /uploads/images/
        registry.addResourceHandler("/media/images/**")
                .addResourceLocations("file:./uploads/images/");
    }
}