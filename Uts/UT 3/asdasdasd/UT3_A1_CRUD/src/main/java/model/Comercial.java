package model;

import jakarta.persistence.*;

@Entity
@Table(name = "Comercial")
public class Comercial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String nombre;
    @Column(name = "apellido1",  nullable = false, length = 100, unique = true)
    private String apellido1;
    @Column(name = "apellido2", length = 100, unique = true)
    private String apellido2;
    @Column(name = "comision", nullable = false, unique = true)
    private Float comision;


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

    public Float getComision() {
        return comision;
    }

    public void setComision(Float comision) {
        this.comision = comision;
    }

    @Override
    public String toString() {
        return "Comercial{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", comision=" + comision +
                '}';
    }
}
