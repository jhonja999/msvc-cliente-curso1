package org.jvargas.springcloud.msvc.clientes.services;
import org.jvargas.springcloud.msvc.clientes.models.entity.Cliente;
import org.jvargas.springcloud.msvc.clientes.models.entity.ServicioAdicional;

import java.util.*;

public interface ServicioAdicionalService {
    List<ServicioAdicional> listar(); //lista  user
    Optional<ServicioAdicional> porId(Long id); //te devuelve un solo valor
    ServicioAdicional guardar(ServicioAdicional empleado); //para gaurdar los datos o un user
    void eliminar(Long id);

    Optional<ServicioAdicional> porDni(String dni);
}