package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Pedido")
public class Pedido {
    @Id
    @Column(columnDefinition = "int unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Total", nullable = false)
    private double total;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha", nullable = true)
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_comercial", nullable = true)
    private Comercial comercial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_cliente", nullable = true)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Contiene> contiene = new ArrayList<>();


    public Pedido(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Comercial getComercial() {
        return comercial;
    }

    public void setComercial(Comercial comercial) {
        this.comercial = comercial;
    }

    public List<Contiene> getContiene() {
        return contiene;
    }

    public void setContiene(List<Contiene> contiene) {
        this.contiene = contiene;
    }

    @Override
    public String toString() {
        return "Pedido: ["+id+"] " + "\n"
                + "\tTotal = " + total + "\n"
                + "\tFecha = " + fecha + "\n\n";
    }
}
