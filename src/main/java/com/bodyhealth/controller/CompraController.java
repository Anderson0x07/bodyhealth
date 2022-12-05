package com.bodyhealth.controller;

import com.bodyhealth.model.Compra;
import com.bodyhealth.model.Maquina;
import com.bodyhealth.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/compra")
public class CompraController {
    @Autowired
    private CompraService compraService;
    @GetMapping("/lista")
    public List<Compra> listarRutinas(Model model){
        List<Compra> compras = compraService.listarCompras();

        model.addAttribute("compras",compras);

        return compras;
    }
}
