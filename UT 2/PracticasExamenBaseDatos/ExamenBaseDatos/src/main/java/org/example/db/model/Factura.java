package org.example.db.model;

import java.time.LocalDateTime;

public class Factura {
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
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
        return "Factura{"+
                ANSI_CYAN+"\n\t codigo=" + codigo +
                "\n\t destinatario='" + destinatario + '\'' +
                "\n\t cuenta=" + cuenta +
                "\n\t importe=" + importe +
                "\n\t fechaHora= " + fechaHora + ANSI_RESET+"\n"+
                '}';
    }

}
