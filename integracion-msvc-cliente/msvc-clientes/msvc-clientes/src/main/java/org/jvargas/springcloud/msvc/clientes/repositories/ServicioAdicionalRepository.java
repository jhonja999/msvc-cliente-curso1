package org.jvargas.springcloud.msvc.clientes.repositories;

import org.jvargas.springcloud.msvc.clientes.models.entity.Contacto;
import org.jvargas.springcloud.msvc.clientes.models.entity.ServicioAdicional;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface ServicioAdicionalRepository extends CrudRepository<ServicioAdicional, Long> {
    Optional<ServicioAdicional> findByDni(String dni);
}