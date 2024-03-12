package org.jvargas.springcloud.msvc.clientes.repositories;
import org.jvargas.springcloud.msvc.clientes.models.entity.Contacto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContactoRepository extends CrudRepository<Contacto, Long> {
    Optional<Contacto> findByDni(String dni);
}
