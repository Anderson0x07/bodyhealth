package com.bodyhealth.controller;

import com.bodyhealth.model.*;
import com.bodyhealth.repository.*;
import com.bodyhealth.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
public class EntrenadorController {

    private BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
    @Autowired
    private EntrenadorClienteRepository entrenadorClienteRepository;

    @Autowired
    private ControlClienteRepository controlClienteRepository;

    @Autowired
    private ClienteRutinaRepository clienteRutinaRepository;

    @Autowired
    private ControlClienteService controlClienteService;
    @Autowired
    private RutinaService rutinaService;

    @Autowired
    private ClienteRutinaService clienteRutinaService;

    @Autowired
    private ClienteRutinaEjercicioRepository clienteRutinaEjercicioRepository;

    @Autowired
    private RutinaEjercicioRepository rutinaEjercicioRepository;
    @Autowired
    private ClienteRutinaEjercicioService clienteRutinaEjercicioService;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteDetalleRepository clienteDetalleRepository;

    private Util util = new Util();
    private String msj = "";


    @GetMapping("/admin/dash-trainers")
    public String listarTrainers(Model model){

        List<Entrenador> activos = usuarioService.listarEntrenadoresActivos();
        List<Entrenador> desactivados = usuarioService.listarEntrenadoresDesactivados();
        model.addAttribute("trainers",activos);
        model.addAttribute("trainersDesactivados",desactivados);
        model.addAttribute("msj",msj);
        msj="";

        return "admin/trainers/dash-trainers";
    }

    @GetMapping("/admin/dash-trainers/expand/{id_usuario}")
    public String mostrarEntrenador(Entrenador entrenador, Model model){


        Entrenador trainer = (Entrenador) usuarioService.encontrarUsuario(entrenador);
        model.addAttribute("trainer",trainer);


        return "admin/trainers/trainer-expand";
    }

    @GetMapping("/admin/dash-trainers/expand/editar/{id_usuario}")
    public String editar(Entrenador entrenador, Model model){
        

        entrenador = (Entrenador) usuarioService.encontrarUsuario(entrenador);

        model.addAttribute("trainer",entrenador);
        model.addAttribute("msj",msj);
        msj="";

        return "admin/trainers/trainer-editar";
    }

    //Guarda edición de entrenador en el dashboard del admin
    @PostMapping("/admin/dash-trainers/expand/guardar")
    public String guardarEntrenador(Entrenador entrenador,@RequestParam("file") MultipartFile imagen){

        List<Usuario> usuarios = usuarioService.listarUsuarios();

        for (Usuario maq : usuarios) {
            if (entrenador.getDocumento()==maq.getDocumento() || entrenador.getEmail().equalsIgnoreCase(maq.getEmail())) {
                msj = "Error al registrar el entrenador, ya existe";
                return "redirect:/admin/dash-trainers";
            }
        }

        Path directorioImagenes = Paths.get("src//main//resources//static/images");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        if(!imagen.isEmpty()) {
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                entrenador.setFoto(imagen.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        entrenador.setPassword(encode.encode(entrenador.getPassword()));

        usuarioService.guardar(entrenador);
        msj="Entrenador registrado con exito.";

        return "redirect:/admin/dash-trainers";
    }
    @PostMapping("/admin/dash-trainers/expand/guardar-entrenador")
    public String guardarEdicionEntrenador(Entrenador entrenador,@RequestParam("file") MultipartFile imagen){

        Path directorioImagenes = Paths.get("src//main//resources//static/images");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        if(!imagen.isEmpty()) {
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                entrenador.setFoto(imagen.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        usuarioService.guardar(entrenador);
        msj="Entrenador editado con exito.";

        return "redirect:/admin/dash-trainers/expand/editar/"+entrenador.getId_usuario();
    }

    //Desactiva entrenadores en el dashboard del admin
    @GetMapping("/admin/dash-trainers/expand/desactivar/{id_usuario}")
    public String desactivarEntrenador(Entrenador entrenador){

        entrenador = (Entrenador) usuarioService.encontrarUsuario(entrenador);

        entrenador.setEstado(false);

        usuarioService.guardar(entrenador);


        return "redirect:/admin/dash-trainers";
    }

    //Desactiva entrenadores en el dashboard del admin
    @GetMapping("/admin/dash-trainers/expand/activar/{id_usuario}")
    public String activarEntrenador(Entrenador entrenador){

        entrenador = (Entrenador) usuarioService.encontrarUsuario(entrenador);

        entrenador.setEstado(true);

        usuarioService.guardar(entrenador);


        return "redirect:/admin/dash-trainers";
    }


    //ACCESO A TRAINER/RUTINAS
    @GetMapping("/trainer/dash-clientes")
    public String listarClientesEntrenador(Model model, @AuthenticationPrincipal User user){

        Entrenador entrenador = usuarioRepository.encontrarTrainerEmail(user.getUsername());

        List<EntrenadorCliente> clientesAsignados = entrenadorClienteRepository.encontrarClientes(entrenador.getId_usuario());

        model.addAttribute("clientesAsignados",clientesAsignados);

        return "trainer/clientes/dash-clientes";
    }


    @GetMapping("/trainer/dash-clientes/expand/{id_usuario}")
    public String mostrarCliente(Cliente cliente, Model model){

        Cliente cnuevo = (Cliente) usuarioService.encontrarUsuario(cliente);

        model.addAttribute("cliente",cnuevo);


        //PLAN DE CLIENTE EN CASO DE TENER
        ClienteDetalle clienteDetalle = clienteDetalleRepository.encontrarPlan(cnuevo.getId_usuario());

        if(clienteDetalle!=null){
            model.addAttribute("diferencia", util.obtenerDiferenciaDias(clienteDetalle.getFecha_fin()));

            model.addAttribute("clientedetalle",clienteDetalle);
        }

        //PARA EL CONTROL DE PESO Y ESTATURA
        ControlCliente control = controlClienteRepository.encontrarControlCliente(cnuevo.getId_usuario());
        if(control != null){
            model.addAttribute("control",control);
        }


        ClienteRutina clienteRutina = clienteRutinaRepository.encontrarRutina(cnuevo.getId_usuario());

        List<Rutina> rutinas = rutinaService.listarRutina();
        model.addAttribute("rutinas",rutinas);
        if(clienteRutina != null){
            log.info("IF ENVIO");
            model.addAttribute("clienteRutina",clienteRutina);

            //*GUARDA TODAS LOS EJERCICIOS DE LA RUTINA ESPECIFICADA EN LA TABLA CLIENTE_RUTINA_EJERCICIO
            List<ClienteRutinaEjercicio>  rutinaconejercicios = clienteRutinaEjercicioRepository.encontrarRutinaCompletaCliente(clienteRutina.getId_clienterutina());

            for (ClienteRutinaEjercicio rutinac: rutinaconejercicios) {
                clienteRutinaEjercicioService.eliminar(rutinac);
            }

            rutinaconejercicios.removeAll(rutinaconejercicios);


            if(rutinaconejercicios.size()==0){
                List<RutinaEjercicio> rutinaEjercicio = rutinaEjercicioRepository.encontrarRutinaEjercicios(clienteRutina.getId_rutina().getId_rutina());
                ClienteRutinaEjercicio clienteRutinaEjercicio;
                int idActual = clienteRutinaEjercicioRepository.idActual();
                for (int i = 1; i <= rutinaEjercicio.size(); i++) {

                    clienteRutinaEjercicio=new ClienteRutinaEjercicio();
                    clienteRutinaEjercicio.setId_cliente_rutina_ejercicio(idActual+i);
                    clienteRutinaEjercicio.setId_cliente_rutina(clienteRutina);
                    clienteRutinaEjercicio.setId_rutina_ejercicio(rutinaEjercicio.get(i-1));
                    clienteRutinaEjercicioService.guardar(clienteRutinaEjercicio);

                }
                model.addAttribute("rutinaconejercicios",clienteRutinaEjercicioRepository.encontrarRutinaCompletaCliente(clienteRutina.getId_clienterutina()));
            }
        }

        return "trainer/clientes/cliente-expand";
    }


    @PostMapping("/trainer/dash-clientes/guardar-control")
    public String guardarEdicionControl(ControlCliente controlCliente){

        log.info("CONTROL ENTRANTE: "+controlCliente.toString());

        controlClienteService.guardar(controlCliente);

        return "redirect:/trainer/dash-clientes/expand/"+controlCliente.getId_cliente().getId_usuario();
    }


    @PostMapping("/trainer/dash-rutinas/guardar-cliente-rutina")
    public String guardarAsignacionRutina(ClienteRutina clienteRutina){

        clienteRutinaService.guardar(clienteRutina);

        return "redirect:/trainer/dash-clientes/expand/"+clienteRutina.getId_cliente().getId_usuario();
    }

    @GetMapping("/trainer/dashboard")
    public String irDashboard(){

        return "trainer/dashboard";
    }

    //M I P E R F I L
    @GetMapping("/trainer/perfil")
    public String perfilTrainer(Model model, @AuthenticationPrincipal User user){

        Entrenador entrenador = usuarioRepository.encontrarTrainerEmail(user.getUsername());

        model.addAttribute("trainer",entrenador);

        return "trainer/perfil";
    }

    @GetMapping("/trainer/dash-trainer/ver-horario/{id_usuario}")
    public String horarioTrainer(Model model, Entrenador entrenador){
        String rta="";

        entrenador = (Entrenador) usuarioService.encontrarUsuario(entrenador);

        if(entrenador.getJornada().equalsIgnoreCase("Mañana")){
            rta = "layouts/horario-manana";
        } else if (entrenador.getJornada().equalsIgnoreCase("Tarde")){
            rta = "layouts/horario-tarde";
        }

        model.addAttribute("trainer",entrenador);
        return rta;
    }

    @PostMapping("/trainer/perfil/guardar-perfil")
    public String guardarEdicionPerfil(Entrenador entrenador, @RequestParam("file") MultipartFile imagen){

        log.info("ENTRENADOR: "+entrenador.getId_usuario());

        Path directorioImagenes = Paths.get("src//main//resources//static/images");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        if(!imagen.isEmpty()){
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                entrenador.setFoto(imagen.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        usuarioService.guardar(entrenador);

        return "redirect:/trainer/perfil";
    }

}
