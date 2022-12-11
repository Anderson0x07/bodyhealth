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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private ProductoService productoService;

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private List<Pedido> pedidos = new ArrayList<>();

    private Compra compra = new Compra();

    private Util util = new Util();

    String msj="";


    //add producto al carrito
    @PostMapping("/addItem")
    public String addCart(Producto id_producto,@RequestParam Integer cantidad, Model model){

        Pedido pedido = new Pedido();

        double sumaTotal = 0;

        Producto producto = productoService.encontrarProducto(id_producto);
        log.info("producto: {}",producto.toString());


        pedido.setProducto(producto);
        pedido.setCantidad(cantidad);
        pedido.setTotal(producto.getPrecio()*cantidad);

        //validar que le producto no se añada 2 veces
        Integer idProducto=producto.getId_producto();
        boolean ingresado= pedidos.stream().anyMatch(p -> p.getProducto().getId_producto()==idProducto);

        if (!ingresado) {
            pedidos.add(pedido);
        }

        sumaTotal = pedidos.stream().mapToDouble(dt -> dt.getTotal()).sum();

        compra.setTotal(sumaTotal);
        compra.setFecha_compra(util.obtenerFechaActual());

        msj = "Producto añadido con éxito";

        model.addAttribute("items", pedidos);
        model.addAttribute("orden", compra);
        model.addAttribute("msj",msj);

        return "cliente/carrito";
    }

    @GetMapping("/carrito")
    public String getCart(Model model) {

        msj="";

        model.addAttribute("items", pedidos);
        model.addAttribute("orden", compra);
        model.addAttribute("msj",msj);


        return "cliente/carrito";
    }

    // quitar un producto del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteProductoCart(@PathVariable Integer id, Model model) {

        // lista nueva de prodcutos
        List<Pedido> pedidosNuevos = new ArrayList<Pedido>();

        for (Pedido detalleOrden : pedidos) {
            if (detalleOrden.getProducto().getId_producto() != id) {
                pedidosNuevos.add(detalleOrden);
            }
        }

        // poner la nueva lista con los productos restantes
        pedidos = pedidosNuevos;

        double sumaTotal = 0;
        sumaTotal = pedidos.stream().mapToDouble(dt -> dt.getTotal()).sum();

        compra.setTotal(sumaTotal);

        msj = "Producto eliminado del carrito";

        model.addAttribute("items", pedidos);
        model.addAttribute("orden", compra);
        model.addAttribute("msj",msj);

        return "cliente/carrito";
    }

    @GetMapping("/order")
    public String order(Model model, @AuthenticationPrincipal User user) {

        Usuario usuario = usuarioRepository.encontrarClienteEmail(user.getUsername());

        model.addAttribute("usuario",usuario);
        model.addAttribute("items", pedidos);
        model.addAttribute("orden", compra);
        model.addAttribute("metodos",metodoPagoService.listarMetodosPagos());

        return "cliente/resumenorden";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(@AuthenticationPrincipal User user, Model model, MetodoPago metodo_pago) {

        Date fechaCreacion = new Date();

        metodo_pago = metodoPagoService.encontrarMetodoPago(metodo_pago);

        compra.setFecha_compra(fechaCreacion);
        compra.setId_cliente(usuarioRepository.encontrarClienteEmail(user.getUsername()));
        compra.setId_metodopago(metodo_pago);

        compraService.guardar(compra);

        msj = "Compra realizada con exito!!";

        //guardar detalles
        for (Pedido pedido:pedidos) {
            pedido.setCompra(compra);

            int nuevoStock = pedido.getProducto().getStock()-pedido.getCantidad();
            System.out.println("stock: "+nuevoStock);
            Producto pro = pedido.getProducto();

            if(nuevoStock>=0){
                pro.setStock(nuevoStock);
                if(pro.getStock()==0){
                    pro.setEstado(false);
                }
                productoService.guardar(pro);
            } else {
                msj ="Error, compra no realizada no hay suficiente stock";
                break;
            }
            pedidoService.guardar(pedido);

        }


        model.addAttribute("msj",msj);

        ///limpiar lista y orden de compra
        compra = new Compra();
        pedidos.clear();

        return "index";
    }



}
