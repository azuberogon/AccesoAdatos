package es.marianasanz.acda.repaso.ut4.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Entity
@Table(name="Personas")
public class Persona {

    @Id
    @Column(name = "NIF", length = 9)
//    @GeneratedValue(strategy = GenerationType.AUTO) es porque el id lo pone la persona
    private String nif;

    @Column(name = "NSS", nullable = false, unique = true, length = 9)
    private String nss;

    @Column(name = "NOMBRE", length = 25)
    private String nombre;

    @Column(name = "APELLIDO", length = 50)
    private String apellido;

    @Column(name = "AÑO_NACIMIENTO")
    private Integer anoNacimiento;

    @Column(name = "SALARIO")
    private Integer salario;

    @Column(name = "PUESTO", columnDefinition = "ENUM('JEFE','OPERARIO','JUBILADO', 'LIMPIEZA')")
    private String puesto;

    @Column(name = "FECHA_ALTA")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaAlta;

    @Column(name = "FECHA_BAJA")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaBaja = null;

    @Column(name = "SEDE_TRABAJA", length = 2)
    private String sedeTrabaja;

    //------------- Constructor ---------------//
    public Persona(){}

    public Persona(String nif, String nss, String nombre, String apellido,
                   Integer anoNacimiento, Integer salario, String puesto,
                   LocalDate fechaAlta, LocalDate fechaBaja, String sedeTrabaja){
        this.nif = nif;
        this.nss = nss;
        this.nombre = nombre;
        this.apellido = apellido;
        this.anoNacimiento = anoNacimiento;
        this.salario = salario;
        this.puesto = puesto;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.sedeTrabaja = sedeTrabaja;
    }

    //---------- Setters y Getters ------------//

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

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }
    public void setAnoNacimiento(Integer anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
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

    public String getSedeTrabaja() {
        return sedeTrabaja;
    }
    public void setSedeTrabaja(String sedeTrabaja) {
        this.sedeTrabaja = sedeTrabaja;
    }
}
