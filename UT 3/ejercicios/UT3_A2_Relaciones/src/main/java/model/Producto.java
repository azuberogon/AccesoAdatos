package model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @Column(columnDefinition = "int unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoID;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PedidoProducto> pedidoProductos = new ArrayList<>();

    public Producto() {}

    public Long getProductoID() { return productoID; }
    public void setProductoID(Long productoID) { this.productoID = productoID; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public List<PedidoProducto> getPedidoProductos() { return pedidoProductos; }
    public void setPedidoProductos(List<PedidoProducto> pedidoProductos) {
        this.pedidoProductos = pedidoProductos;
    }

    @Override
    public String toString() {
        return "Producto nº: " + productoID + "\n"
                + "\tNombre = " + nombre + "\n"
                + "\tPrecio = " + precio + "\n";
    }
}
