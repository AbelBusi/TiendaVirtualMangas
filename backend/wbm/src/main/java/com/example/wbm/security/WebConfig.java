package com.example.wbm.security; // Asegúrate de usar tu paquete de configuración

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
        registry.addResourceHandler("/media/images/store/**")
                .addResourceLocations("file:./uploads/images/store/");

        registry.addResourceHandler("/media/images/iconos/**")
                .addResourceLocations("file:./uploads/images/iconos/");

        registry.addResourceHandler("/media/images/slider/**")
                .addResourceLocations("file:./uploads/images/slider/");

        registry.addResourceHandler("/media/images/sliderTomo/**")
                .addResourceLocations("file:./uploads/images/sliderTomo/");

        // Este último es opcional (sirve como respaldo general)
        registry.addResourceHandler("/media/images/**")
                .addResourceLocations("file:./uploads/images/");
    }

}