package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Historia")
public class Historia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int historiaID;

    @Size(min = 3, message = "No se aceptan títulos más cortos que 3 caracteres")
    @Column(name = "Titulo", nullable = false, length = 50, unique = true)
    private String titulo;

    @Column(name = "Descripcion", nullable = false, length = 300)
    private String descripcion;

    // Constructor, getters y setters

    public Historia() {}

    public int getHistoriaID() {
        return historiaID;
    }

    public void setHistoriaID(int historiaID) {
        this.historiaID = historiaID;
    }

    public @Size(min = 3, message = "No se aceptan títulos más cortos que 3 caracteres") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@Size(min = 3, message = "No se aceptan títulos más cortos que 3 caracteres") String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "[" + historiaID + "] Historia - '" + titulo + "': " + descripcion;
    }
}
