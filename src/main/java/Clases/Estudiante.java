package Clases;

import java.io.Serializable;

public class Estudiante implements Serializable {

    private Long Id;
    private String contrasena;
    private String Nombre;
    private String Apellido;
    private boolean discapacidad;
    private boolean primerSemestre;

    public Estudiante(Long id, String contrasena, String nombre, String apellido, boolean discapacidad, boolean primerSemestre) {
        Id = id;
        this.contrasena = contrasena;
        Nombre = nombre;
        Apellido = apellido;
        this.discapacidad = discapacidad;
        this.primerSemestre = primerSemestre;
    }

    public Estudiante(){

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public boolean isDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(boolean discapacidad) {
        this.discapacidad = discapacidad;
    }

    public boolean isPrimerSemestre() {
        return primerSemestre;
    }

    public void setPrimerSemestre(boolean primerSemestre) {
        this.primerSemestre = primerSemestre;
    }
}
