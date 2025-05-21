package db.model;

import java.time.LocalDate;
import java.util.List;

public class Pedido {
    private int pedidoID;
    private Cliente cliente;
    private LocalDate fecha;
    private List<Contiene> contienes;

    public Pedido(int pedidoID, Cliente cliente, LocalDate fecha, List<Contiene> contienes) {
        this.pedidoID = pedidoID;
        this.cliente = cliente;
        this.fecha = fecha;
        this.contienes = contienes;
    }

    public int getPedidoID() {
        return pedidoID;
    }

    public void setPedidoID(int pedidoID) {
        this.pedidoID = pedidoID;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Contiene> getContienes() {
        return contienes;
    }

    public void setContienes(List<Contiene> contienes) {
        this.contienes = contienes;
    }

    @Override
    public String toString() {
        return "Pedido[" + pedidoID + "] - Fecha:" + fecha + " - Cliente:\n" + cliente.getClienteID();
    }
}
