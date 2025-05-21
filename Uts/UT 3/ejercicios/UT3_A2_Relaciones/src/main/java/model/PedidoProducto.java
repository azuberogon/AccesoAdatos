package model;

import jakarta.persistence.*;

@Entity
@Table(name = "PedidoProducto")
public class PedidoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int unsigned")
    private Long pedidoProductoID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedidoID", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productoID", nullable = false)
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    public PedidoProducto() {}

    public Long getPedidoProductoID() { return pedidoProductoID; }
    public void setPedidoProductoID(Long pedidoProductoID) { this.pedidoProductoID = pedidoProductoID; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    @Override
    public String toString() {
        return "PedidoProducto nº: " + pedidoProductoID + "\n"
                + "\tPedido ID = " + pedido + "\n"
                + "\tProducto ID = " + producto.getProductoID() + "\n"
                + "\tCantidad = " + cantidad + "\n";
    }
}
