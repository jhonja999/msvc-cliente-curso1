package org.jvargas.springcloud.msvc.cursos.clients;
import org.jvargas.springcloud.msvc.cursos.models.Cliente;
import org.jvargas.springcloud.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-clientes",url = "localhost:8002/api/cliente")
public interface ClientesClientRest {
    //2 metodos detalle(id) y crear(obj)

    @GetMapping("/{id}")
    Cliente detalle(@PathVariable Long id);

    @PostMapping()
    Cliente crear(@RequestBody Cliente cliente);

    @GetMapping("/clientes-por-curso")
    List<Cliente> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);


}
