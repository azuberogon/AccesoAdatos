package es.mariana.acda.ut5.acda_ut5.model.entity;


public class Libro {
    // ID del documento en Firestore
    private String id;
    private String titulo;
    private String autor;
    private int paginas;
    private boolean disponible;

    // Constructor vacío necesario para Firebase
    public Libro() {}

    // Getters y setters...
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
