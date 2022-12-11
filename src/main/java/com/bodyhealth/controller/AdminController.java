package com.bodyhealth.controller;


import com.bodyhealth.model.Administrador;
import com.bodyhealth.model.Usuario;
import com.bodyhealth.repository.UsuarioRepository;
import com.bodyhealth.service.EjercicioService;
import com.bodyhealth.service.ProductoService;
import com.bodyhealth.service.RutinaService;
import com.bodyhealth.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@Slf4j
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoService productoService;
    @Autowired
    private RutinaService rutinaService;

    @Autowired
    private EjercicioService ejercicioService;




    @GetMapping("/admin/dashboard") //Para acceder al dashboard
    public String inicio(Model model, @AuthenticationPrincipal User admin){

        log.info("dashboard");
        model.addAttribute("admin", admin);

        model.addAttribute("clientesactivos",usuarioService.listarActivos().size());
        model.addAttribute("clientesdesactivados",usuarioService.listarDesactivados().size());

        model.addAttribute("trainersactivos",usuarioService.listarEntrenadoresActivos().size());
        model.addAttribute("trainersdesactivados",usuarioService.listarEntrenadoresDesactivados().size());

        model.addAttribute("productosactivos",productoService.listarActivos().size());
        model.addAttribute("productosdesactivados",productoService.listarDesactivados().size());

        model.addAttribute("rutinas",rutinaService.listarRutina().size());
        model.addAttribute("ejercicios",ejercicioService.listarEjercicios().size());

        return "admin/dashboard";
    }

    @GetMapping("/admin/dash-horarios")
    public String listarHorarios(){
        return "admin/dash-horarios";
    }




    //M I P E R F I L
    @GetMapping("/admin/perfil")
    public String perfilAdmin(Model model, @AuthenticationPrincipal User user){

        log.info("USER LOGIN: "+user.getUsername());

        model.addAttribute("admin",usuarioRepository.encontrarAdminEmail(user.getUsername()));

        return "admin/perfil";
    }


    @PostMapping("/admin/perfil/guardar-perfil")
    public String guardarEdicionPerfil(Administrador administrador){

        log.info("ADMIN: "+administrador.getId_usuario() + " - "+administrador.toString());
        usuarioService.guardar(administrador);

        return "redirect:/admin/perfil";
    }



}
