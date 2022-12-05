package com.bodyhealth.controller;

import com.bodyhealth.model.Ejercicio;
import com.bodyhealth.model.Musculo;
import com.bodyhealth.service.EjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ejercicio")
public class EjercicioController {
    @Autowired
    private EjercicioService ejercicioService;
    @GetMapping("/lista")
    public List<Ejercicio> listarEjercicios(Model model){
        List<Ejercicio> ejercicios = ejercicioService.listarEjercicios();

        model.addAttribute("ejercicios",ejercicios);

        return ejercicios;
    }
}
