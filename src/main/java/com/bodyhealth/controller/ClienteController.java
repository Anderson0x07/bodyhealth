package com.bodyhealth.controller;


import com.bodyhealth.model.*;
import com.bodyhealth.repository.*;
import com.bodyhealth.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Slf4j
public class ClienteController {


    private BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EntrenadorClienteRepository entrenadorClienteRepository;
    @Autowired
    private ClienteDetalleRepository clienteDetalleRepository;

    @Autowired
    private DetalleRepository detalleRepository;

    @Autowired
    private EntrenadorClienteService entrenadorClienteService;


    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private ClienteDetalleService clienteDetalleService;

    @Autowired
    ProductoService productoService;

    @Autowired
    private ControlClienteRepository controlClienteRepository;
    @Autowired
    private ClienteRutinaRepository clienteRutinaRepository;

    @Autowired
    private RutinaService rutinaService;
    @Autowired
    private ClienteRutinaEjercicioRepository clienteRutinaEjercicioRepository;
    @Autowired
    private RutinaEjercicioRepository rutinaEjercicioRepository;
    @Autowired
    private ClienteRutinaEjercicioService clienteRutinaEjercicioService;
    @Autowired
    private ControlClienteService controlClienteService;

    @Autowired
    private ClienteRutinaService clienteRutinaService;

    private Util util = new Util();
    private String msj = "";


    //Lista clientes en el dashboard del admin
    @GetMapping("/admin/dash-clientes")
    public String listarClientes(Model model){

        //PLAN DE CLIENTE EN CASO DE TENER
        List<ClienteDetalle> clienteDet = clienteDetalleRepository.encontrarPlanes();
        if(clienteDet!=null){
            model.addAttribute("clientesActivados",clienteDet);
        }

        List<Cliente> desactivados =  usuarioService.listarDesactivados();
        model.addAttribute("clientesDesactivados",desactivados);

        model.addAttribute("msj",msj);
        msj="";

        return "/admin/clientes/dash-clientes";
    }

    @GetMapping("/admin/dash-clientes/expand/{id_usuario}")
    public String mostrarCliente(Cliente cliente, Model model){

        Cliente cnuevo = (Cliente) usuarioService.encontrarUsuario(cliente);

        model.addAttribute("cliente",cnuevo);

        EntrenadorCliente entrenadorCliente = entrenadorClienteRepository.encontrarEntrenador(cnuevo.getId_usuario());

        //ENTRENADOR DEL CLIENTE EN CASO DE TENER
        if(entrenadorCliente!=null){
            model.addAttribute("entrenadorcliente",entrenadorCliente);
        }

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





        return "/admin/clientes/cliente-expand";
    }

    //Guarda clientes en el dashboard del admin
    @PostMapping("/admin/dash-clientes/expand/guardar")
    public String guardarCliente(Cliente cliente, @RequestParam("file") MultipartFile imagen){

        List<Usuario> usuarios = usuarioService.listarUsuarios();

        for (Usuario maq : usuarios) {
            if (cliente.getDocumento()==maq.getDocumento() || cliente.getEmail().equalsIgnoreCase(maq.getEmail())) {
                msj = "Error al registrar el cliente, ya existe";
                return "redirect:/admin/dash-clientes";
            }

        }

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
        msj="Cliente registrado con exito.";

        return "redirect:/admin/dash-clientes";
    }
    @PostMapping("/admin/dash-clientes/expand/guardar-edicion")
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
        }

        usuarioService.guardar(cliente);
        msj="Cliente editado con exito.";

        return "redirect:/admin/dash-clientes/expand/editar/"+cliente.getId_usuario();
    }


    //ACTUALIZA ENTRENADOR A CLIENTE
    @PostMapping("/admin/dash-clientes/expand/guardartrainer")
    public String cambioEntrenador(EntrenadorCliente entrenadorCliente, Model model){

        log.info("llega: "+entrenadorCliente.toString());
        int id_cliente = entrenadorCliente.getCliente().getId_usuario();

        entrenadorClienteService.guardar(entrenadorCliente);

        model.addAttribute("trainer",entrenadorClienteRepository.encontrarEntrenador(entrenadorCliente.getCliente().getId_usuario()));

        return "redirect:/admin/dash-clientes/expand/editar/"+id_cliente;
    }

    //GUARDA PLAN DEL CLIENTE
    @PostMapping("/admin/dash-clientes/expand/guardarPlan")
    public String guardarPlan(ClienteDetalle clienteDetalle, Model model){

        log.info("ADD de plan: "+clienteDetalle.toString());

        int id_cliente = clienteDetalle.getId_cliente().getId_usuario();

        clienteDetalle.getId_cliente().setEstado(true);
        clienteDetalleService.guardar(clienteDetalle);

        model.addAttribute("clienteDetalle",clienteDetalleRepository.encontrarPlan(clienteDetalle.getId_cliente().getId_usuario()));

        return "redirect:/admin/dash-clientes/expand/editar/"+id_cliente;
    }



    //Editar clientes en el dashboard del admin
    @GetMapping("/admin/dash-clientes/expand/editar/{id_usuario}")
    public String editarCliente(Cliente cliente1, Model model){

        Cliente cliente = (Cliente) usuarioService.encontrarUsuario(cliente1);
        model.addAttribute("cliente",cliente);

        //ENCONTRAR ENTRENADOR ASIGNADO EN CASO DE TENER UNO
        EntrenadorCliente entrenadorCliente = entrenadorClienteRepository.encontrarEntrenador(cliente.getId_usuario());
        if(entrenadorCliente!=null){
            model.addAttribute("trainer",entrenadorCliente);
        }

        //PARA MOSTRAR TODOS LOS ENTRENADORES POR JORNADA
        List<Entrenador> entrenadoresJornada = usuarioRepository.entrenadoresJornada(cliente.getJornada());
        if(entrenadoresJornada.size()>0){
            model.addAttribute("trainers",entrenadoresJornada);
        }

        //PARA MOSTRAR PLAN ASIGNADO EN CASO DE TENER UNO
        ClienteDetalle clienteDetalle = clienteDetalleRepository.encontrarPlan(cliente.getId_usuario());
        if(clienteDetalle!=null){
            model.addAttribute("diferencia", util.obtenerDiferenciaDias(clienteDetalle.getFecha_fin()));
            model.addAttribute("clienteDetalle",clienteDetalle);
        }
        //PARA MOSTRAR TODOS LOS PLANES PARA SELECCIONAR
        model.addAttribute("planesdetallados",detalleRepository.findAll());
        //PARA MOSTRAR TODOS LOS METODOS DE PAGO DISPONIBLES
        model.addAttribute("metodos",metodoPagoRepository.findAll());
        model.addAttribute("msj",msj);
        msj="";

        return "/admin/clientes/cliente-editar";
    }

    @PostMapping("/admin/dash-clientes/expand/guardar-control")
    public String guardarEdicionControl(ControlCliente controlCliente){

        log.info("CONTROL ENTRANTE: "+controlCliente.toString());

        controlClienteService.guardar(controlCliente);

        return "redirect:/admin/dash-clientes/expand/"+controlCliente.getId_cliente().getId_usuario();
    }
    @PostMapping("/admin/dash-clientes/expand/guardar-cliente-rutina")
    public String guardarAsignacionRutina(ClienteRutina clienteRutina){

        clienteRutinaService.guardar(clienteRutina);

        return "redirect:/admin/dash-clientes/expand/"+clienteRutina.getId_cliente().getId_usuario();
    }

    //Desactiva clientes en el dashboard del admin
    @GetMapping("/admin/dash-clientes/expand/desactivar/{id_usuario}")
    public String desactivarCliente(Usuario usuario) throws ParseException {

        log.info("Cliente a desactivar: "+usuario.toString());

        Cliente cliente = (Cliente) usuarioService.encontrarUsuario(usuario);

        cliente.setEstado(false);

        usuarioService.guardar(cliente);

        ClienteDetalle ct = clienteDetalleRepository.encontrarPlan(cliente.getId_usuario());

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        Date ayer = c.getTime();

        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        String date = formato.format(ayer);

        Date miFecha = formato.parse(date);

        ct.setFecha_fin(miFecha);
        clienteDetalleRepository.save(ct);

        return "redirect:/admin/dash-clientes";

    }

    //Activa clientes en el dashboard del admin
    @GetMapping("/admin/dash-clientes/expand/activar/{id_usuario}")
    public String activarCliente(Usuario usuario){

        log.info("Cliente a activar: "+usuario.toString());

        Cliente cliente = (Cliente) usuarioService.encontrarUsuario(usuario);

        cliente.setEstado(true);

        usuarioService.guardar(cliente);

        return "redirect:/admin/dash-clientes";
    }




}
