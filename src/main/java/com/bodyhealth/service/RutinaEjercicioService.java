package com.bodyhealth.service;

import com.bodyhealth.model.RutinaEjercicio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RutinaEjercicioService {
    public List<RutinaEjercicio> listarRutinasEjercicios();

    public void guardar(RutinaEjercicio rutinaEjercicio);

    public void eliminar(RutinaEjercicio rutinaEjercicio);

    public RutinaEjercicio encontrarRutinaEjercicio(RutinaEjercicio rutinaEjercicio);
}
