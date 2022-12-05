package com.bodyhealth.service;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Detalle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetalleService {
    public List<Detalle> listarDetalles();

    public void guardar(Detalle detalle);

    public void eliminar(Detalle detalle);

    public Detalle encontrarDetalle(Detalle detalle);
}
