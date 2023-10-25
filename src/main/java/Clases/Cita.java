package Clases;

import java.io.Serializable;
import java.security.PrivateKey;
import java.util.Date;

public class Cita implements Serializable {

    private Long id;
    private Estudiante estudiante;
    private int hora;
    private Date fecha;

    public Cita(Date fecha, int hora, Estudiante estudiante) {
        Date fechaActual = new Date();
        if (fecha.after(fechaActual)) {
            this.fecha = fecha;
            this.hora = hora;
            this.estudiante = estudiante;
        } else {
            System.out.println("La fecha debe ser posterior a la fecha actual.");
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String toString(){
        return "id: " + id + " Estudiante: " + estudiante.getNombre() +" " + estudiante.getId() + " Fecha: " + fecha + " Hora: " + hora;
    }
}
