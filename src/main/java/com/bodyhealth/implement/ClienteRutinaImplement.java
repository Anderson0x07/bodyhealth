package com.bodyhealth.implement;

import com.bodyhealth.model.ClienteRutina;
import com.bodyhealth.model.Usuario;
import com.bodyhealth.repository.ClienteRutinaRepository;
import com.bodyhealth.service.ClienteRutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteRutinaImplement implements ClienteRutinaService {
    @Autowired
    private ClienteRutinaRepository clienteRutinaRepository;
    @Override
    public List<ClienteRutina> listarClientesRutina() {
        return (List<ClienteRutina>) clienteRutinaRepository.findAll();
    }

    @Override
    public void guardar(ClienteRutina clienteRutina) {
        clienteRutinaRepository.save(clienteRutina);
    }

    @Override
    public void eliminar(ClienteRutina clienteRutina) {
        clienteRutinaRepository.delete(clienteRutina);
    }

    @Override
    public ClienteRutina encontrarClienteRutina(ClienteRutina clienteRutina) {

        return clienteRutinaRepository.findById(clienteRutina.getId_clienterutina()).orElse(null);
    }

    @Override
    public List<ClienteRutina> encontrarClientesRutina(int id_rutina) {
        return clienteRutinaRepository.encontrarClientesRutina(id_rutina);
    }
}
