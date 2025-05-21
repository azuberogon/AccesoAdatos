package com.example.demo.entity;

import java.util.List;

public class Disco {
    private Long id;
    private String titulo ;
    private int anio ;
    private List<Cancion> canciones;

    public Disco() {
    }

    public Disco(Long id, String titulo, int anio, List<Cancion> canciones) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.canciones = canciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
}
