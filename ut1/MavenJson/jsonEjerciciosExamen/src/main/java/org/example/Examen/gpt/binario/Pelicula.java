package org.example.Examen.gpt.binario;

import java.io.Serializable;

public class Pelicula implements Serializable {
    private String titulo;
    private String fechaDeEstreno;
    private String director;

    public Pelicula(String titulo, String fechaDeEstreno, String director) {
        this.titulo = titulo;
        this.fechaDeEstreno = fechaDeEstreno;
        this.director = director;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + "\nFecha de estreno: " + fechaDeEstreno + "\nDirector: " + director + "\n";
    }
}
