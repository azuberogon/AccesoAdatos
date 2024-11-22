package db.model;

public class ContieneID {
    private int pedidoID;
    private int videojuegoID;

    public ContieneID(int pedidoID, int videojuegoID) {
        this.pedidoID = pedidoID;
        this.videojuegoID = videojuegoID;
    }

    public int getPedidoID() {
        return pedidoID;
    }

    public void setPedidoID(int pedidoID) {
        this.pedidoID = pedidoID;
    }

    public int getVideojuegoID() {
        return videojuegoID;
    }

    public void setVideojuegoID(int videojuegoID) {
        this.videojuegoID = videojuegoID;
    }
}