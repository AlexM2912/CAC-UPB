package InterfacesGraficas;

import Clases.Cita;
import Clases.Estudiante;
import Modulos.CentroDeAtencion;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.SpinnerNumberModel;

public class GUICrearCita {

    private JFrame frame;
    private JTextField idField;
    private JTextField estudianteField;
    private JDateChooser fechaChooser;
    private JSpinner horaSpinner;
    private Estudiante estudiante;
    private CentroDeAtencion centroDeAtencion;

    public GUICrearCita(Estudiante estudiante) {
        initialize();
        centroDeAtencion = new CentroDeAtencion();
        this.estudiante = estudiante;
        idField.setText(estudiante.getId().toString());
        idField.setEditable(false);
        estudianteField.setText(estudiante.getNombre() + " " + estudiante.getApellido());
        estudianteField.setEditable(false);
    }

    private void initialize() {
        frame = new JFrame("Crear Cita");
        frame.setBounds(100, 100, 400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblId = new JLabel("ID:");
        idField = new JTextField();
        JLabel lblEstudiante = new JLabel("Nombre del Estudiante:");
        estudianteField = new JTextField();
        JLabel lblFecha = new JLabel("Fecha:");
        fechaChooser = new JDateChooser();
        JLabel lblHora = new JLabel("Hora:");
        horaSpinner = new JSpinner(new SpinnerNumberModel(6, 6, 19, 1));

        panel.add(lblId);
        panel.add(idField);
        panel.add(lblEstudiante);
        panel.add(estudianteField);
        panel.add(lblFecha);
        panel.add(fechaChooser);
        panel.add(lblHora);
        panel.add(horaSpinner);

        JButton crearCitaButton = new JButton("Crear Cita");
        crearCitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCita();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(crearCitaButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void crearCita() {
        try {
            Date fechaCita = fechaChooser.getDate();
            int horaCita = (int) horaSpinner.getValue();
            Date fechaActual = new Date();

            if (fechaCita.after(fechaActual)) {
                Cita cita = new Cita(fechaCita, horaCita, estudiante);
                cita.setId(Long.parseLong(idField.getText()));
                centroDeAtencion.addCita(cita);

                JOptionPane.showMessageDialog(frame, "Cita creada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else if (fechaCita.equals(fechaActual)) {
                throw new IllegalArgumentException("La fecha de la cita no puede ser la misma que la fecha actual.");
            } else {
                throw new IllegalArgumentException("La fecha debe ser posterior a la fecha actual.");
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error al crear la cita: Formato de fecha u hora incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUICrearCita(new Estudiante());
            }
        });
    }
}
