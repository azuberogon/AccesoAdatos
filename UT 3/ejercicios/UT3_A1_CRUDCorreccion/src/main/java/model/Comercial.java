package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "comercial")
public class Comercial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int unsigned")
    private Long comercialID;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido1", nullable = false, length = 100)
    private String apellido1;

    @Column(name = "apellido2", length = 100)
    private String apellido2;

    @Column(name = "comision")
    private Float comision;

    @OneToMany(mappedBy = "comercial", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos; // Relación 1:N con Pedido

    public Comercial() {}

    // Getters y Setters
    public Long getComercialID() {
        return comercialID;
    }

    public void setComercialID(Long comercialID) {
        this.comercialID = comercialID;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

/*
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

    @Override
    public String toString() {
        return "Vendedor nº: "+comercialID+"\n"
                + "\tNombre = " + apellido1 + " " + apellido2 + "," + nombre + "\n"
                + "\tComisión = " + comision + "\n\n";
    }
}
*/