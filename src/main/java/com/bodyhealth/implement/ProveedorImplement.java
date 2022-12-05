package com.bodyhealth.implement;

import com.bodyhealth.model.Proveedor;
import com.bodyhealth.repository.ProveedorRepository;
import com.bodyhealth.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorImplement implements ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Override
    public List<Proveedor> listarProveedores() {
        return (List<Proveedor>) proveedorRepository.findAll();
    }

    @Override
    public void guardar(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    @Override
    public void eliminar(Proveedor proveedor) {
        proveedorRepository.delete(proveedor);
    }

    @Override
    public Proveedor encontrarProveedor(Proveedor proveedor) {
        return proveedorRepository.findById(proveedor.getId_proveedor()).orElse(null);
    }
}
