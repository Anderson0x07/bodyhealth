package com.bodyhealth.service;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Entrenador;
import com.bodyhealth.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    public List<Usuario> listarUsuarios();

    public void guardar(Usuario usuario);

    public void eliminar(Usuario usuario);

    public Usuario encontrarUsuario(Usuario usuario);


    List<Cliente> listarActivos();

    List<Cliente> listarDesactivados();

    List<Entrenador> listarEntrenadoresActivos();

    List<Entrenador> listarEntrenadoresDesactivados();
}
