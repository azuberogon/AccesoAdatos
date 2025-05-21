package model;

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
    private int cantidad;
    public Contiene()
    {

    }
    public Contiene(Pedido pedido, Producto producto, int cantidad)
    {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.contieneID = new ContieneID(pedido.getpedidoID(), producto.getProductoID());
    }
}
