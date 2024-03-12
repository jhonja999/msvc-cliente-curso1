package org.jvargas.springcloud.msvc.clientes.controllers;

import org.jvargas.springcloud.msvc.clientes.models.entity.Contacto;
import org.jvargas.springcloud.msvc.clientes.services.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/contactos")
public class ContactoController {

    @Autowired
    private ContactoService service;

    @GetMapping
    public List<Contacto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Contacto> op = service.porId(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Contacto contacto, BindingResult result) {
        if(service.porDni(contacto.getDni()).isPresent()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("Errou!!", "Este cliente ya existe"));
        }
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(contacto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Contacto contacto,BindingResult result,  @PathVariable Long id) {
        if (result.hasErrors()) {
            return getMapResponseEntity(result);
        }

        Optional<Contacto> op = service.porId(id);
        if (op.isPresent()) {
            Contacto contactoDB = op.get();

            if(!contacto.getDni().equalsIgnoreCase(contactoDB.getDni()) && service.porDni(contacto.getDni()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("Errou!!! ", "Ya existe el Dni en otro cliente"));
            }
            contactoDB.setCiudad(contacto.getCiudad());
            contactoDB.setPais(contacto.getPais());
            contactoDB.setTelefono(contacto.getTelefono());
            contactoDB.setDni(contacto.getDni());
            contactoDB.setNumeroReserva(contacto.getNumeroReserva());
            contactoDB.setFechaCheckIn(contacto.getFechaCheckIn());
            contactoDB.setFechaCheckOut(contacto.getFechaCheckOut());
            contactoDB.setTipoHabitacionReservada(contacto.getTipoHabitacionReservada());
            contactoDB.setNumPiso(contacto.getNumPiso());
            contactoDB.setCondicionEspecial(contacto.getCondicionEspecial());
            contactoDB.setNecesitaCochera(contacto.getNecesitaCochera());
            contactoDB.setDatosFacturacion(contacto.getDatosFacturacion());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(contactoDB));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Contacto> op = service.porId(id);
        if (op.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    private ResponseEntity<Map<String, String>> getMapResponseEntity(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "Error!!! " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}