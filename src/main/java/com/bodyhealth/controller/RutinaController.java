package com.bodyhealth.controller;

import com.bodyhealth.model.*;
import com.bodyhealth.service.*;
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
public class RutinaController {
    @Autowired
    private RutinaService rutinaService;
    @Autowired
    private RutinaEjercicioService rutinaEjercicioService;
    @Autowired
    private EjercicioService ejercicioService;
    @Autowired
    private MusculoService musculoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ClienteRutinaService clienteRutinaService;

    @GetMapping("/admin/dash-rutinas")
    public String listarRutinas(Model model){

        List<Rutina> rutinas = rutinaService.listarRutina();

        model.addAttribute("rutinas",rutinas);
        model.addAttribute("rutina_ejercicios",listarRutinaEjercicio());
        model.addAttribute("ejercicios",listarEjercicios());
        model.addAttribute("musculos", musculoService.listarMusculos());



        return "admin/rutinas/dash-rutinas";
    }

    public List<RutinaEjercicio> listarRutinaEjercicio(){
        return rutinaEjercicioService.listarRutinasEjercicios();
    }

    public List<Ejercicio> listarEjercicios(){
        return ejercicioService.listarEjercicios();
    }

    //DESDE ENTRENADOR MANEJO DE RUTINAS

    @GetMapping("/trainer/dash-rutinas")
    public String listarRutinasTrainer(Model model){
        List<Rutina> rutinas = rutinaService.listarRutina();

        model.addAttribute("rutinas",rutinas);
        model.addAttribute("rutina_ejercicios",listarRutinaEjercicio());
        model.addAttribute("ejercicios",listarEjercicios());
        model.addAttribute("musculos", musculoService.listarMusculos());



        return "trainer/rutinas/dash-rutinas";
    }

    @PostMapping("/trainer/dash-rutinas/guardar-rutina")
    public String guardarRutina(Rutina rutina){

        rutinaService.guardar(rutina);

        return "redirect:/trainer/dash-rutinas";
    }


    @PostMapping("/admin/dash-rutinas/guardar-rutina")
    public String guardarRutinaAd(Rutina rutina){

        rutinaService.guardar(rutina);

        return "redirect:/admin/dash-rutinas";
    }

    @PostMapping("/trainer/dash-rutinas/guardar-ejercicio")
    public String guardarEjercicio(Ejercicio ejercicio){

        log.info("EJer: "+ejercicio.toString());

        ejercicio.setId_musculo(ejercicio.getId_musculo());

        ejercicioService.guardar(ejercicio);

        return "redirect:/trainer/dash-rutinas";
    }
    @PostMapping("/admin/dash-rutinas/guardar-ejercicio")
    public String guardarEjercicioAd(Ejercicio ejercicio){

        ejercicio.setId_musculo(ejercicio.getId_musculo());

        ejercicioService.guardar(ejercicio);

        return "redirect:/admin/dash-rutinas";
    }
    @PostMapping("/trainer/dash-rutinas/guardar-rutina-ejercicio")
    public String editarRutina(RutinaEjercicio rutinaEjercicio){

        rutinaEjercicioService.guardar(rutinaEjercicio);

        return "redirect:/trainer/dash-rutinas";
    }
    @PostMapping("/admin/dash-rutinas/guardar-rutina-ejercicio")
    public String editarRutinaAd(RutinaEjercicio rutinaEjercicio){

        rutinaEjercicioService.guardar(rutinaEjercicio);

        return "redirect:/admin/dash-rutinas";
    }

    @GetMapping("/admin/dash-rutinas/eliminar/{id_rutina}")
    public String eliminarRutinasRevision(Model model, Rutina rutina){

        List<ClienteRutina> clientesRutina = clienteRutinaService.encontrarClientesRutina(rutina.getId_rutina());

        model.addAttribute("clientesRutina", clientesRutina);

        return "admin/rutinas/eliminar-rutina";
    }
    @GetMapping("/admin/dash-rutinas/eliminar-rutina/{id_rutina}")
    public String eliminarRutina(Rutina rutina){
        rutinaService.eliminar(rutina);
        return "redirect:/admin/dash-rutinas";
    }


    @GetMapping("/admin/dash-ejercicio/eliminar/{id_ejercicio}")
    public String eliminarEjercicios(Ejercicio ejercicio){
        ejercicioService.eliminar(ejercicio);
        return "redirect:/admin/dash-rutinas";
    }
    @GetMapping("/admin/dash-rutina-ejercicio/eliminar/{id_rutina_ejercicio}")
    public String eliminarRutinaEjercicio(RutinaEjercicio rutinaEjercicio){
        rutinaEjercicioService.eliminar(rutinaEjercicio);
        return "redirect:/admin/dash-rutinas";
    }


}
