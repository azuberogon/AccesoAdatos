package org.example.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "comercial", schema = "borrartesthibernate", catalog = "")
public class ComercialEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "apellido1")
    private String apellido1;
    @Basic
    @Column(name = "apellido2")
    private String apellido2;
    @Basic
    @Column(name = "comisión")
    private Double comisión;

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

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Double getComisión() {
        return comisión;
    }

    public void setComisión(Double comisión) {
        this.comisión = comisión;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComercialEntity that = (ComercialEntity) o;
        return id == that.id && Objects.equals(nombre, that.nombre) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(comisión, that.comisión);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido1, apellido2, comisión);
    }
}
