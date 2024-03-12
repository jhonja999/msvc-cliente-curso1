package org.jvargas.springcloud.msvc.clientes.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La ciudad no puede estar en blanco")
    private String ciudad;

    @NotBlank(message = "El país no puede estar en blanco")
    private String pais;

    @NotBlank(message = "El número de teléfono no puede estar en blanco")
    @NotBlank
    @Pattern(regexp = "\\d{9}")
    private String telefono;

    @NotBlank(message = "El DNI no puede estar en blanco")
    @Column(unique = true)
    @Pattern(regexp = "\\d{8}")
    private String dni;

    @NotBlank(message = "El número de reserva no puede estar en blanco")
    private String numeroReserva;

    @NotBlank(message = "La fecha de check-in no puede estar en blanco")
    private String fechaCheckIn;

    @NotBlank(message = "La fecha de check-out no puede estar en blanco")
    private String fechaCheckOut;

    @NotBlank(message = "El tipo de habitación reservada no puede estar en blanco")
    private String tipoHabitacionReservada;

    private String numPiso;

    private String condicionEspecial;

    private Boolean necesitaCochera;

    private String numeroMembresia;
    @DecimalMin(value = "0.0")
    private String datosFacturacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumeroReserva() {
        return numeroReserva;
    }

    public void setNumeroReserva(String numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public String getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(String fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }

    public String getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(String fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }

    public String getTipoHabitacionReservada() {
        return tipoHabitacionReservada;
    }

    public void setTipoHabitacionReservada(String tipoHabitacionReservada) {
        this.tipoHabitacionReservada = tipoHabitacionReservada;
    }

    public String getNumPiso() {
        return numPiso;
    }

    public void setNumPiso(String numPiso) {
        this.numPiso = numPiso;
    }

    public String getCondicionEspecial() {
        return condicionEspecial;
    }

    public void setCondicionEspecial(String condicionEspecial) {
        this.condicionEspecial = condicionEspecial;
    }

    public Boolean getNecesitaCochera() {
        return necesitaCochera;
    }

    public void setNecesitaCochera(Boolean necesitaCochera) {
        this.necesitaCochera = necesitaCochera;
    }

    public String getNumeroMembresia() {
        return numeroMembresia;
    }

    public void setNumeroMembresia(String numeroMembresia) {
        this.numeroMembresia = numeroMembresia;
    }

    public String getDatosFacturacion() {
        return datosFacturacion;
    }

    public void setDatosFacturacion(String datosFacturacion) {
        this.datosFacturacion = datosFacturacion;
    }
}
