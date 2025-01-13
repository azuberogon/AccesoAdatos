package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clienteID;

    @Size(min = 3, message = "No se aceptan nombre más cortos que 3 caracteres")
    @Column(name = "Nombre", nullable = false, length = 30, unique = false)
    private String nombre;

    @Column(name = "Apellidos", nullable = false, length = 50)
    private String apellidos;

    @Temporal(TemporalType.DATE)
    @Column(name = "F_Nacimiento")
    private Date fNacimiento;

    // Relación 1 a Muchos con Pedido
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();

    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Cliente() {}

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public @Size(min = 3, message = "No se aceptan nombre más cortos que 3 caracteres") String getNombre() {
        return nombre;
    }

    public void setNombre(@Size(min = 3, message = "No se aceptan nombre más cortos que 3 caracteres") String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(Date fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "clienteID=" + clienteID +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fNacimiento=" + fNacimiento +
                '}';
    }
}
