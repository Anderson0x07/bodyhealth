package com.bodyhealth.implement;

import com.bodyhealth.model.Compra;
import com.bodyhealth.repository.CompraRepository;
import com.bodyhealth.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraImplement implements CompraService {
    @Autowired
    private CompraRepository compraRepository;
    @Override
    public List<Compra> listarCompras() {
        return (List<Compra>) compraRepository.findAll();
    }

    @Override
    public void guardar(Compra compra) {
        compraRepository.save(compra);
    }

    @Override
    public void eliminar(Compra compra) {
        compraRepository.delete(compra);
    }

    @Override
    public Compra encontrarCompra(Compra compra) {

        return compraRepository.findById(compra.getId_compra()).orElse(null);
    }
}
