package com.example.demo.entity;

import java.util.List;

public class Grupo {

    private String id;
    private String nombre;
    private String genero;
    private String pais;
    private List<Disco> discos;

    public Grupo() {
    }

    public Grupo(String id, String nombre, String genero, String pais, List<Disco> discos) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.pais = pais;
        this.discos = discos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(List<Disco> discos) {
        this.discos = discos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", genero='" + genero + '\'' +
                ", pais='" + pais + '\'' +
                ", discos=" + discos +
                '}';
    }
}
