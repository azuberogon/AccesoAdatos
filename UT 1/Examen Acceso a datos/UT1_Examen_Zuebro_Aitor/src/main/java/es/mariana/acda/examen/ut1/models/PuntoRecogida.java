package es.mariana.acda.examen.ut1.models;

import java.io.Serializable;

public class PuntoRecogida implements Serializable{
    private int id;
    private int idTipo;
    private int idEquipamiento;
    private String tipoEquipamiento;
    private Coordenadas coordenadas;
    private String mancomunidad;
    private String localidad;
    private String direccion;
    private String horario;

    // Constructor
    public PuntoRecogida(int id, int idTipo, int idEquipamiento, String tipoEquipamiento, Coordenadas coordenadas,
                         String mancomunidad, String localidad, String direccion, String horario) {
        this.id = id;
        this.idTipo = idTipo;
        this.idEquipamiento = idEquipamiento;
        this.tipoEquipamiento = tipoEquipamiento;
        this.coordenadas = coordenadas;
        this.mancomunidad = mancomunidad;
        this.localidad = localidad;
        this.direccion = direccion;
        this.horario = horario;

    }

    public int getId() {
        return id;
    }
    public int getIdTipo() { return idTipo; }
    public int getIdEquipamiento() { return idEquipamiento; }
    public String getTipoEquipamieto() {
        return tipoEquipamiento;
    }
    public Coordenadas getCoordenadas() { return coordenadas; }
    public String getMancomunidad() { return mancomunidad; }
    public String getLocalidad() { return localidad; }
    public String getDireccion() { return direccion; }
    public String getHorario() {
        return horario;
    }

}
