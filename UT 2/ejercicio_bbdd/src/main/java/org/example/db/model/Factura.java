package org.example.db.model;

import java.time.LocalDateTime;

public class Factura {




    private Integer codigo;
    private String destinatario;
    private Integer cuenta;
    private Double importe ;
    private LocalDateTime fecha_hora;


    public Factura(Integer codigo, String destinatario, Integer cuenta, Double importe, LocalDateTime fecha_hora) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.cuenta = cuenta;
        this.importe = importe;
        this.fecha_hora = fecha_hora;
    }
    public Factura(Integer codigo, String destinatario, Integer cuenta, Double importe ) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.cuenta = cuenta;
        this.importe = importe;

    }
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
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
