package org.example.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "pedido", schema = "ut3accesoadatoshibernetempresa", catalog = "")
public class PedidoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Object id;
    @Basic
    @Column(name = "total")
    private double total;
    @Basic
    @Column(name = "fecha")
    private Date fecha;
    @Basic
    @Column(name = "id_cliente")
    private Object idCliente;
    @Basic
    @Column(name = "id_comercial")
    private Object idComercial;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
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

    public Object getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Object idCliente) {
        this.idCliente = idCliente;
    }

    public Object getIdComercial() {
        return idComercial;
    }

    public void setIdComercial(Object idComercial) {
        this.idComercial = idComercial;
    }
}
