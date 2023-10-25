package InterfacesGraficas;

import Clases.Estudiante;
import Modulos.CentroDeAtencion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GUIInicioSesionRegistro {

    private JFrame frame;
    private JTextField idField;
    private JTextField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private CentroDeAtencion centroDeAtencion;
    private ImageIcon icon;

    public GUIInicioSesionRegistro() {
        this.centroDeAtencion = new CentroDeAtencion();
        initialize();
    }

    private void initialize() {

        String imagePath = "C:/Users/JUAN DAVID FUENTES/Desktop/CAC-UPB/src/main/java/InterfacesGraficas/descarga.png";
        ImageIcon icon = new ImageIcon(imagePath);

        frame = new JFrame("Inicio de Sesión");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setIconImage(icon.getImage());
        frame.setLocationRelativeTo(null);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(50, 30, 50, 25);
        frame.getContentPane().add(lblId);

        idField = new JTextField();
        idField.setBounds(150, 30, 150, 25);
        frame.getContentPane().add(idField);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 65, 100, 25);
        frame.getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 65, 150, 25);
        frame.getContentPane().add(passwordField);

        loginButton = new JButton("Iniciar Sesión");
        loginButton.setBounds(100, 100, 120, 30);
        frame.getContentPane().add(loginButton);

        registerButton = new JButton("Registrarse");
        registerButton.setBounds(230, 100, 120, 30);
        frame.getContentPane().add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    iniciarSesion();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mostrarFormularioRegistro();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void iniciarSesion() throws RemoteException {

        int id = Integer.parseInt(idField.getText());
        String password = passwordField.getText();

        if (centroDeAtencion.validarCredenciales(id, password)){
            new GUICrearCita(centroDeAtencion.getEstudianteCredenciales(id,password));
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }

    }

    private void mostrarFormularioRegistro() throws RemoteException {
        JPanel panel = new JPanel(new GridLayout(6, 2));

        JTextField idField = new JTextField();
        JTextField nombreField = new JTextField();
        JPasswordField contrasenaField = new JPasswordField();
        JTextField apellidoField = new JTextField();
        JCheckBox discapacidadCheckBox = new JCheckBox("Tiene discapacidad");
        JCheckBox primerSemestreCheckBox = new JCheckBox("Primer semestre");

        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Contraseña:"));
        panel.add(contrasenaField);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreField);
        panel.add(new JLabel("Apellido:"));
        panel.add(apellidoField);
        panel.add(new JLabel("Discapacidad:"));
        panel.add(discapacidadCheckBox);
        panel.add(new JLabel("Primer Semestre:"));
        panel.add(primerSemestreCheckBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Agregar Nuevo Estudiante",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION && idField.getText().matches("\\d{6}")) {
            Long id = Long.parseLong(idField.getText());
            String contrasena = contrasenaField.getText();
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            boolean discapacidad = discapacidadCheckBox.isSelected();
            boolean primerSemestre = primerSemestreCheckBox.isSelected();

            if (centroDeAtencion.crearEstudiante(id, contrasena, nombre, apellido, discapacidad, primerSemestre)) {
                String mensaje = "Registro correcto\n\n" +
                        "ID: " + id + "\n" +
                        "Nombre: " + nombre + "\n" +
                        "Apellido: " + apellido + "\n" +
                        "Discapacidad: " + (discapacidad ? "Sí" : "No") + "\n" +
                        "Primer Semestre: " + (primerSemestre ? "Sí" : "No");

                JOptionPane.showMessageDialog(null, mensaje, "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "El estudiante ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El id debe ser de 6 digitos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CentroDeAtencion centroDeAtencion = new CentroDeAtencion();
                    GUIInicioSesionRegistro window = new GUIInicioSesionRegistro();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
