package org.jvargas.springcloud.msvc.clientes.controllers;


import jakarta.validation.Valid;
import org.jvargas.springcloud.msvc.clientes.models.entity.Cliente;
import org.jvargas.springcloud.msvc.clientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/cliente")
@RestController
public class ClienteController {
    @Autowired
    private ClienteService service;


    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Cliente> usuarioOptional = service.porId(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Cliente usuario, BindingResult result) {
        //recoger errores
        if (service.porEmail(usuario.getEmail()).isPresent()){
            return  ResponseEntity.badRequest().body(Collections.singletonMap("Vargas Malaver, Jhon Jairo Raúl ☻","Ya existe usuario con ese Email!"));
        }



        if (result.hasErrors()){
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        //ctr+alt+m , se crea como metodo

        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "Vargas Malaver " + err.getField()
                    + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);

    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Cliente usuario, BindingResult result, @PathVariable Long id) {


        if (result.hasErrors()){
            return validar(result);
        }

        Optional<Cliente> op = service.porId(id);
        if (op.isPresent()) {
            Cliente usuarioDB = op.get();
            //objeto encontrado
            if (!usuario.getEmail().equalsIgnoreCase(usuarioDB.getEmail())
                    && service.porEmail(usuario.getEmail()).isPresent()){
                return ResponseEntity.badRequest(). body(Collections.singletonMap("Vargas Malaver, Jhon Jairo Raúl ☻","Ya existe el Email en la Data!"));
            }

            usuarioDB.setName(usuario.getName());
            usuarioDB.setEmail(usuario.getEmail());
            usuarioDB.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDB));
        }else{
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Cliente> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }



}
