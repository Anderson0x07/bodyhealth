package com.bodyhealth.service;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Entrenador;
import com.bodyhealth.model.EntrenadorCliente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EntrenadorClienteService {
    public List<EntrenadorCliente> listarEntrenadoresClientes();

    public void guardar(EntrenadorCliente entrenadorCliente);

    public void eliminar(EntrenadorCliente entrenadorCliente);

    public EntrenadorCliente encontrarEntrenadorCliente(EntrenadorCliente entrenadorCliente);

}
