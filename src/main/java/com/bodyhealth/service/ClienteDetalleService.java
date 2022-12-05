package com.bodyhealth.service;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.ClienteDetalle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteDetalleService {
    public List<ClienteDetalle> listarClientesDetalles();

    public void guardar(ClienteDetalle clienteDetalle);

    public void eliminar(ClienteDetalle clienteDetalle);

    public ClienteDetalle encontrarClienteDetalle(ClienteDetalle clienteDetalle);
}
