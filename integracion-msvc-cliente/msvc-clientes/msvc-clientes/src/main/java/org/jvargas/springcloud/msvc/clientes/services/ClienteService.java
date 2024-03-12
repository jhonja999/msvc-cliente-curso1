package org.jvargas.springcloud.msvc.clientes.services;

import org.jvargas.springcloud.msvc.clientes.models.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> listar();
    Optional<Cliente> porId(Long id);
    Cliente guardar(Cliente usuario);
    void eliminar(Long id);

    Optional<Cliente> porEmail(String email);
}
