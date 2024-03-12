package org.jvargas.springcloud.msvc.cursos.repositories;

import org.jvargas.springcloud.msvc.cursos.models.entity.Curso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
    @Modifying
    @Query("delete from CursoCliente cu where cu.clienteId=?1")
    void eliminarCursoUsuarioPorId(Long id);

}


