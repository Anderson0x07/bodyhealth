package com.bodyhealth.controller;

import com.bodyhealth.model.*;
import com.bodyhealth.repository.UsuarioRepository;
import com.bodyhealth.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class CompraController {
    @Autowired
    private CompraService compraService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private List<Pedido> pedidos = new ArrayList<>();

    private Compra compra = new Compra();

    private Util util = new Util();


    /*@PostMapping("/addItem")
    public String addItem(Model model, Producto producto, @AuthenticationPrincipal User user){

        Cliente cliente = usuarioRepository.encontrarClienteEmail(user.getUsername());

        MetodoPago busca = new MetodoPago(); busca.setId_metodopago(1);
        MetodoPago metodoPago = metodoPagoService.encontrarMetodoPago(busca);

        compra.setFecha_compra(util.obtenerFechaActual());
        compra.setId_cliente(cliente);
        compra.setId_metodopago(metodoPago);

        log.info("producto a añadir: "+producto.toString());
        Producto item = productoService.encontrarProducto(producto);

        //CREACION DE PEDIDOS
        Pedido pedido = new Pedido();

        pedido.setProducto(item);
        pedido.setCantidad(1);
        pedido.setCompra(compra);


        if(!pedidos.isEmpty()){
            for (int i=0; i<pedidos.size();i++){
                if(pedidos.get(i)==pedido){
                    pedidos.get(i).setCantidad(pedidos.get(i).getCantidad()+1);
                }
                else{
                    pedidos.add(pedido);
                }
            }
        } else {
            pedidos.add(pedido);
        }

        String add = "Producto añadido con éxito";

        model.addAttribute("items",pedidos);
        model.addAttribute("add",add);


        return "/cliente/carrito";
    }*/

    @PostMapping("/addItem")
    public String addCart(Producto id_producto, Model model){

        Pedido pedido = new Pedido();

        double sumaTotal = 0;

        Producto producto = productoService.encontrarProducto(id_producto);
        log.info("producto: {}",producto.toString());
        int cantidad =1;

        pedido.setProducto(producto);
        pedido.setCantidad(cantidad);
        pedido.setTotal(producto.getPrecio()*pedido.getCantidad());

        if(pedidos.size()>0){
            for (int i=0; i<pedidos.size();i++){
                log.info("get: "+pedidos.get(i).getProducto()+" - prod: "+producto);
                if(pedidos.get(i).getProducto().equals(producto)){
                    log.info("entre");
                    cantidad = pedidos.get(i).getCantidad()+1;
                    log.info("cantidaad for: "+cantidad);
                }
            }
        }

        //validar que le producto no se añada 2 veces
        Integer idProducto=producto.getId_producto();
        boolean ingresado= pedidos.stream().anyMatch(p -> p.getProducto().getId_producto()==idProducto);

        if (!ingresado) {
            pedidos.add(pedido);
        } else{
            log.info("si es ingresado, entro a validar");
            for (int i=0; i<pedidos.size();i++){
                if(pedidos.get(i).equals(pedido)){
                    log.info("entro a cambiar cantidad");
                    pedidos.get(i).setCantidad(cantidad);

                }
            }
        }

        sumaTotal = pedidos.stream().mapToDouble(dt -> dt.getTotal()).sum();

        compra.setTotal(sumaTotal);
        compra.setFecha_compra(util.obtenerFechaActual());

        String add = "Producto añadido con éxito";

        model.addAttribute("items", pedidos);
        model.addAttribute("orden", compra);
        model.addAttribute("add",add);

        return "/cliente/carrito";
    }
}
