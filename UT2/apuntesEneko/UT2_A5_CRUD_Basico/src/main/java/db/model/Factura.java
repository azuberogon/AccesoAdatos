package db.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Factura {
    private int codigo;
    private String destinatario;
    private int cuenta;
    private double importe;
    private LocalDateTime fechaHora;

    public Factura (int codigo, String destinatario, int cuenta, double importe, LocalDateTime fechaHora) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.cuenta = cuenta;
        this.importe = importe;
        this.fechaHora = fechaHora;
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

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "Factura nº " + codigo + "\n" +
                "\t- Destinatario: " + destinatario + "\n" +
                "\t- Nº cuenta: " + cuenta + "\n" +
                "\t- Importe: " + importe + " €\n" +
                "\t- Fecha de emisión: " + fechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }
}
