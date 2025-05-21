package db.model;

public class Contiene {
    private ContieneID contieneID;
    private Integer cantidad;

    public Contiene(ContieneID contieneID, Integer cantidad) {
        this.contieneID = contieneID;
        this.cantidad = cantidad;
    }

    public ContieneID getContieneID() {
        return contieneID;
    }

    public void setContieneID(ContieneID contieneID) {
        this.contieneID = contieneID;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Contiene[P-" + contieneID.getPedidoID() + ", V-" + contieneID.getVideojuegoID() + "] - Cantidad:" + cantidad;
    }
}
