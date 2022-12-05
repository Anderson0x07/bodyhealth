package com.bodyhealth.implement;

import com.bodyhealth.model.Musculo;
import com.bodyhealth.repository.MusculoRepository;
import com.bodyhealth.service.MusculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusculoImplement implements MusculoService {
    @Autowired
    private MusculoRepository musculoRepository;
    @Override
    public List<Musculo> listarMusculos() {
        return (List<Musculo>) musculoRepository.findAll();
    }

    @Override
    public void guardar(Musculo musculo) {
        musculoRepository.save(musculo);
    }

    @Override
    public void eliminar(Musculo musculo) {
        musculoRepository.delete(musculo);
    }

    @Override
    public Musculo encontrarMusculo(Musculo musculo) {

        return musculoRepository.findById(musculo.getId_musculo()).orElse(null);
    }
}
