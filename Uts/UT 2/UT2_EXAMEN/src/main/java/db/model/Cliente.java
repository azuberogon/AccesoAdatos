package db.model;

import java.time.LocalDate;
import java.util.List;

public class Cliente {
    private int clienteID;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private List<Integer> pedidos;

    public Cliente(int clienteID, String nombre, String apellidos, LocalDate fechaNacimiento, List<Integer> pedidos) {
        this.clienteID = clienteID;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.pedidos = pedidos;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Integer> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Integer> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Cliente[" + clienteID + "]:\n" +
                "\tNombre: " + nombre + " " + apellidos +
                "\n\tFecha nacimiento: " + fechaNacimiento;
    }
}
