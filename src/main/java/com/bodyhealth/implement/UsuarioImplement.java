package com.bodyhealth.implement;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Entrenador;
import com.bodyhealth.model.Usuario;
import com.bodyhealth.repository.UsuarioRepository;
import com.bodyhealth.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@Slf4j
public class UsuarioImplement implements UsuarioService, UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public Usuario encontrarUsuario(Usuario usuario) {
        return usuarioRepository.findById(usuario.getId_usuario()).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserDetails userDet = null;
        //SOLO ADMINS
        Usuario admin = usuarioRepository.findByRolAdmin(email);

        if(admin != null){
            log.info("Admin obtenido: "+admin);

            List<GrantedAuthority> rol = new ArrayList<>();
            rol.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            userDet = new User(admin.getEmail(), admin.getPassword(), rol);
        }
        else {
            //CLIENTES
            Usuario cliente = usuarioRepository.findByRolCliente(email);
            if(cliente != null){
                log.info("Cliente obtenido: "+cliente);

                List<GrantedAuthority> rol = new ArrayList<>();
                rol.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
                userDet = new User(cliente.getEmail(), cliente.getPassword(), rol);
            }
            else {
                //TRAINER
                Usuario trainer = usuarioRepository.findByRolTrainer(email);
                if(trainer != null){
                    log.info("Trainer obtenido: "+trainer);

                    List<GrantedAuthority> rol = new ArrayList<>();
                    rol.add(new SimpleGrantedAuthority("ROLE_TRAINER"));
                    userDet = new User(trainer.getEmail(), trainer.getPassword(), rol);
                }
                else{
                    throw new UsernameNotFoundException(email);
                }
            }
        }


        return userDet;

    }



    @Override
    public List<Cliente> listarActivos() {

        List<Cliente> clientes = usuarioRepository.findClientesActivos();

        return clientes;

    }

    @Override
    public List<Cliente> listarDesactivados() {
        List<Cliente> clientes = usuarioRepository.findClientesDesactivados();

        return clientes;
    }

    @Override
    public List<Entrenador> listarEntrenadoresActivos() {
        List<Entrenador> entrenadores = usuarioRepository.findEntrenadoresActivos();
        return entrenadores;
    }

    @Override
    public List<Entrenador> listarEntrenadoresDesactivados() {
        List<Entrenador> entrenadores = usuarioRepository.findEntrenadoresDesactivados();
        return entrenadores;
    }
}
