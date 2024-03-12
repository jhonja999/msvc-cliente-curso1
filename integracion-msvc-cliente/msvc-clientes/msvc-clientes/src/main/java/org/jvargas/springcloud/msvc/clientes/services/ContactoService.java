package org.jvargas.springcloud.msvc.clientes.services;

import org.jvargas.springcloud.msvc.clientes.models.entity.Contacto;
import org.jvargas.springcloud.msvc.clientes.repositories.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ContactoService {

    List<Contacto> listar(); //lista  user
    Optional<Contacto> porId(Long id); //te devuelve un solo valor
    Contacto guardar(Contacto contacto); //para gaurdar los datos o un user
    void eliminar(Long id);

    Optional<Contacto> porDni(String dni);
}