package model;

import jakarta.persistence.*;

@Entity
@Table(name="Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String nombre;
    @Column(name = "apellido1", nullable = false, length = 100, unique = true)
    private String apellido1;
    @Column(name = "apellido2", length = 100, unique = true)
    private String apellido2;
    @Column(name = "ciudad", length = 100, unique = true)
    private String ciudad;
    @Column(name = "categoria", unique = true)
    private Long categoria;
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

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }



}