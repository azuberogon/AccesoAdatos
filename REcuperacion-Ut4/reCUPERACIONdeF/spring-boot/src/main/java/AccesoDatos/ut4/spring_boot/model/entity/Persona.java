package AccesoDatos.ut4.spring_boot.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

public class Persona {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="nif", length = 9)
    private String nif;
    @Column(name = "nss", length = 9 )
    private String nss;
    @Column(name ="nombre", length = 25 )
    private String nombre;
    @Column(name ="apellidos", length = 25 )
    private String apellidos;
    @Column(name ="anoNacimiento" )
    private Integer anoNacimiento;
    @Column(name="fecha_alta")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaAlta;
    @Column(name="fecha_baja")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaBaja = null;
    @Column(name="puesto", columnDefinition = "ENUM('JEFE','OPERARIO','JUBILADO','LIMPIEZA')")
    private String puesto;
    @Column(name="sedeTrabaja", length = 25)
    private String sedeTrabaja;
    @Column(name="salario")
    private Integer salario;

    public Persona() {
    }

    public Persona(String nif, String nss, String nombre, String apellidos, Integer anoNacimiento, LocalDate fechaAlta, LocalDate fechaBaja, String puesto, String sedeTrabaja, Integer salario) {
        this.nif = nif;
        this.nss = nss;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.anoNacimiento = anoNacimiento;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.puesto = puesto;
        this.sedeTrabaja = sedeTrabaja;
        this.salario = salario;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getSedeTrabaja() {
        return sedeTrabaja;
    }

    public void setSedeTrabaja(String sedeTrabaja) {
        this.sedeTrabaja = sedeTrabaja;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }
}
