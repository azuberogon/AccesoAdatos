package org.example.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "comercial", schema = "bdgestionventas")
public class ComercialEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "comercialID")
    private Long comercialId;
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
    private Double comision;



    public Long getComercialId() {
        return comercialId;
    }

    public void setComercialId(Long comercialId) {
        this.comercialId = comercialId;
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

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ComercialEntity that = (ComercialEntity) o;
            return Objects.equals(comercialId, that.comercialId) && Objects.equals(nombre, that.nombre)
                   && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2)
                   && Objects.equals(comision, that.comision);
        }

    @Override
    public int hashCode() {
        return Objects.hash(comercialId, nombre, apellido1, apellido2, comision);
    }


    @Override
    public String toString() {
        return "ComercialEntity{" +
                "comercialId=" + comercialId +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", comision=" + comision +
                '}';
    }

}
