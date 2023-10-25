package Clases;

import java.io.Serializable;

public class Ticket implements Serializable {

    private int id;
    private Estudiante estudiante;
    private Cita cita;

    public Ticket(Estudiante estudiante, Cita cita) {
        this.estudiante = estudiante;
        this.cita = cita;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiantes(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }
}
