package model;

import jakarta.persistence.*;

@Entity
public class Contiene {
    @EmbeddedId
    private ContieneID contieneID = new ContieneID(); // Clave compuesta



    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pedidoID")
    @JoinColumn(name = "pedidoID", nullable = false)
    private Pedido pedido;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("videojuegoID")
    @JoinColumn(name = "videojuegoID", nullable = false)
    private Producto producto;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    public Contiene(){}
    public Contiene(Pedido pedidoID, Producto productoID, int cantidad) {
        this.pedido = pedidoID;
        this.producto = productoID;
        this.cantidad = cantidad;
        this.contieneID = new ContieneID(pedidoID.getPedidoID(),productoID.getProductoId());
    }


}
