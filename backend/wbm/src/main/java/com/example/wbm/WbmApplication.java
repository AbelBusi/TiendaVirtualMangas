package com.example.wbm;

import com.example.wbm.model.entity.Usuario;
import com.example.wbm.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class WbmApplication {

	public static void main(String[] args) {
		SpringApplication.run(WbmApplication.class, args);

	}


	@Bean
	public CommandLineRunner verificarConexion(DataSource dataSource) {
		return args -> {
			System.out.println("--VERIFICANDO CCONEXION A LA BASE DE DATOS--");
			try (Connection connection = dataSource.getConnection()) {

				System.out.println("CONEXION A LA BASE DE DATOS EXITOSA");
			} catch (SQLException e) {
				System.out.println("ERROR: FALLO AL CONECTAR A LA BASE DE DATOS: " + e.getMessage());
			}
			System.out.println("--VERIFICACION FINALIZADA--");
		};
	}


}
