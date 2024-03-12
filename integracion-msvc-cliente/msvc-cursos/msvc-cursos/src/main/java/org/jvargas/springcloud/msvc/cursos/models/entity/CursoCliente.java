package org.jvargas.springcloud.msvc.cursos.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name="cursos_cliente")
public class CursoCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="cliente_id",unique = true)
    private Long clienteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public boolean equals(Object obj){
        if (this==obj){
            return true;
        }
        if (!(obj instanceof CursoCliente)){
            return false;
        }
        CursoCliente o=(CursoCliente) obj;
        return this.clienteId != null && this.clienteId.equals(o.clienteId);
    }
}
