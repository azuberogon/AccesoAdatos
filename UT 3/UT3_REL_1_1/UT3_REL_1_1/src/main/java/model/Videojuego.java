package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Videojuego")
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int videojuegoID;

    @Size(min = 3, message = "No se aceptan títulos más cortos que 3 caracteres")
    @Column(name = "Titulo", nullable = false, length = 100, unique = true)
    private String titulo;

    @Column(name = "Plataforma", nullable = false, length = 20)
    private String plataforma;

    @Min(value = 1900, message = "El año de lanzamiento debe ser como mínimo 1900")
    @Max(value = 2100, message = "El año de lanzamiento debe ser como máximo 2100")
    @Column(name = "Anio_Lanzamiento", nullable = false)
    private int anioLanzamiento;

    @Column(name = "Precio", precision = 10, scale = 2, nullable = false)
    private BigDecimal precio;

    @Temporal(TemporalType.DATE)
    @Column(name = "Fecha_Actualizacion")
    private Date fechaActualizacion;

    @Column(name = "Disponible", nullable = false)
    private boolean disponible;

    // Opciones de carga EAGER o LAZY
    @OneToOne(fetch = FetchType.LAZY)
    // Nombre de la columna de relación
    @JoinColumn(name = "historiaID", nullable = false, unique = true)
    private Historia historia;

    // Constructor sin parámetros
    public Videojuego() {}

    // Getters y Setters

    public int getVideojuegoID() {
        return videojuegoID;
    }

    public void setVideojuegoID(int videojuegoID) {
        this.videojuegoID = videojuegoID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Historia getHistoria() {
        return historia;
    }

    public void setHistoria(Historia historia) {
        this.historia = historia;
    }

    @Override
    public String toString() {
        return "["+videojuegoID+"] " + titulo + "(" + anioLanzamiento + "):\n"
                + "\tPlataforma = " + plataforma + "\n"
                + "\tPrecio = " + precio + "\n"
                + "\tDisponible = " + disponible + "\n"
                + "\tFechaActualizacion = " + fechaActualizacion + "\n\n";
    }
}
