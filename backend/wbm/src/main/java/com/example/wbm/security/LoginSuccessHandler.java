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
	private static final String ROLE_USUARIO = "ROLE_USUARIO";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws ServletException, IOException {

		String contextPath = request.getContextPath();
		String targetUrl;

		boolean isAdmin = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.anyMatch(authority -> authority.equals(ROLE_ADMIN));

		boolean isUser = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.anyMatch(authority -> authority.equals(ROLE_USUARIO));

		if (isAdmin) {
			targetUrl = contextPath + "/administrador";
		} else if (isUser) {
			targetUrl = contextPath + "/inicio";
		} else {
			targetUrl = contextPath + "/ingresar?error=rol_no_asignado";
		}

		// Redirección final
		response.sendRedirect(targetUrl);
	}
}
