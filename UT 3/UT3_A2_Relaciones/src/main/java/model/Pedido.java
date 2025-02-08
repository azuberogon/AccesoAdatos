package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Pedido")
public class Pedido {


    @Id
    @Column(columnDefinition = "int unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoID;
    @Column(name = "total", nullable = false)
    private Double total;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;

    //TODO Añado las relaciones y claves foráneas con las otras tablas.
    // En este caso si creo un pedido me va a crear tambien el cliente asociado,
    // pero no el comercial (CascadeType.ALL solo lo aplico a la relacion con cliente)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "clienteID", nullable = false)
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comercialID")
    private Comercial comercial;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Contiene> contiene = new ArrayList<>();

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Comercial getComercial() {
        return comercial;
    }

    public void setComercial(Comercial comercial) {
        this.comercial = comercial;
    }

    public List<Contiene> getContiene() {
        return contiene;
    }

    public void setContiene(List<Contiene> contiene) {
        this.contiene = contiene;
    }
    //TODO cuidado con modificar el metodo para que imprima cliente y comercial
    // ya que podríamos caer en un bucle si lo hacemos en el resto de clases
    @Override
    public String toString() {
        return "Pedido nº: " + pedidoID + "\n"
                + "\tMonto total = " + total + "\n"
                + "\tFecha de pedido = " + fecha + "\n";
    }
}
