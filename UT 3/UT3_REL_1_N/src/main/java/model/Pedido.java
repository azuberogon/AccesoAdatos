package model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedidoID;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Fecha_hora")
    private Date fechaHora;

    // Relación Muchos a Uno con Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clienteID", nullable = false)
    private Cliente cliente; // aqui se le añade al pedido un cliente en este punto el cliente tiene el pedido

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido() {}

    public int getPedidoID() {
        return pedidoID;
    }

    public void setPedidoID(int pedidoID) {
        this.pedidoID = pedidoID;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "pedidoID=" + pedidoID +
                ", fechaHora=" + fechaHora +
                '}';
    }
}
