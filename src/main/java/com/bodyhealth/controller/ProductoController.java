package com.bodyhealth.controller;

import com.bodyhealth.model.*;
import com.bodyhealth.service.MaquinaService;
import com.bodyhealth.service.ProductoService;
import com.bodyhealth.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/dash-productos")
    public String listarProductos(Model model){
        List<Producto> productos = productoService.listarProductos();

        List<Producto> activos = productoService.listarActivos();
        List<Producto> desactivados = productoService.listarDesactivados();
        model.addAttribute("productos",activos);
        model.addAttribute("productossDesactivados",desactivados);
        model.addAttribute("proveedores",proveedorService.listarProveedores());

        return "admin/productos/dash-productos";
    }

    //Guarda nuevo producto
    @PostMapping("/dash-productos/guardar")
    public String guardarNuevoProducto(Producto producto,@RequestParam("file") MultipartFile imagen){
        Path directorioImagenes = Paths.get("src//main//resources//static/images");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        try {
            byte[] bytesImg = imagen.getBytes();
            Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
            Files.write(rutaCompleta, bytesImg);
            producto.setFoto(imagen.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        productoService.guardar(producto);

        return "redirect:/admin/dash-productos";
    }

    @GetMapping("/dash-productos/expand/{id_producto}")
    public String expandProducto(Producto producto, Model model){

        producto = productoService.encontrarProducto(producto);

        model.addAttribute("producto",producto);

        return "admin/productos/producto-expand";
    }

    //Guarda edición de producto en el dashboard del admin
    @PostMapping("/dash-productos/expand/guardar")
    public String guardarEdicionProducto(Producto producto,@RequestParam("file") MultipartFile imagen){

        Path directorioImagenes = Paths.get("src//main//resources//static/images");
        String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
        if(!imagen.isEmpty()){
            try {
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                //Path rutaCompleta1 = Paths.get(rutaAbsoluta + "//" + "Anime5.jpg");
                //Files.delete(rutaCompleta1);
                Files.write(rutaCompleta, bytesImg);
                producto.setFoto(imagen.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            Producto panterior = productoService.encontrarProducto(producto);

            producto.setFoto(panterior.getFoto());
        }

        productoService.guardar(producto);

        return "redirect:/admin/dash-productos/expand/"+producto.getId_producto();
    }


    @GetMapping("/dash-productos/expand/editar/{id_producto}")
    public String editar(Producto producto, Model model){

        producto = productoService.encontrarProducto(producto);

        model.addAttribute("producto",producto);
        model.addAttribute("proveedores",proveedorService.listarProveedores());


        return "admin/productos/producto-editar";
    }

    @GetMapping("/dash-productos/eliminar")
    public String eliminarProducto(Producto producto){
        productoService.eliminar(producto);
        return "redirect:/admin/dash-productos";
    }

    @GetMapping("/dash-productos/expand/desactivar/{id_producto}")
    public String desactivarProducto(Producto producto){

        producto = productoService.encontrarProducto(producto);

        producto.setEstado(false);

        productoService.guardar(producto);


        return "redirect:/admin/dash-productos";
    }

    //Desactiva entrenadores en el dashboard del admin
    @GetMapping("/dash-productos/expand/activar/{id_producto}")
    public String activarProducto(Producto producto){

        producto = productoService.encontrarProducto(producto);

        producto.setEstado(true);

        productoService.guardar(producto);


        return "redirect:/admin/dash-productos";
    }
}
