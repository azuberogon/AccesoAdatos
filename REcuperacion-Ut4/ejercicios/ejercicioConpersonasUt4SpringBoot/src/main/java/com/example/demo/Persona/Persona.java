package com.example.demo.Persona;


import jakarta.persistence.*;

import org.springframework.lang.NonNull;

import java.time.LocalDate;
@Entity
@Table(name = "Personas") // de aqui se puede hacer un chekeo de la tabala con los datos DNI no entra en esto
public class Persona {
    @Id
    //se puede quitar el nombre si coincide el nombre del atributo con el de la tabla en el (colum name=  nif)
    @Column(name="nif", length = 9) // hay que referenciarlo con la tabla que es
    private String nif;
    //habria que validar los datos antes pero no hace falta para
    @Column(name="nss", length = 9,unique = true,nullable = false)// notnull: nulable se hable
    private String nss ;
    @Column(name ="nombre" ,length = 25)
    private String nombre;
    @Column(name ="apellido", length = 50)
    private String apellido;
    @Column(name= "año_Nacimiento")
    private Integer anyoNacimiento;
    @Temporal(TemporalType.DATE)
    @Column(name ="Fecha_Alta")
    private LocalDate fechaAlta;
    @Column(name ="Salario")
    private Integer salario;
    //@Enum mirar como se hace lo de las tablas pero no va a caer en el cxamen
    @Column(name ="Puesto", columnDefinition = "'JEFE','EMPLEADO','LIMPIEZA','JUIBILADO'")
    private String puesto;
    @Temporal(TemporalType.DATE)
    @Column(name ="Fecha_Baja")
    private LocalDate fecha_baja;
    @Column(name ="Sede_Trabaja")
    private String sede_trabaja;


    public String getNif() {

        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    @NonNull
    public String getNss() {
        return nss;
    }

    public void setNss(@NonNull String nss) {
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

    public Integer getAnyoNacimiento() {
        return anyoNacimiento;
    }

    public void setAnyoNacimiento(Integer anyoNacimiento) {
        this.anyoNacimiento = anyoNacimiento;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
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

    public LocalDate getFecha_baja() {
        return fecha_baja;
    }

    public void setFecha_baja(LocalDate fecha_baja) {
        this.fecha_baja = fecha_baja;
    }

    public String getSede_trabaja() {
        return sede_trabaja;
    }

    public void setSede_trabaja(String sede_trabaja) {
        this.sede_trabaja = sede_trabaja;
    }
}
