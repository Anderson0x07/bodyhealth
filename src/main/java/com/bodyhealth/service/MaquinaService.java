package com.bodyhealth.service;

import com.bodyhealth.model.Cliente;
import com.bodyhealth.model.Maquina;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MaquinaService {
    public List<Maquina> listarMaquinas();

    public void guardar(Maquina maquina);

    public void eliminar(Maquina maquina);

    public Maquina encontrarMaquina(Maquina maquina);
}
