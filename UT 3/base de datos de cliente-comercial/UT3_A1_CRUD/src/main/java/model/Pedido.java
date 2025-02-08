package model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Pedido")
public class Pedido {
    @Id
    @Column(columnDefinition = "int unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoID;
    @Column(name="total", nullable = false)
    private Double total;
    @Temporal(TemporalType.DATE)
    @Column(name="fecha")
    private Date fecha;

    /**@JoinColumn(name = "clienteID"): crea
    una clave foránea en la tabla Pedido,
    apuntando a la columna cliente_id en
    Cliente */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clienteID", nullable = false)
    private Cliente cliente;
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    //Constructor
    public Pedido() {}
    // Getters y Setters
    public Long getpedidoID() {return pedidoID;}
    public void setpedidoID(Long pedidoID) {this.pedidoID = pedidoID;}

    public Double getTotal(){return this.total;}
    public void setTotal(Double total){this.total = total;}

    public Date getFecha(){return fecha;}
    public void setFecha(Date fecha){this.fecha = fecha;}

    @Override
    public String toString() {
        return "Pedido nº: "+pedidoID+"\n"
                + "\tMonto total = " + total + "\n"
                + "\tFecha de pedido = " + fecha + "\n\n";
    }
}
