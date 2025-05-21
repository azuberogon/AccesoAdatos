package org.example.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cliente") // esto es el nombre de la tabla que esta representando
public class ClienteEntity {
    @Id // indicador de la primary key importante para enlazar tablas
    @GeneratedValue(strategy = GenerationType.IDENTITY)//autoincrement strategy = GenerationType.IDENTITY
    @Column(columnDefinition = "int unsigned")
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
    @Column(name = "ciudad")
    private String ciudad;
    @Basic
    @Column(name = "categoría")
    private Integer categoría;



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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getCategoría() {
        return categoría;
    }

    public void setCategoría(Integer categoría) {
        this.categoría = categoría;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntity that = (ClienteEntity) o;
        return id == that.id && Objects.equals(nombre, that.nombre) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(ciudad, that.ciudad) && Objects.equals(categoría, that.categoría);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido1, apellido2, ciudad, categoría);
    }


    @Override
    public String toString() {
        return "ClienteEntity{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", categoría=" + categoría +
                '}';
    }
}
