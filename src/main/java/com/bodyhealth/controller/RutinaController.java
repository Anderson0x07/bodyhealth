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

    private String msj1 = "";
    private String msj2 = "";
    private String msj3 = "";

    @GetMapping("/admin/dash-rutinas")
    public String listarRutinas(Model model){

        List<Rutina> rutinas = rutinaService.listarRutina();

        model.addAttribute("rutinas",rutinas);
        model.addAttribute("rutina_ejercicios",listarRutinaEjercicio());
        model.addAttribute("ejercicios",listarEjercicios());
        model.addAttribute("musculos", musculoService.listarMusculos());
        model.addAttribute("msj1",msj1);
        model.addAttribute("msj2",msj2);
        model.addAttribute("msj3",msj3);
        msj1="";
        msj2="";
        msj3="";



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
        model.addAttribute("msj1",msj1);
        model.addAttribute("msj2",msj2);
        model.addAttribute("msj3",msj3);
        msj1="";
        msj2="";
        msj3="";



        return "trainer/rutinas/dash-rutinas";
    }

    @PostMapping("/trainer/dash-rutinas/guardar-rutina")
    public String guardarRutina(Rutina rutina){

        List<Rutina> rutinas = rutinaService.listarRutina();

        if(rutinas.size()>0){
            for (Rutina rut: rutinas) {
                if(rutina.getNombre_rutina().equalsIgnoreCase(rut.getNombre_rutina())){
                    msj1 = "Error al guardar la rutina, ya existe";
                    return "redirect:/trainer/dash-rutinas";
                }
            }
        }

        rutinaService.guardar(rutina);

        msj1="Rutina registrada con éxito";

        return "redirect:/trainer/dash-rutinas";
    }


    @PostMapping("/admin/dash-rutinas/guardar-rutina")
    public String guardarRutinaAd(Rutina rutina){

        List<Rutina> rutinas = rutinaService.listarRutina();

        if(rutinas.size()>0){
            for (Rutina rut: rutinas) {
                if(rutina.getNombre_rutina().equalsIgnoreCase(rut.getNombre_rutina())){
                    msj1 = "Error al guardar la rutina, ya existe";
                    return "redirect:/admin/dash-rutinas";
                }
            }
        }

        rutinaService.guardar(rutina);

        msj1="Rutina registrada con exito";

        return "redirect:/admin/dash-rutinas";
    }

    @PostMapping("/trainer/dash-rutinas/guardar-ejercicio")
    public String guardarEjercicio(Ejercicio ejercicio){

        ejercicio.setId_musculo(ejercicio.getId_musculo());

        List<Ejercicio> ejercicios = ejercicioService.listarEjercicios();

        if(ejercicios.size()>0){
            for (Ejercicio eje: ejercicios) {
                if(ejercicio.getId_musculo().getId_musculo()==eje.getId_musculo().getId_musculo() && ejercicio.getDescripcion().equalsIgnoreCase(eje.getDescripcion())){
                    msj2 = "Error al guardar el ejercicio, ya existe";
                    return "redirect:/trainer/dash-rutinas";
                }
            }
        }

        ejercicioService.guardar(ejercicio);

        msj2="Ejercicio registrado con exito";

        return "redirect:/trainer/dash-rutinas";
    }
    @PostMapping("/admin/dash-rutinas/guardar-ejercicio")
    public String guardarEjercicioAd(Ejercicio ejercicio){

        ejercicio.setId_musculo(ejercicio.getId_musculo());

        List<Ejercicio> ejercicios = ejercicioService.listarEjercicios();

        if(ejercicios.size()>0){
            for (Ejercicio eje: ejercicios) {
                if(ejercicio.getId_musculo().getId_musculo()==eje.getId_musculo().getId_musculo() && ejercicio.getDescripcion().equalsIgnoreCase(eje.getDescripcion())){
                    msj2 = "Error al guardar el ejercicio, ya existe";
                    return "redirect:/admin/dash-rutinas";
                }
            }
        }

        ejercicioService.guardar(ejercicio);

        msj2="Ejercicio registrado con exito";

        return "redirect:/admin/dash-rutinas";
    }
    @PostMapping("/trainer/dash-rutinas/guardar-rutina-ejercicio")
    public String editarRutina(RutinaEjercicio rutinaEjercicio){

        List<RutinaEjercicio> rutinaEjercicios = rutinaEjercicioService.listarRutinasEjercicios();

        if(rutinaEjercicios.size()>0){
            for (RutinaEjercicio ruteje: rutinaEjercicios) {
                if(rutinaEjercicio.getId_rutina().equals(ruteje.getId_rutina()) && rutinaEjercicio.getId_ejercicio().equals(ruteje.getId_ejercicio())){
                    msj3 = "Error al guardar la rutina con ejercicio, ya existe";
                    return "redirect:/trainer/dash-rutinas";
                }
            }
        }

        rutinaEjercicioService.guardar(rutinaEjercicio);

        msj3="Ejercicio registrado con exito";

        return "redirect:/trainer/dash-rutinas";
    }
    @PostMapping("/admin/dash-rutinas/guardar-rutina-ejercicio")
    public String editarRutinaAd(RutinaEjercicio rutinaEjercicio){

        List<RutinaEjercicio> rutinaEjercicios = rutinaEjercicioService.listarRutinasEjercicios();

        if(rutinaEjercicios.size()>0){
            for (RutinaEjercicio ruteje: rutinaEjercicios) {
                if(rutinaEjercicio.getId_rutina().equals(ruteje.getId_rutina()) && rutinaEjercicio.getId_ejercicio().equals(ruteje.getId_ejercicio())){
                    msj3 = "Error al guardar la rutina con ejercicio, ya existe";
                    return "redirect:/admin/dash-rutinas";
                }
            }
        }

        rutinaEjercicioService.guardar(rutinaEjercicio);

        msj3="Rutina con ejercicios registrada con exito";

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
        msj1="Rutina eliminada con exito";
        msj2="";
        msj3="";
        rutinaService.eliminar(rutina);
        return "redirect:/admin/dash-rutinas";
    }


    @GetMapping("/admin/dash-ejercicio/eliminar/{id_ejercicio}")
    public String eliminarEjercicios(Ejercicio ejercicio){
        msj1="";
        msj2="Ejercicio eliminado con exito";
        msj3="";
        ejercicioService.eliminar(ejercicio);
        return "redirect:/admin/dash-rutinas";
    }
    @GetMapping("/admin/dash-rutina-ejercicio/eliminar/{id_rutina_ejercicio}")
    public String eliminarRutinaEjercicio(RutinaEjercicio rutinaEjercicio){
        msj1="";
        msj2="";
        msj3="Rutina con ejercicios eliminada con exito";
        rutinaEjercicioService.eliminar(rutinaEjercicio);
        return "redirect:/admin/dash-rutinas";
    }


}
