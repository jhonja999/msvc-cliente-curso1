package org.jvargas.springcloud.msvc.cursos.models.entity;

import jakarta.persistence.*;
import org.jvargas.springcloud.msvc.cursos.models.Cliente;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "curso_id")
    private List<CursoCliente> cursoClientes;//relacion de ids

    @Transient
    private List<Cliente> clientes;

    public Curso(){
        cursoClientes = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addCursoUsuario(CursoCliente cursoUsuario){
        cursoClientes.add(cursoUsuario);
    }
    public void removeCursoUsuario(CursoCliente cursoUsuario){
        cursoClientes.remove(cursoUsuario);
    }

    public List<CursoCliente> getCursoUsuarios() {
        return cursoClientes;
    }
    public void setCursoUsuarios(List<CursoCliente> cursoUsuarios) {
        this.cursoClientes = cursoUsuarios;
    }

    public List<Cliente> getUsuarios() {
        return clientes;
    }

    public void setUsuarios(List<Cliente> usuarios) {
        this.clientes = usuarios;
    }
}
