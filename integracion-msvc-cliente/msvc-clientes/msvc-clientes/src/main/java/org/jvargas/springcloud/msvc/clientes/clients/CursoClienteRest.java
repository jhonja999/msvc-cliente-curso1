package org.jvargas.springcloud.msvc.clientes.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc-cursos", url="localhost:8003/api/curso")
public interface CursoClienteRest {
    @DeleteMapping("/eliminar-curuser/{id}")
    void eliminarCursoUsuarioPorId(@PathVariable Long id);
}
