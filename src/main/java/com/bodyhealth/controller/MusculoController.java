package com.bodyhealth.controller;

import com.bodyhealth.model.Maquina;
import com.bodyhealth.model.Musculo;
import com.bodyhealth.service.MusculoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/trainer")
public class MusculoController {
    @Autowired
    private MusculoService musculoService;
    @GetMapping("/dash-musculo")
    public String listarMusculos(Model model){
        List<Musculo> musculos = musculoService.listarMusculos();

        model.addAttribute("musculos",musculos);

        return "/trainer/rutinas/dash-rutinas";
    }

    public String guardarMusculo(Musculo musculo){

        log.info("Musculo: "+musculo.toString());


        musculoService.guardar(musculo);

        return "redirect:/trainer/dash-musculos";
    }
    @GetMapping("/dash-maquinas/expand/editar/{id_maquina}")
    public String editar(Musculo musculo, Model model){

        musculo = musculoService.encontrarMusculo(musculo);

        model.addAttribute("musculo",musculo);


        return "/trainer/rutinas/dash-rutinas";
    }

    @GetMapping("/dash-maquinas/eliminar")
    public String eliminarMusculo(Musculo musculo){
        musculoService.eliminar(musculo);
        return "redirect:/trainer/rutinas/dash-rutinas";
    }
}
