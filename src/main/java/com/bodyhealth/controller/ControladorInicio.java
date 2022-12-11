package com.bodyhealth.controller;

import com.bodyhealth.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

@Controller
@Slf4j
public class ControladorInicio {

    private BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DetalleService detalleService;

    @Autowired
    private ClienteDetalleService clienteDetalleService;

    @Autowired
    private EntrenadorClienteRepository entrenadorClienteRepository;

    @Autowired
    private ClienteDetalleRepository clienteDetalleRepository;

    private Util util = new Util();

    @Autowired
    private ControlClienteRepository controlClienteRepository;

    @Autowired
    private ClienteRutinaRepository clienteRutinaRepository;

    @Autowired
    private RutinaService rutinaService;

    @Autowired
    private ClienteRutinaEjercicioRepository clienteRutinaEjercicioRepository;

    @Autowired
    private ClienteRutinaEjercicioService clienteRutinaEjercicioService;

    @Autowired
    private RutinaEjercicioRepository rutinaEjercicioRepository;

    @Autowired
    private DetalleRepository detalleRepository;

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private final EmailService emailService;

    public ControladorInicio(EmailService emailService) {
        this.emailService = emailService;
    }

    String msj="";

    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("msj",msj);
        msj="";

        return "index";
    }

    @GetMapping("/productos")
    public String productos(Model model){
        List<Producto> product = productoService.listarActivos();
        model.addAttribute("productos",product);
        return "cliente/productos";
    }

    @GetMapping("/planes")
    public String planes(Model model, @AuthenticationPrincipal User user){
        List<Detalle> detalle = detalleRepository.listarActivos();
        model.addAttribute("planes",detalle);
        model.addAttribute("metodos",metodoPagoService.listarMetodosPagos());

            //PLAN DE CLIENTE EN CASO DE TENER
            Cliente cnuevo = null;
            cnuevo = usuarioRepository.encontrarClienteEmail(user.getUsername());
            if(cnuevo != null){
                ClienteDetalle clienteDetalle = clienteDetalleRepository.encontrarPlan(cnuevo.getId_usuario());

                if(clienteDetalle!=null){
                    model.addAttribute("diferencia", util.obtenerDiferenciaDias(clienteDetalle.getFecha_fin()));

                    model.addAttribute("clienteDetalle",clienteDetalle);
                }
            }


        return "cliente/planes";
    }
    @GetMapping("/noticias")
    public String noticias(){
        return "cliente/noticias";
    }



    //Prueba de nuevo regstro cliente plantilla
    @GetMapping("/registroCliente")
    public String registroClientePlantilla(){
        return "cliente/registroCliente";
    }

    @PostMapping("/cliente/dash-clientes/expand/guardar")
    public String guardarClienteIndex(Cliente cliente, @RequestParam("file") MultipartFile imagen){

        Path directorioImagenes = Paths.get("src//main//resources//static/images");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        if(!imagen.isEmpty()){
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                cliente.setFoto(imagen.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        cliente.setPassword(encode.encode(cliente.getPassword()));

        usuarioService.guardar(cliente);
        this.emailService.sendListEmail(cliente.getEmail());

        return "redirect:/login";
    }

    @PostMapping("/cliente/mi-perfil/guardar-edicion")
    public String guardarEdicionCliente(Cliente cliente, @RequestParam("file") MultipartFile imagen){

        Path directorioImagenes = Paths.get("src//main//resources//static/images");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        if(!imagen.isEmpty()){
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                cliente.setFoto(imagen.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Cliente canterior = (Cliente) usuarioService.encontrarUsuario(cliente);

            cliente.setFoto(canterior.getFoto());
        }

        usuarioService.guardar(cliente);

        return "redirect:/mi-perfil";
    }

    @GetMapping("/administradores")
    public String administrador(){
        log.info("ENTRO A ADMINS");
        return "admin/dashboard";
    }

    @GetMapping("/trainer")
    public String trainers(){
        log.info("ENTRO A TRAINER");
        return "trainer/dashboard";
    }

    @GetMapping("/errores/403")
    public String muestraAccesoDenegado(){
        log.info("ENTRO A errores");
        return "errores/403";
    }

    //M I P E R F I L - C L I E N T E
    @GetMapping("/mi-perfil")
    public String miPerfil(Model model, @AuthenticationPrincipal User user){

        Cliente cnuevo = usuarioRepository.encontrarClienteEmail(user.getUsername());
        model.addAttribute("cliente", cnuevo);

        EntrenadorCliente entrenadorCliente = entrenadorClienteRepository.encontrarEntrenador(cnuevo.getId_usuario());

        //ENTRENADOR DEL CLIENTE EN CASO DE TENER
        if(entrenadorCliente!=null){
            model.addAttribute("entrenadorcliente",entrenadorCliente);
        }

        //PLAN DE CLIENTE EN CASO DE TENER
        ClienteDetalle clienteDetalle = clienteDetalleRepository.encontrarPlan(cnuevo.getId_usuario());

        if(clienteDetalle!=null){
            model.addAttribute("diferencia", util.obtenerDiferenciaDias(clienteDetalle.getFecha_fin()));

            model.addAttribute("clienteDetalle",clienteDetalle);
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
                int idActual = rutinaEjercicio.get(rutinaEjercicio.size()-1).getId_rutina_ejercicio();
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

        return "cliente/mi-perfil";
    }

    @PostMapping("/cliente/mi-perfil/enviar-comentario")
    public String enviarComentario(Cliente cliente){

        Cliente cl = (Cliente) usuarioService.encontrarUsuario(cliente);

        cl.setComentario(cliente.getComentario());

        usuarioService.guardar(cl);

        return "redirect:/mi-perfil";
    }

    @PostMapping("/addPlan")
    public String addPlan(Model model, ClienteDetalle clienteDetalle, @AuthenticationPrincipal User user){

        Cliente cliente = usuarioRepository.encontrarClienteEmail(user.getUsername());

        Detalle detalle = detalleService.encontrarDetalle(clienteDetalle.getId_detalle());

        Date fecha = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);

        calendar.setTime(fecha); // Configuramos la fecha que se recibe
        calendar.add(calendar.MONTH, detalle.getMeses());  // numero de meses a añadir

        clienteDetalle.setFecha_inicio(fecha);
        clienteDetalle.setFecha_fin(calendar.getTime());
        clienteDetalle.setId_detalle(detalle);
        cliente.setEstado(true);
        clienteDetalle.setId_cliente(cliente);


        clienteDetalleService.guardar(clienteDetalle);

        msj = "Plan adquirido con exito";

        return "redirect:/";

    }
    

}
