package com.bodyhealth.service;

import com.bodyhealth.model.Ejercicio;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EjercicioService {
    public List<Ejercicio> listarEjercicios();

    public void guardar(Ejercicio ejercicio);

    public void eliminar(Ejercicio ejercicio);

    public Ejercicio encontrarEjercicio(Ejercicio ejercicio);
}
