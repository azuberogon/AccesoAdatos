package org.example.model;

import java.time.LocalDateTime;

public class Factura {



    private int codigo;
    private int cuenta;
    private String destinatario;
    private LocalDateTime fecha_hora;
    private double precio ;



    public Factura(int codigo, String destinatario, int cuenta, double importe, LocalDateTime localDateTime) {
        this.codigo = codigo;
        this.cuenta = cuenta;
        this.destinatario = this.destinatario;
        this.fecha_hora = fecha_hora;
        this.precio = precio;
    }

    public Factura() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "codigo=" + codigo +
                ", cuenta=" + cuenta +
                ", desstinatario='" + destinatario + '\'' +
                ", fecha_hora=" + fecha_hora +
                ", precio=" + precio +
                '}';
    }
}
