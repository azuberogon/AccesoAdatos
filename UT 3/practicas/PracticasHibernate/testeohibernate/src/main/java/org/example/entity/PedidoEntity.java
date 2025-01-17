package org.example.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "pedido", schema = "bdgestionventas")
public class PedidoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pedidoID")
    private Long pedidoId;
    @Basic
    @Column(name = "total")
    private double total;
    @Basic
    @Column(name = "fecha")
    private Date fecha;
    @Basic
    @Column(name = "clienteID")
    private Long clienteId;
    @Basic
    @Column(name = "comercialID")
    private Long comercialId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clienteID", nullable = false)
    private ClienteEntity cliente;
    public ClienteEntity getCliente() {
        return cliente;
    }
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }



    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
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

    public Object getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Object getComercialId() {
        return comercialId;
    }

    public void setComercialId(Long comercialId) {
        this.comercialId = comercialId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoEntity that = (PedidoEntity) o;
        return Double.compare(total, that.total) == 0 && Objects.equals(pedidoId, that.pedidoId) && Objects.equals(fecha, that.fecha) && Objects.equals(clienteId, that.clienteId) && Objects.equals(comercialId, that.comercialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoId, total, fecha, clienteId, comercialId);
    }

    @Override
    public String toString() {
        return "PedidoEntity{" +
                "pedidoId=" + pedidoId +
                ", total=" + total +
                ", fecha=" + fecha +
                ", clienteId=" + clienteId +
                ", comercialId=" + comercialId +
                '}';
    }

}
