package com.bodyhealth.service;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Compra;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompraService{
    public List<Compra> listarCompras();

    public void guardar(Compra compra);

    public void eliminar(Compra compra);

    public Compra encontrarCompra(Compra compra);
}
