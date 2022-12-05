package com.bodyhealth.repository;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Entrenador;
import com.bodyhealth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    //Usuario findByEmail(String email);

    @Query(
            value = "SELECT * from usuario u where u.rol = 'ADMIN' and u.email = :email",
            nativeQuery=true
    )
    Usuario findByRolAdmin(@Param("email") String email);

    @Query(
            value = "SELECT * from usuario u where u.rol = 'CLIENTE' and u.email = :email",
            nativeQuery=true
    )
    Usuario findByRolCliente(@Param("email") String email);

    @Query(
            value = "SELECT * from usuario u where u.rol = 'TRAINER' and u.email = :email",
            nativeQuery=true
    )
    Usuario findByRolTrainer(@Param("email") String email);


    @Query(
            value = "SELECT * from usuario u where u.rol = 'CLIENTE' and u.estado=1",
            nativeQuery = true
    )
    List<Cliente> findClientesActivos();
    @Query(
            value = "SELECT * from usuario u where u.rol = 'CLIENTE' and u.estado=0",
            nativeQuery = true
    )
    List<Cliente> findClientesDesactivados();

    @Query(
            value = "SELECT * from usuario u where u.rol = 'TRAINER' and u.estado=1",
            nativeQuery = true
    )
    List<Entrenador> findEntrenadoresActivos();
    @Query(
            value = "SELECT * from usuario u where u.rol = 'TRAINER' and u.estado=0",
            nativeQuery = true
    )
    List<Entrenador> findEntrenadoresDesactivados();


    @Query(
            value = "SELECT * from usuario u where u.rol = 'TRAINER' and u.estado=1 and u.jornada=:jornada",
            nativeQuery = true
    )
    List<Entrenador> entrenadoresJornada(@Param("jornada") String jornada);





}
