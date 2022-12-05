package com.bodyhealth.controller;

import com.bodyhealth.model.ControlCliente;
import com.bodyhealth.service.ControlClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controlCliente")
public class ControlClienteController {
    @Autowired
    private ControlClienteService controlClienteService;
    @GetMapping("/lista")
    public List<ControlCliente> listarRutinas(Model model){
        List<ControlCliente> controlClientes = controlClienteService.listarContolClientes();

        model.addAttribute("controlClientes",controlClientes);

        return controlClientes;
    }
}
