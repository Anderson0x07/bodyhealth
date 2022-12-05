package com.bodyhealth.service;

import com.bodyhealth.model.MetodoPago;
import com.bodyhealth.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MetodoPagoService {
    public List<MetodoPago> listarMetodosPagos();

    public void guardar(MetodoPago metodoPago);

    public void eliminar(MetodoPago metodoPago);

    public MetodoPago encontrarMetodoPago(MetodoPago metodoPago);
}
