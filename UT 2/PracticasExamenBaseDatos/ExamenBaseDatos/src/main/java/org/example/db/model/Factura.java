package org.example.db.model;

import java.time.LocalDateTime;

public class Factura {
    private Integer codigo;
    private String destinatario;
    private Integer cuenta;
    private Double importe;
    private LocalDateTime fechaHora;

    public Factura(Integer codigo, String destinatario, Integer cuenta, Double importe, LocalDateTime fechaHora) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.cuenta = cuenta;
        this.importe = importe;
        this.fechaHora = fechaHora;
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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    @Override
    public String toString() {
        return "Factura{" +
                "codigo=" + codigo +
                ", destinatario='" + destinatario + '\'' +
                ", cuenta=" + cuenta +
                ", importe=" + importe +
                ", fechaHora=" + fechaHora +
                '}';
    }

}
