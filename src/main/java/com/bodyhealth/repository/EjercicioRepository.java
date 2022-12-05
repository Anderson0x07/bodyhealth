package com.bodyhealth.repository;

import com.bodyhealth.model.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjercicioRepository extends JpaRepository<Ejercicio,Integer> {
}
