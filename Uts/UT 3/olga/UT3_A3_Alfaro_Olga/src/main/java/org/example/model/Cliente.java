package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @Column(columnDefinition = "int unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido1", nullable = false, length = 100)
    private String apellido1;

    @Column(name = "apellido2", nullable = true, length = 100)
    private String apellido2;

    @Column(name = "ciudad", nullable = false, length = 100)
    private String ciudad;

    @Column(name = "categoria", nullable = false, columnDefinition = "int unsigned")
    private int categoria;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(){}

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

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {

        String cliente =  "Cliente: ["+id+"] " + nombre + " " + apellido1 + " " + apellido2 + "\n"
                + "\tCiudad = " + ciudad + "\n"
                + "\tCategoria = " + categoria;

        return cliente;
    }
}
