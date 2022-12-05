package com.bodyhealth;

import com.bodyhealth.model.Administrador;
import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Entrenador;
import com.bodyhealth.model.Usuario;
import com.bodyhealth.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Access;

@SpringBootApplication
public class BodyhealthApplication {
	public static void main(String[] args) {
		SpringApplication.run(BodyhealthApplication.class, args);
	}
}
