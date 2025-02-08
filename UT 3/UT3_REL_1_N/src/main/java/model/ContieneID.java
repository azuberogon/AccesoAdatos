package model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ContieneID implements Serializable {


    private int pedidoID;
    private int videojuegoID;
    public ContieneID() {
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
    // Constructor vacío


    // Constructor con campos
    public ContieneID(int pedidoID, int videojuegoID) {
        this.pedidoID = pedidoID;
        this.videojuegoID = videojuegoID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContieneID that = (ContieneID) o;
        return pedidoID == that.pedidoID && videojuegoID == that.videojuegoID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedidoID, videojuegoID);
    }
}