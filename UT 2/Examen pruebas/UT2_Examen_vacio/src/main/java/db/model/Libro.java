package db.model;


public class Libro {
    private Integer isbn;
    private String titulo;
    private String genero;
    private String autor;
    private Double precio;

    public Libro(int isbn, String titulo, String genero, String autor, double precio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.precio = precio;
    }

    //Getters y setters
    public Integer getIsbn() {
        return isbn;
    }
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //Para imprimir en pantalla
    @Override
    public String toString() {
        return "Libro con código ISBN " + isbn + "\n" +
                "\t- Título: " + titulo + "\n" +
                "\t- Tipo: " + genero + "\n"  +
                "\t- Autor: " + autor + "\n"  +
                "\t- Precio: " + precio + " €\n";
    }
}
