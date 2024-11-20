package org.example.Examen.gpt.binario;

import java.io.Serializable;
import java.util.ArrayList;

public class Trilogia implements Serializable {
    private String nombre;
    private ArrayList<Pelicula> peliculas;
    private ArrayList<String> productores;

    public Trilogia(String nombre) {
        this.nombre = nombre;
        this.peliculas = new ArrayList<>();
        this.productores = new ArrayList<>();
    }

    public void agregarPelicula(Pelicula pelicula) {
        peliculas.add(pelicula);
    }

    public void agregarProductor(String productor) {
        productores.add(productor);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(nombre).append("\nPelículas:\n");
        for (Pelicula pelicula : peliculas) {
            sb.append(pelicula.toString()).append("\n");
        }
        sb.append("Productores:\n");
        for (String productor : productores) {
            sb.append(productor).append("\n");
        }
        return sb.toString();
    }
}
