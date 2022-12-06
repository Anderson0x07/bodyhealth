package com.bodyhealth.controller;

import com.bodyhealth.model.Detalle;
import com.bodyhealth.model.Proveedor;
import com.bodyhealth.service.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class DetalleController {
    @Autowired
    private DetalleService detalleService;
    private String error = "";

    @GetMapping("/dash-planes")
    public String listarPlanesDetallados(Model model){
        List<Detalle> planesDeta = detalleService.listarDetalles();
        model.addAttribute("planesDeta",planesDeta);
        model.addAttribute("error",error);
        error="";
        return "admin/planes/dash-planes";
    }

    //Guarda plan detallado
    @PostMapping("/dash-planes/guardar")
    public String guardarPlanDetallado(Detalle detalle){

        List<Detalle> planes = detalleService.listarDetalles();

        if(planes.size()>0){
            for (Detalle plan: planes) {
                if(detalle.getPlan().equalsIgnoreCase(plan.getPlan()) && detalle.getMeses()==plan.getMeses()){
                    error = "Error al guardar plan, ya existe";
                    return "redirect:/admin/dash-planes";
                }
            }
        }

        detalleService.guardar(detalle);
        error="";

        return "redirect:/admin/dash-planes";
    }

    @GetMapping("/dash-planes/expand/{id_detalle}")
    public String mostrarPlan(Detalle detalle, Model model){

        detalle = detalleService.encontrarDetalle(detalle);

        model.addAttribute("detalle",detalle);
        model.addAttribute("error",error);
        error="";

        return "admin/planes/plan-expand";
    }

    //Guarda edición de plan en el dashboard del admin
    @PostMapping("/dash-planes/expand/guardar")
    public String guardarEdicionPlan(Detalle detalle){

        List<Detalle> planes = detalleService.listarDetalles();

        if(planes.size()>0){
            for (Detalle plan: planes) {
                if(detalle.getPlan().equalsIgnoreCase(plan.getPlan()) && detalle.getMeses()==plan.getMeses()){
                    error = "Error al guardar el plan, ya existe";
                    return "redirect:/admin/dash-planes/expand/editar/"+detalle.getId_detalle();
                }
            }
        }

        detalleService.guardar(detalle);
        error="";

        return "redirect:/admin/dash-planes/expand/"+detalle.getId_detalle();
    }


    @GetMapping("/dash-planes/expand/editar/{id_detalle}")
    public String editar(Detalle detalle, Model model){
        detalle = detalleService.encontrarDetalle(detalle);

        model.addAttribute("detalle",detalle);
        model.addAttribute("error",error);
        error="";

        return "admin/planes/plan-editar";
    }

    @GetMapping("/dash-planes/eliminar")
    public String eliminarPlan(Detalle detalle){
        error="";
        detalleService.eliminar(detalle);
        return "redirect:/admin/dash-planes";
    }
}
