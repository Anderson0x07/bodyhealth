package com.bodyhealth.controller;

import com.bodyhealth.model.Rutina;
import com.bodyhealth.model.RutinaEjercicio;
import com.bodyhealth.service.RutinaEjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rutinaE")
public class RutinaEjercicioController {
    @Autowired
    private RutinaEjercicioService rutinaEjercicioService;
    @GetMapping("/lista")
    public List<RutinaEjercicio> listarRutinasEjercicio(Model model){
        List<RutinaEjercicio> rutinasEjercicios = rutinaEjercicioService.listarRutinasEjercicios();

        model.addAttribute("rutinasEjercicios",rutinasEjercicios);

        return rutinasEjercicios;
    }
}
