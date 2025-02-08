package model;

import java.io.Serializable;
import java.util.Objects;

public class ContieneID implements Serializable {
    private long pedidoID;
    private long productoID;
    public ContieneID()
    {

    }
    public ContieneID(long pedidoID, long productoID)
    {
        this.pedidoID = pedidoID;
        this.productoID = productoID;
    }

    public long getPedidoID() {
        return pedidoID;
    }

    public void setPedidoID(int pedidoID) {
        this.pedidoID = pedidoID;
    }

    public long getProductoID() {
        return productoID;
    }

    public void setProductoID(int productoID) {
        this.productoID = productoID;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContieneID that = (ContieneID) o;
        return pedidoID == that.pedidoID && productoID == that.productoID;
    }
    @Override
    public int hashCode() {
        return Objects.hash(pedidoID, productoID);
    }
}
