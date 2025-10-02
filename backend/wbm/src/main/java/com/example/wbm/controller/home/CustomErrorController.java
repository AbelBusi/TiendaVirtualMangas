package com.example.wbm.controller.home;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Obtiene el código de estado del error
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                // Si el error es 404, añade la ruta al modelo y devuelve la vista 404
                String path = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
                model.addAttribute("path", path);
                return "error/404"; // Apunta a src/main/resources/templates/error/404.html
            }
            // Si el error es 500, puedes redirigir a una vista 500
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500";
            }
        }
        // Para cualquier otro error no especificado
        return "error/error";
    }

    // Spring Boot a partir de la versión 2.3 ya no requiere el método getErrorPath()
}