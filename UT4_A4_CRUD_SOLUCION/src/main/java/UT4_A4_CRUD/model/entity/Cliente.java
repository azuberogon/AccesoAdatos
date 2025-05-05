package UT4_A4_CRUD.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="nombre", nullable = false, length = 100)
    private String nombre;
    @Column(name="apellido1", nullable = false, length = 100)
    private String apellido1;
    @Column(name="apellido2", length =  100)
    private String apellido2;
    @Column(name="ciudad", length = 100)
    private String ciudad;
    @Column(name = "categoria")
    private Long categoria;

    // Getters y Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNombre(){return this.nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}

    public String getApellido1(){return apellido1;}
    public void setApellido1(String apellido1){this.apellido1 = apellido1;}

    public String getApellido2(){return apellido2;}
    public void setApellido2(String apellido2){this.apellido2 = apellido2;}

    public String getCiudad(){return ciudad;}
    public void setCiudad(String ciudad){this.ciudad = ciudad;}

    public Long getCategoria(){return categoria;}
    public void setCategoria(Long categoria){this.categoria = categoria;}

}
