package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @Column(columnDefinition = "int unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private double precio;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch
            = FetchType.LAZY)
    private List<Contiene> contenidoEn = new ArrayList<>();

    public Producto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Contiene> getContenidoEn() {
        return contenidoEn;
    }

    public void setContenidoEn(List<Contiene> contenidoEn) {
        this.contenidoEn = contenidoEn;
    }

    @Override
    public String toString() {
        return  "Producto: ["+id+"] " + nombre + "\n"
                + "\tPrecio = " + precio + "\n\n";
    }

}
