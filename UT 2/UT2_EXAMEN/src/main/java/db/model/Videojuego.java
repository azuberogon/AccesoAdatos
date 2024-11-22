package db.model;

import java.time.LocalDate;

public class Videojuego {
    private int videojuegoID;
    private String titulo;
    private String plataforma;
    private Integer anioLanzamiento;
    private Double precio;
    private LocalDate fechaActualizacion;
    private boolean disponible;
    private Historia historia;

    public Videojuego(int videojuegoID, String titulo, String plataforma, Integer anioLanzamiento, Double precio, LocalDate fechaActualizacion, boolean disponible, Historia historia) {
        this.videojuegoID = videojuegoID;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.anioLanzamiento = anioLanzamiento;
        this.precio = precio;
        this.fechaActualizacion = fechaActualizacion;
        this.disponible = disponible;
        this.historia = historia;
    }

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

    public Integer getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(Integer anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
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
        return "(" + anioLanzamiento + ") Videojuego [" + videojuegoID + "]" +
                "- " + titulo +
                ", " + plataforma +
                ", " + precio +
                "€, " + fechaActualizacion +
                ", disponible=" + disponible;
    }
}
