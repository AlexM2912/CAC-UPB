package Modulos;

import Clases.Cita;
import Clases.Estudiante;
import Clases.Ticket;
import DataStructures.PriorityQueue;
import DataStructures.Queue;
import DataStructures.SinglyLinkedList;
import DataStructures.SinglyLinkedNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.rmi.RemoteException;
import java.util.Iterator;

public class CentroDeAtencion implements Serializable {

    private static final String filePathEstudiantes = "C:/Users/JUAN DAVID FUENTES/Desktop/CAC-UPB/src/main/java/BaseDeDatos/estudiantes.json";

    private SinglyLinkedList<Cita> citas = new SinglyLinkedList<>();
    private SinglyLinkedList<Estudiante> estudiantes = new SinglyLinkedList<>();
    private PriorityQueue<Ticket> mentorias;
    private PriorityQueue<Ticket> asesorias;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public CentroDeAtencion(){
        try {
            this.estudiantes.add(cargarEstudiantes());
            this.mentorias = new PriorityQueue<>(2);
            this.asesorias = new PriorityQueue<>(2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Estudiante buscarEstudiantePorId(int id) throws RemoteException {
        SinglyLinkedList<Estudiante> cloneList = estudiantes.cloneList();
        if (!cloneList.isEmpty()) {
            while (!cloneList.isEmpty()) {
                if (cloneList.get().getId() == id) {
                    return cloneList.get();
                } else {
                    cloneList.pop();
                }
            }
        }
        return null;
    }

    public Estudiante[] cargarEstudiantes() throws RemoteException {
        try (Reader reader = new FileReader(filePathEstudiantes)) {
            Estudiante[] UsuarioArr = gson.fromJson(reader, Estudiante[].class);
            if (UsuarioArr == null) {
                UsuarioArr = new Estudiante[0];
            }
            return UsuarioArr;
        } catch (IOException e) {
            e.printStackTrace();
            return new Estudiante[0];
        }
    }

    public boolean crearEstudiante(Long id, String password, String nombre, String apellido, boolean discapacidad, boolean primerSemestre) throws RemoteException {

        if(buscarEstudiantePorId(Math.toIntExact(id)) == null) {
            Estudiante newEstudiantes = new Estudiante(id, password, nombre, apellido, discapacidad, primerSemestre);
            estudiantes.add(newEstudiantes);
            guardarEstudiantesEnJson();
            return true;
        }
        return false;
    }

    public void addCita(Cita cita) {
        SinglyLinkedList<Cita> cloneList = citas.cloneList();
        if (!cloneList.isEmpty()) {
            while (!cloneList.isEmpty()) {
                if (cloneList.get().getFecha().equals(cita.getFecha()) && cloneList.get().getHora() == cita.getHora()) {
                    throw new IllegalArgumentException("Ya existe una cita con la misma fecha y hora.");
                } else {
                    cloneList.pop();
                }
            }
        }
        citas.add(cita);
    }

    public void cancelarCita(int id) throws RemoteException {
        Cita citaToRemove = buscarCitaPorId(id);
        if (citaToRemove != null) {
            System.out.println(citas.remove(citaToRemove) + "cita removida");;
        }
    }

    public Cita buscarCitaPorId(int cita) {
        SinglyLinkedList<Cita> cloneList = citas.cloneList();
        if (!cloneList.isEmpty()) {
            while (!cloneList.isEmpty()) {
                if (cloneList.get().getId() == cita) {
                    return cloneList.get();
                } else {
                    cloneList.pop();
                }
            }
        }
        return null;
    }


    public void encolarMentorias(Ticket ticket) throws RemoteException {
        int prioridad = 0;
        if (ticket.getEstudiante().isDiscapacidad()) {
            prioridad = 0;
        } else if (ticket.getEstudiante().isPrimerSemestre()) {
            prioridad = 1;
        } else {
            prioridad = 2;
        }
        mentorias.insert(ticket, prioridad);
        System.out.println(mentorias.peek().getEstudiante().getNombre());
    }

    public void encolarAsesorias(Ticket ticket) throws RemoteException {
        int prioridad = 0;
        if (ticket.getEstudiante().isDiscapacidad()) {
            prioridad = 0;
        } else if (ticket.getEstudiante().isPrimerSemestre()) {
            prioridad = 1;
        } else {
            prioridad = 2;
        }
        asesorias.insert(ticket, prioridad);
        System.out.println(asesorias.peek().getEstudiante().getNombre());
    }

    public Ticket deseconlarMentoria() throws RemoteException {
        return mentorias.pop();
    }

    public Ticket deseconlarTicket() throws RemoteException {
        return asesorias.pop();
    }

    public Estudiante getEstudianteCredenciales(int id, String password) throws RemoteException {
        SinglyLinkedList<Estudiante> cloneList = estudiantes.cloneList();
        while (!cloneList.isEmpty()) {
            if (cloneList.get().getId() == id && cloneList.get().getContrasena().equals(password)) {
                return cloneList.get();
            }else {
                cloneList.pop();
            }
        }
        return null;
    }

    public boolean validarCredenciales(int id, String password) throws RemoteException {
        SinglyLinkedList<Estudiante> cloneList = estudiantes.cloneList();
        while (!cloneList.isEmpty()) {
            if (cloneList.get().getId() == id && cloneList.get().getContrasena().equals(password)) {
                return true;
            }else {
                cloneList.pop();
            }
        }
        return false;
    }

    public void guardarEstudiantesEnJson() throws RemoteException {
        try (Writer writer = new FileWriter(filePathEstudiantes)) {
            gson.toJson(estudiantes.toArray(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
