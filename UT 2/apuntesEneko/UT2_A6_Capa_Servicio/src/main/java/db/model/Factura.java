package db.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Factura {
    private Integer codigo;
    private String destinatario;
    private Integer cuenta;
    private Double importe;
    private LocalDateTime fechaHora;

    public Factura (int codigo, String destinatario, int cuenta, double importe, LocalDateTime fechaHora) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.cuenta = cuenta;
        this.importe = importe;
        this.fechaHora = fechaHora;
    }

    public Integer getCodigo() {
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

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public Double getImporte() {
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
                "\t- Fecha de emisión: " + fechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
