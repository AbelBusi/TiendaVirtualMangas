package com.example.wbm.security;

import com.example.wbm.implementation.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfigUser {

	@Autowired
	private LoginSuccessHandler loginSuccessHandler;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
				.csrf(csrf -> csrf.disable()) // Dejado si es estrictamente necesario, pero se recomienda no deshabilitarlo
				.authorizeHttpRequests((requests) -> requests
						// Permisos públicos
						.requestMatchers("/assets/**", "/css/**", "/js/**", "/media/**").permitAll()
						.requestMatchers("/ingresar", "/ingresar/**", "/inicio/**","/tienda","/tienda/**","/revista","/revista/**"
						,"/contactanos","/contactanos/**").permitAll()

						// CORRECCIÓN CLAVE: Se usa "ROLE_ADMINISTRADOR" que es el que se crea en CustomUserDetails
						.requestMatchers("/administrador/**").hasAuthority("ROLE_ADMINISTRADOR")

						.anyRequest().authenticated()
				)
				.formLogin( (form )->form
						.loginPage("/ingresar")
						.usernameParameter("correo")
						.passwordParameter("password")
						.successHandler(loginSuccessHandler)
						.permitAll()
				)
				.logout((logout) -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/ingresar?logout")
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.permitAll()
				);

		return http.build();
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth ) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}
}