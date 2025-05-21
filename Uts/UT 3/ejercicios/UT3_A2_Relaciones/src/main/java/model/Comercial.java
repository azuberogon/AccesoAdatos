package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Comercial")
public class Comercial {
    @Id
    @Column(columnDefinition = "int unsigned")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comercialID;
    @Column(name="nombre", nullable = false, length = 100)
    private String nombre;
    @Column(name="apellido1", nullable = false, length = 100)
    private String apellido1;
    @Column(name="apellido2", length =  100)
    private String apellido2;
    @Column(name = "comision")
    private Float comision;

    //TODO añadimos la relación. CascadeType.ALL garantiza la consistencia de los datos entre tablas.
    // Inicializamos la lista vacia para evitar nullpointer de getPedidos()
    @OneToMany(mappedBy = "comercial", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos = new ArrayList<>();

    //Constructor
    public Comercial() {}
    // Getters y Setters
    public Long getComercialID() {return comercialID;}
    public void setComercialID(Long comercialID) {this.comercialID = comercialID;}

    public String getNombre(){return this.nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}

    public String getApellido1(){return apellido1;}
    public void setApellido1(String apellido1){this.apellido1 = apellido1;}

    public String getApellido2(){return apellido2;}
    public void setApellido2(String apellido2){this.apellido2 = apellido2;}

    public Float getComision(){return comision;}
    public void setComision(Float comision){this.comision = comision;}

    //TODO Getter y setter para la nueva relación
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    //TODO cuidado con modificar el metodo para que imprima los pedidos
    // ya que podríamos caer en un bucle si lo hacemos en el resto de clases
    @Override
    public String toString() {
        return "Vendedor nº: "+comercialID+"\n"
                + "\tNombre = " + apellido1 + " " + apellido2 + "," + nombre + "\n"
                + "\tComisión = " + comision + "\n";
    }
}
