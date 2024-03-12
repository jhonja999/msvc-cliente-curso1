package org.jvargas.springcloud.msvc.clientes.services;

import org.jvargas.springcloud.msvc.clientes.models.entity.Cliente;
import org.jvargas.springcloud.msvc.clientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicempl implements ClienteService {

    @Autowired // inyecta la dependencia de una clase que tiene metodos a otra clase
    private ClienteRepository repository;

    @Override
    @Transactional(readOnly = true) //solo lectura
    public List<Cliente> listar() {
        return (List<Cliente>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Cliente guardar(Cliente usuario) {
        return repository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Cliente> porEmail(String email)  { return repository.findByEmail(email);}

}
