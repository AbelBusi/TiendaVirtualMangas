package com.example.wbm.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private static final String ROLE_ADMIN = "ROLE_ADMINISTRADOR";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws ServletException, IOException {

		String targetUrl;

		boolean isAdmin = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.anyMatch(authority -> authority.equals(ROLE_ADMIN));

		if (isAdmin) {
			// Si es admin, redirige a la base del controlador /administrador
			targetUrl = "/administrador";
		} else {
			// Si es usuario, redirige a /inicio
			targetUrl = "/inicio";
		}

		// DEBEMOS FORZAR LA URL SIN DEJAR QUE EL PADRE LA MANEJE,
		// porque el contexto path está causando problemas.
		// Obtenemos el path base (/webStoreManga)
		String contextPath = request.getContextPath();

		// Concatenamos para obtener la URL final (/webStoreManga/administrador)
		String finalUrl = contextPath + targetUrl;

		// Y forzamos la redirección manualmente para evitar el doble prefijo
		response.sendRedirect(finalUrl);
	}
}