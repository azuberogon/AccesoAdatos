package model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoID;
    @Column(name = "ciudad", nullable = false, length = 100, unique = true)
    private String ciudad;
    @Column(name = "total", nullable = false, length = 100, unique = true)
    private Double total;
    @Column(name = "categoria", nullable = false, length = 100, unique = true)
    private Long categoria;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;


    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Long getCategoria() {
        return categoria;
    }

    public void setCategoria(Long categoria) {
        this.categoria = categoria;
    }
    public Long getPedidoID() {
        return pedidoID;
    }

    public void setPedidoID(Long pedidoID) {
        this.pedidoID = pedidoID;
    }
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
