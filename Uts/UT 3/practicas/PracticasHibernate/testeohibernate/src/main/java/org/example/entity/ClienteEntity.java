package org.example.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cliente", schema = "bdgestionventas")
public class ClienteEntity {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "clienteID", nullable = false)
    private Long clienteId;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "apellido1")
    private String apellido1;
    @Basic
    @Column(name = "apellido2")
    private String apellido2;
    @Basic
    @Column(name = "ciudad")
    private String ciudad;
    @Basic
    @Column(name = "categoria")
    private Long categoria;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PedidoEntity> pedidos = new ArrayList<>();
    public List<PedidoEntity> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<PedidoEntity> pedidos) {
        this.pedidos = pedidos;
    }
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteEntity that = (ClienteEntity) o;
        return Objects.equals(clienteId, that.clienteId) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido1, that.apellido1) && Objects.equals(apellido2, that.apellido2) && Objects.equals(ciudad, that.ciudad) && Objects.equals(categoria, that.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, nombre, apellido1, apellido2, ciudad, categoria);
    }

    @Override
    public String toString() {
        return "ClienteEntity{" +
                "clienteId=" + clienteId +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
