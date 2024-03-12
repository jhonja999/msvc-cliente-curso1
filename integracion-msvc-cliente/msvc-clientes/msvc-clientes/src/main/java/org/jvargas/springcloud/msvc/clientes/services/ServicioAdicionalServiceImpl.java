package org.jvargas.springcloud.msvc.clientes.services;


import org.jvargas.springcloud.msvc.clientes.models.entity.ServicioAdicional;
import org.jvargas.springcloud.msvc.clientes.repositories.ServicioAdicionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ServicioAdicionalServiceImpl implements ServicioAdicionalService {

    @Autowired //para inyectar la dependencia de una clase con metodos
    private ServicioAdicionalRepository repository;

    @Override
    @Transactional(readOnly = true) //springframework.transaction.annotation - solo de lectura
    public List<ServicioAdicional> listar() {
        return (List<ServicioAdicional>) repository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<ServicioAdicional> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public ServicioAdicional guardar(ServicioAdicional empleado) {
        return repository.save(empleado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
    @Override
    public Optional<ServicioAdicional> porDni(String dni) {return repository.findByDni(dni);}
}
