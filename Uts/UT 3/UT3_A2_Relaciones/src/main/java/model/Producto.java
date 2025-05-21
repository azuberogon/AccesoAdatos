package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Producto {


    @Id
    @Column(columnDefinition = "int unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long productoId;

    @Column(name = "nombre", length =100 , nullable = false)
    private String nombre;
    @Column(name = "precio", nullable = false)
    private double precio;
    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL, fetch
            = FetchType.LAZY)
    private List<Contiene> contenidoEn = new ArrayList<>();



    public long getProductoId() {
        return productoId;
    }

    public void setProductoId(long productoId) {
        this.productoId = productoId;
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
        return "Producto{" +
                "productoId=" + productoId +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", contenidoEn=" + contenidoEn +
                '}';
    }




}
