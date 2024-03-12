package org.jvargas.springcloud.msvc.clientes.repositories;

import org.jvargas.springcloud.msvc.clientes.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente, Long > {

    Optional<Cliente> findByEmail(String email);

}
