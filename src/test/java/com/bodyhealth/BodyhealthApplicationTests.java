package com.bodyhealth;

import com.bodyhealth.model.Administrador;
import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Entrenador;
import com.bodyhealth.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BodyhealthApplicationTests {


	@Autowired
	private UsuarioRepository usuarioRepository;


	@Autowired
	private BCryptPasswordEncoder encoder;
	@Test
	public void crearClienteTest() {
		Cliente cliente = new Cliente();

		cliente.setId_usuario(1);
		cliente.setDocumento(1111);
		cliente.setEmail("cliente@prueba.com");
		cliente.setPassword(encoder.encode("1234"));
		cliente.setFoto("Foto cliente");
		cliente.setComentario("Este es un comentario de cliente");

		Cliente retorno = usuarioRepository.save(cliente);

		assertTrue(retorno.getPassword().equalsIgnoreCase(cliente.getPassword()));
	}
	@Test
	public void crearAdminTest() {
		Administrador admin = new Administrador();
		admin.setId_usuario(2);
		admin.setDocumento(1004926016);
		admin.setEmail("admin@prueba.com");
		admin.setPassword(encoder.encode("1234"));

		Administrador ret = usuarioRepository.save(admin);

		assertTrue(ret.getPassword().equalsIgnoreCase(admin.getPassword()));
	}

	@Test
	public void crearTrainerTest() {
		Entrenador trainer = new Entrenador();
		trainer.setId_usuario(3);
		trainer.setDocumento(333);
		trainer.setEmail("trainer@prueba.com");
		trainer.setPassword(encoder.encode("1234"));
		trainer.setFoto("Foto trainer");
		trainer.setExperiencia("24 Meses");
		trainer.setHoja_vida("Hoja de vida trainer");

		Entrenador ret = usuarioRepository.save(trainer);

		assertTrue(ret.getPassword().equalsIgnoreCase(trainer.getPassword()));
	}



}
