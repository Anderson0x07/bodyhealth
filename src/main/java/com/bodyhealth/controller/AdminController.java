package com.bodyhealth.controller;


import com.bodyhealth.model.Administrador;
import com.bodyhealth.model.Usuario;
import com.bodyhealth.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/admin/dashboard") //Para acceder al dashboard
    public String inicio(Model model, @AuthenticationPrincipal User admin){

        log.info("dashboard");
        model.addAttribute("admin", admin);

        return "admin/dashboard";
    }

    @GetMapping("/admin/dash-horarios")
    public String listarHorarios(){
        return "/admin/dash-horarios";
    }


    //M I P E R F I L
    @GetMapping("/admin/perfil")
    public String perfilAdmin(Model model){

        //DE ACUERDO DE LOGIN
        Usuario administrador = new Administrador();
        administrador.setId_usuario(1);

        administrador = usuarioService.encontrarUsuario(administrador);

        model.addAttribute("admin",administrador);

        return "admin/perfil";
    }


    @PostMapping("/admin/perfil/guardar-perfil")
    public String guardarEdicionPerfil(Administrador administrador){

        usuarioService.guardar(administrador);

        return "redirect:/admin/perfil";
    }



}
