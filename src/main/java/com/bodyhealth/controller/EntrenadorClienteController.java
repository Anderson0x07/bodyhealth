package com.bodyhealth.controller;

import com.bodyhealth.model.EntrenadorCliente;
import com.bodyhealth.service.EntrenadorClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entrenadorC")
public class EntrenadorClienteController {
    @Autowired
    private EntrenadorClienteService entrenadorClienteService;
    @GetMapping("/lista")
    public List<EntrenadorCliente> listarTrainers(Model model){
        List<EntrenadorCliente> entrenadorClientes = entrenadorClienteService.listarEntrenadoresClientes();
        model.addAttribute("entrenadorClientes",entrenadorClientes);
        return entrenadorClientes;
    }
}
