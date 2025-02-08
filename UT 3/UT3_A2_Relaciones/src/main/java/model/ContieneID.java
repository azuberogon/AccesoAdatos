package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class ContieneID implements Serializable {

    private Long pedidoID;
    private Long videojuegoID;
    // Constructor vacío
    public ContieneID() {}
    // Constructor con campos
    public ContieneID(Long pedidoID, Long videojuegoID) {
        this.pedidoID = pedidoID;
        this.videojuegoID = videojuegoID;
    }
    public Long getPedidoID() {
        return pedidoID;
    }

    public void setPedidoID(Long pedidoID) {
        this.pedidoID = pedidoID;
    }

    public Long getVideojuegoID() {
        return videojuegoID;
    }

    public void setVideojuegoID(Long videojuegoID) {
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