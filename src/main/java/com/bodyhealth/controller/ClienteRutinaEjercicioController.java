package com.bodyhealth.controller;

import com.bodyhealth.model.ClienteRutinaEjercicio;
import com.bodyhealth.service.ClienteRutinaEjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clienteRutinaC")
public class ClienteRutinaEjercicioController {
    @Autowired
    private ClienteRutinaEjercicioService clienteRutinaEjercicioService;
    @GetMapping("/lista")
    public List<ClienteRutinaEjercicio> listarClienteRutinas(Model model){
        List<ClienteRutinaEjercicio> clientesRutinasEjercicio = clienteRutinaEjercicioService.listarClientesRutinaEjercicios();

        model.addAttribute("clientesRutinasEjercicio",clientesRutinasEjercicio);

        return clientesRutinasEjercicio;
    }
}
