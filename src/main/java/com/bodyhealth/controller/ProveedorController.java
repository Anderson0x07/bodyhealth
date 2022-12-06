package com.bodyhealth.controller;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.ClienteDetalle;
import com.bodyhealth.model.Proveedor;
import com.bodyhealth.service.ProveedorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;
    @GetMapping("/dash-proveedores")
    public String listarProveedores(Model model, Proveedor proveedor){
        List<Proveedor> proveedores = proveedorService.listarProveedores();

        model.addAttribute("proveedores",proveedores);

        return "admin/proveedores/dash-proveedores";
    }

    //Guarda proveedor
    @PostMapping("/dash-proveedores/guardar")
    public String guardarProveedor(Proveedor proveedor){

        proveedorService.guardar(proveedor);

        return "redirect:/admin/dash-proveedores";
    }

    @GetMapping("/dash-proveedores/expand/{id_proveedor}")
    public String mostrarCliente(Proveedor proveedor, Model model){

        proveedor = proveedorService.encontrarProveedor(proveedor);

        model.addAttribute("proveedor",proveedor);

        return "admin/proveedores/proveedor-expand";
    }

    //Guarda edición proveedores en el dashboard del admin
    @PostMapping("/dash-proveedores/expand/guardar")
    public String guardarEdicionProveedor(Proveedor proveedor){

        proveedorService.guardar(proveedor);

        return "redirect:/admin/dash-proveedores/expand/"+proveedor.getId_proveedor();
    }


    @GetMapping("/dash-proveedores/expand/editar/{id_proveedor}")
    public String editar(Proveedor proveedor, Model model){

        proveedor = proveedorService.encontrarProveedor(proveedor);

        model.addAttribute("proveedor",proveedor);

        return "admin/proveedores/proveedor-editar";
    }

    @GetMapping("/dash-proveedores/eliminar")
    public String eliminarProveedor(Proveedor proveedor){
        proveedorService.eliminar(proveedor);
        return "redirect:/admin/dash-proveedores";
    }


}
