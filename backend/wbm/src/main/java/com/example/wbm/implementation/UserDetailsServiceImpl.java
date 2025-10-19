package com.example.wbm.implementation;


import com.example.wbm.model.entity.Usuario;
import com.example.wbm.repository.IUsuarioRepository;
import com.example.wbm.security.CustomUserDetails;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByCorreoWithPerfiles(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

		// Obtener la sesión
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		session.setAttribute("usuarioSesion", usuario);
		session.setMaxInactiveInterval(300);

		// Logs
		logger.info("Usuario id: {}", usuario.getIdUsuario());
		logger.info("Correo: {}", usuario.getCorreo());
		logger.info("Password hash BD: {}", usuario.getPassword());
		usuario.getPerfiles().forEach(perfil ->
				logger.info("Rol: {}", perfil.getRol().getNombre())
		);

		return new CustomUserDetails(usuario);
	}


	

}
