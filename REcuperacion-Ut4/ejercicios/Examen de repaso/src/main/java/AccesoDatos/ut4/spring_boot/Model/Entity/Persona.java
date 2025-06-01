package AccesoDatos.ut4.spring_boot.Model.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(schema = "Personas")
public class Persona {
    @Id
    @Column(name = "nif", length = 9)
    //@GeneratedValue(strategy = GenerationType.AUTO) es porque el id lo pone la persona
    private String nif ;

    @Column(name = "nss" ,length = 9 , nullable = false)

    private String nss ;
    @Column(name = "nombre", length=50)
    private String nombre ;
    @Column(name = "apellido" , length=50)
    private String apellido ;
    @Column(name="año_nacimiento" )
    private Integer anoNacimiento ;
    @Column(name = "salario")
    private Integer salario;
    @Column(name = "puesto", columnDefinition = "Enum('JEFE','OPERARIO','LIMPIEZA','JUBILADO')")
    private String puesto ;
    @Column(name = "fechaAlta")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaAlta ;
    @Column(name = "fechaBaja")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaBaja ;
    @Column(name = "sedeTrabaja")
    private String sedeTrabaja ;

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
