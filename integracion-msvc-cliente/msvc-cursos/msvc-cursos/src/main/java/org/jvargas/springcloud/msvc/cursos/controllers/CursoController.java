package org.jvargas.springcloud.msvc.cursos.controllers;

import feign.FeignException;
import org.jvargas.springcloud.msvc.cursos.models.Cliente;
import org.jvargas.springcloud.msvc.cursos.models.entity.Curso;
import org.jvargas.springcloud.msvc.cursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/curso")
public class CursoController {
    @Autowired
    private CursoService service;
    @GetMapping
    public ResponseEntity<List<Curso>> listar(){
        return ResponseEntity.ok(service.listar());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Curso> op = service.porId(id);
        if(op.isPresent()){
            return ResponseEntity.ok(op.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody Curso curso){
        Curso cursoDb = service.guardar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDb);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id){
        Optional<Curso> op = service.porId(id);
        if(op.isPresent()){
            Curso cursoDb = op.get();
            cursoDb.setNombre(curso.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cursoDb));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Curso> op = service.porId(id);
        if(op.isPresent()){
            service.eliminar(op.get().getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/asignar-cliente/{cursoId}")
    public ResponseEntity<?> asignarUsuario(@RequestBody Cliente cliente, @PathVariable Long cursoId){
        Optional<Cliente> o;

        try {
            o=service.asignarCliente(cliente, cursoId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(
                    "mensaje","No existe el usuario por el id o error en la comunicación: "+e.getMessage()));
        }

        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/crear-cliente/{cursoId}")
    public ResponseEntity<?> crearUsuario(@RequestBody Cliente cliente, @PathVariable Long cursoId){
        Optional<Cliente> o;

        try {
            o=service.crearCliente(cliente, cursoId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(
                    "mensaje","No se pudo crear el usuario o error en la comunicación: "+e.getMessage()));
        }

        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-cliente/{cursoId}")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Cliente cliente, @PathVariable Long cursoId){
        Optional<Cliente> o;

        try {
            o=service.eliminarUsuario(cliente, cursoId);
        }catch(FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(
                    "mensaje","No existe el usuario por el id o error en la comunicación: "+e.getMessage()));
        }

        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }

        return ResponseEntity.notFound().build();
    }
    @GetMapping("/curUsers/{id}")
    public ResponseEntity<?> detalleCurUsers(@PathVariable Long id){
        Optional<Curso> op = service.porIdConUsuarios(id);
        if(op.isPresent()){
            return ResponseEntity.ok(op.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/eliminar-curuser/{id}")
    public ResponseEntity<?> eliminarCursoUsuarioPorId(@PathVariable Long id){
        service.eliminarCursoUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }

}
