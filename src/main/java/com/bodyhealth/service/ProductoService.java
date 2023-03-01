package com.bodyhealth.service;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Entrenador;
import com.bodyhealth.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductoService {

    public List<Producto> listarProductos();

    public void guardar(Producto producto);

    public void eliminar(Producto producto);

    public Producto encontrarProducto(Producto producto);
    public List<Producto> listarActivos();

    public List<Producto> listarDesactivados();
}
