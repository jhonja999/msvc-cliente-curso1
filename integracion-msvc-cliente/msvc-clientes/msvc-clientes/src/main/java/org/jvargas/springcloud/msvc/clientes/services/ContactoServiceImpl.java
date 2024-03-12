package org.jvargas.springcloud.msvc.clientes.services;

import org.jvargas.springcloud.msvc.clientes.models.entity.Cliente;
import org.jvargas.springcloud.msvc.clientes.models.entity.Contacto;
import org.jvargas.springcloud.msvc.clientes.repositories.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ContactoServiceImpl implements ContactoService {

    @Autowired //para inyectar la dependencia de una clase con metodos
    private ContactoRepository repository;

    @Override
    @Transactional(readOnly = true) //springframework.transaction.annotation - solo de lectura
    public List<Contacto> listar() {
        return (List<Contacto>) repository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Contacto> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Contacto guardar(Contacto contacto) {
        return repository.save(contacto);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
    @Override
    public Optional<Contacto> porDni(String dni) {return repository.findByDni(dni);}
}