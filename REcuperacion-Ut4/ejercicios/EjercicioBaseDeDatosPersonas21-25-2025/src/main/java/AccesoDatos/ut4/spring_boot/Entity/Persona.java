package AccesoDatos.ut4.spring_boot.Entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String nif;
    @Column(name="nss", nullable = false,  length = 12)
    private String nss;
    @Column(name="nombre",   length = 50)
    private String nombre;
    @Column(name="apellido",   length = 50)
    private String apellido;
    @Column(name="AñO_NACIMIENTO",   length = 12)
    private String anoNaz;

    private Integer salario;
    //private Puesto puesto

    private String puesto;
    @Column(name="FECHA_ALTA",   length = 12)
    private Date fechaAlata;
    @Column(name="FECHA_BAJA", length = )
    private Date fechaBaja;

    private String sedeTrabaja;

    public Persona() {
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAnoNaz() {
        return anoNaz;
    }

    public void setAnoNaz(String anoNaz) {
        this.anoNaz = anoNaz;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Date getFechaAlata() {
        return fechaAlata;
    }

    public void setFechaAlata(Date fechaAlata) {
        this.fechaAlata = fechaAlata;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getSedeTrabaja() {
        return sedeTrabaja;
    }

    public void setSedeTrabaja(String sedeTrabaja) {
        this.sedeTrabaja = sedeTrabaja;
    }
}
