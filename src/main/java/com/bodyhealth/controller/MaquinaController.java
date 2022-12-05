package com.bodyhealth.controller;

import com.bodyhealth.model.Detalle;
import com.bodyhealth.model.Maquina;
import com.bodyhealth.service.DetalleService;
import com.bodyhealth.service.MaquinaService;
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
public class MaquinaController {
    @Autowired
    private MaquinaService maquinaService;
    @Autowired
    private ProveedorService proveedorService;
    private String error = "";
    @GetMapping("/admin/dash-maquinas")
    public String listarMaquinas(Model model){
        List<Maquina> maquinas = maquinaService.listarMaquinas();

        model.addAttribute("maquinas",maquinas);
        model.addAttribute("proveedores",proveedorService.listarProveedores());
        model.addAttribute("error",error);
        error = "";

        return "admin/maquinas/dash-maquinas";
    }

    //Guarda nueva maquina
    @PostMapping("/admin/dash-maquinas/guardar")
    public String guardarNuevaMaquina(Maquina maquina){

        List<Maquina> maquinas = maquinaService.listarMaquinas();
        if(maquinas.size()>0) {
            for (Maquina maq : maquinas) {
                if (maquina.getId_maquina() == maq.getId_maquina()) {
                    error = "Error al guardar la maquina, ya existe";
                    return "redirect:/admin/dash-maquinas";
                }
            }
        }
        maquinaService.guardar(maquina);

        error = "";
        return "redirect:/admin/dash-maquinas";
    }

    @GetMapping("/admin/dash-maquinas/expand/{id_maquina}")
    public String expandMaquina(Maquina maquina, Model model){

        maquina = maquinaService.encontrarMaquina(maquina);

        model.addAttribute("maquina",maquina);

        return "admin/maquinas/maquina-expand";
    }

    //Guarda edición de maquina en el dashboard del admin
    @PostMapping("/admin/dash-maquinas/expand/guardar")
    public String guardarEdicionMaquina(Maquina maquina) {

        log.info("Maquina: "+maquina.toString());

        List<Maquina> maquinas = maquinaService.listarMaquinas();

        if(maquinas.size()>0) {
            for (Maquina maq : maquinas) {
                if (maquina.getId_maquina() == maq.getId_maquina()) {
                    error = "Error al guardar la maquina, ya existe";
                    return "redirect:/admin/dash-maquinas/expand/editar/" + maquina.getId();
                }

            }
        }

        maquinaService.guardar(maquina);

        error = "";
        return "redirect:/admin/dash-maquinas/expand/"+maquina.getId();
    }


    @GetMapping("/admin/dash-maquinas/expand/editar/{id_maquina}")
    public String editar(Maquina maquina, Model model){

        maquina = maquinaService.encontrarMaquina(maquina);

        model.addAttribute("maquina",maquina);
        model.addAttribute("proveedores",proveedorService.listarProveedores());
        model.addAttribute("error",error);
        error="";

        return "admin/maquinas/maquina-editar";
    }

    @GetMapping("/admin/dash-maquinas/eliminar")
    public String eliminarMaquina(Maquina maquina){
        error = "";
        maquinaService.eliminar(maquina);
        return "redirect:/admin/dash-maquinas";
    }
}
