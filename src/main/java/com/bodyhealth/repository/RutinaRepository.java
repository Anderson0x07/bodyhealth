package com.bodyhealth.repository;

import com.bodyhealth.model.Rutina;
import com.bodyhealth.model.RutinaEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RutinaRepository extends JpaRepository<Rutina,Integer> {

    /* NO NECESARIO
    @Query(
            value = "SELECT * FROM rutina r where r.id_rutina = :id_rutina",
            nativeQuery=true
    )
    Rutina encontrarRutinaDeCliente(@Param("id_rutina") int id_rutina);*/
}
