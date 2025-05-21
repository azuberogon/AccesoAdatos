package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Contiene")
public class Contiene {
    @EmbeddedId
    private ContieneID contieneID = new ContieneID();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pedidoID")
    @JoinColumn(name = "pedidoID", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productoID")
    @JoinColumn(name = "productoID", nullable = false)
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    public Contiene(){}

    public Contiene(Pedido pedido, Producto producto, Integer cantidad){
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.contieneID = new ContieneID(pedido.getId(), producto.getId());
    }

    public ContieneID getContieneID() {
        return contieneID;
    }

    public void setContieneID(ContieneID contieneID) {
        this.contieneID = contieneID;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return  "ID: " + contieneID + "\n"
                + "\tCantidad = " + cantidad + "\n\n";
    }
}
