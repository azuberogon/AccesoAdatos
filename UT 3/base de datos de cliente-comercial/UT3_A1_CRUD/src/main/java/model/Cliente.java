package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Cliente")
public class Cliente {
    @Id
    @Column(columnDefinition = "int unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteID;
    @Column(name="nombre", nullable = false, length = 100)
    private String nombre;
    @Column(name="apellido1", nullable = false, length = 100)
    private String apellido1;
    @Column(name="apellido2", length =  100)
    private String apellido2;
    @Column(name="ciudad", length = 100)
    private String ciudad;
    @Column(name = "categoria", columnDefinition = "int unsigned")
    private Long categoria;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    public Cliente() {}
    // Getters y Setters
    public Long getClienteID() {return clienteID;}
    public void setClienteID(Long clienteID) {this.clienteID = clienteID;}

    public String getNombre(){return this.nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}

    public String getApellido1(){return apellido1;}
    public void setApellido1(String apellido1){this.apellido1 = apellido1;}

    public String getApellido2(){return apellido2;}
    public void setApellido2(String apellido2){this.apellido2 = apellido2;}

    public String getCiudad(){return ciudad;}
    public void setCiudad(String ciudad){this.ciudad = ciudad;}

    public Long getCategoria(){return categoria;}
    public void setCategoria(Long categoria){
        if(categoria >= 0) {this.categoria = categoria;} else {this.categoria = 0L;}
    }

    @Override
    public String toString() {
        return "Cliente nº: "+clienteID+"\n"
                + "\tNombre = " + apellido1 + " " + apellido2 + "," + nombre + "\n"
                + "\tLocalidad = " + ciudad + "\n"
                + "\tCategoría = " + categoria + "\n\n";
    }

}
