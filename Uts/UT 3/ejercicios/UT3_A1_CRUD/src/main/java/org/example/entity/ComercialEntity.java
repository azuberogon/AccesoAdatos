package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comercial", schema = "ut3accesoadatoshibernetempresa", catalog = "")
public class ComercialEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Object id;
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
    @Column(name = "comision")
    private double comision;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
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

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }
}
