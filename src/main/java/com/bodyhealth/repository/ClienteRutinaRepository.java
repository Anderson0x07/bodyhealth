package com.bodyhealth.repository;

import com.bodyhealth.model.ClienteDetalle;
import com.bodyhealth.model.ClienteRutina;
import com.bodyhealth.model.ClienteRutinaEjercicio;
import com.bodyhealth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRutinaRepository extends JpaRepository<ClienteRutina,Integer> {
    @Query(
            value = "SELECT * FROM cliente_rutina cr where cr.id_cliente = :id_cliente",
            nativeQuery=true
    )
    ClienteRutina encontrarRutina(@Param("id_cliente") int id_cliente);

    @Query(
            value = "SELECT * FROM cliente_rutina cr where cr.id_rutina = :id_rutina",
            nativeQuery=true
    )
    List<ClienteRutina> encontrarClientesRutina(@Param("id_rutina") int id_rutina);
}
