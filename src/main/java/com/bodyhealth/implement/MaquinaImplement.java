package com.bodyhealth.implement;

import com.bodyhealth.model.Maquina;
import com.bodyhealth.repository.MaquinaRepository;
import com.bodyhealth.service.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaquinaImplement implements MaquinaService {
    @Autowired
    private MaquinaRepository maquinaRepository;
    @Override
    public List<Maquina> listarMaquinas() {
        return (List<Maquina>) maquinaRepository.findAll();
    }

    @Override
    public void guardar(Maquina maquina) {
        maquinaRepository.save(maquina);
    }

    @Override
    public void eliminar(Maquina maquina) {
        maquinaRepository.delete(maquina);
    }

    @Override
    public Maquina encontrarMaquina(Maquina maquina) {

        return maquinaRepository.findById(maquina.getId_maquina()).orElse(null);
    }
}
