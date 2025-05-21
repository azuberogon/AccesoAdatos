package db.model;

public class Historia {
    // TODO START
    private int historiaID;
    private String titulo;
    private String descripcion;

    public Historia(int historiaID, String titulo, String descripcion) {
        this.historiaID = historiaID;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public int getHistoriaID() {
        return historiaID;
    }

    public void setHistoriaID(int historiaID) {
        this.historiaID = historiaID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // TODO END

    @Override
    public String toString() {
        return "Historia[" + historiaID + "] - " + titulo + ": \n\t" + descripcion;
    }
}
