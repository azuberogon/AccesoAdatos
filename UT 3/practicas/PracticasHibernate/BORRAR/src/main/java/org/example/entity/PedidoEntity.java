package org.example.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pedido", schema = "borrartesthibernate", catalog = "")
public class PedidoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "total")
    private double total;
    @Basic
    @Column(name = "fecha")
    private Date fecha;
    @Basic
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Basic
    @Column(name = "id_comercial")
    private Integer idComercial;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdComercial() {
        return idComercial;
    }

    public void setIdComercial(Integer idComercial) {
        this.idComercial = idComercial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PedidoEntity that = (PedidoEntity) o;
        return id == that.id && Double.compare(total, that.total) == 0 && Objects.equals(fecha, that.fecha) && Objects.equals(idCliente, that.idCliente) && Objects.equals(idComercial, that.idComercial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, total, fecha, idCliente, idComercial);
    }
}
