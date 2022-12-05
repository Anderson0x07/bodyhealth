package com.bodyhealth.implement;

import com.bodyhealth.model.Detalle;
import com.bodyhealth.repository.DetalleRepository;
import com.bodyhealth.service.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleImplement implements DetalleService {

    @Autowired
    private DetalleRepository detalleRepository;

    @Override
    public List<Detalle> listarDetalles() {
        return (List<Detalle>) detalleRepository.findAll();
    }

    @Override
    public void guardar(Detalle detalle) {
        detalleRepository.save(detalle);
    }

    @Override
    public void eliminar(Detalle detalle) {
        detalleRepository.delete(detalle);
    }

    @Override
    public Detalle encontrarDetalle(Detalle detalle) {

        return detalleRepository.findById(detalle.getId_detalle()).orElse(null);
    }
}
