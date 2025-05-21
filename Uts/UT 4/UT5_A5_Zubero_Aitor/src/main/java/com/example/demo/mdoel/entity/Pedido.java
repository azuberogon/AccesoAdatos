package com.example.demo.mdoel.entity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;
    private Double total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    public Pedido(Long id, Date fecha, Double total, Cliente cliente) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.cliente = cliente;
    }

    public Pedido() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}

