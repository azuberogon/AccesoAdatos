package com.example.demo.entity;


public class Cancion {

    private String id;
    private String titulo;
    private Double duracion;

    public Cancion() {
    }

    public Cancion(String id, String itulo, Double duracion) {
        this.id = id;
        this.titulo = itulo;
        this.duracion = duracion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }


}
