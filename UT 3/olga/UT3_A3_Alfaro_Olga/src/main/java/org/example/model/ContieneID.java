package org.example.model;

import jakarta.persistence.Embeddable;

import java.util.Objects;

import java.io.Serializable;

@Embeddable
public class ContieneID implements Serializable {
    private int pedidoID;
    private int productoID;

    public ContieneID(){}

    public ContieneID(int pedidoID, int productoID){
        this.pedidoID = pedidoID;
        this.productoID = productoID;
    }

    public int getPedidoID() {
        return pedidoID;
    }

    public void setPedidoID(int pedidoID) {
        this.pedidoID = pedidoID;
    }

    public int getProductoID() {
        return productoID;
    }

    public void setProductoID(int productoID) {
        this.productoID = productoID;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContieneID that = (ContieneID) o;
        return pedidoID == that.pedidoID && productoID == that.productoID;
    }

    @Override
    public int hashCode(){
        return Objects.hash(pedidoID, productoID);
    }

}

