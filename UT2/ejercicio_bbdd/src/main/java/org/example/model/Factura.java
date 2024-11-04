package org.example.model;

import java.time.LocalDateTime;

public class Factura {



    private int codigo;
    private String destinatario;
    private int cuenta;
    private double importe ;
    private LocalDateTime fecha_hora;


    public Factura(int codigo, String destinatario, int cuenta, double importe, LocalDateTime fecha_hora) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.cuenta = cuenta;
        this.importe = importe;
        this.fecha_hora = fecha_hora;
    }
    public Factura(int codigo, String destinatario, int cuenta, double importe ) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.cuenta = cuenta;
        this.importe = importe;

    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }
    @Override
    public String toString() {
        return "\n================= Factura =================" +
                "\nCódigo:            " + codigo +
                "\nDestinatario:      " + destinatario +
                "\nCuenta:            " + cuenta +
                "\nImporte:           " + importe + " €" +
                "\nFecha y Hora:      " + fecha_hora +
                "\n===========================================\n";
    }




}
