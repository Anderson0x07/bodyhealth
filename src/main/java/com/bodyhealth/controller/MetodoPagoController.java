package com.bodyhealth.controller;

import com.bodyhealth.model.MetodoPago;
import com.bodyhealth.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("metodoPago")
public class MetodoPagoController {
    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping("/lista")
    public List<MetodoPago> listarMetodoPagos(){
        return metodoPagoService.listarMetodosPagos();
    }
}
