package com.bodyhealth.service;

import com.bodyhealth.model.ControlCliente;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ControlClienteService{
    public List<ControlCliente> listarContolClientes();

    public void guardar(ControlCliente controlCliente);

    public void eliminar(ControlCliente controlCliente);

    public ControlCliente encontrarControlCliente(ControlCliente controlCliente);
}
