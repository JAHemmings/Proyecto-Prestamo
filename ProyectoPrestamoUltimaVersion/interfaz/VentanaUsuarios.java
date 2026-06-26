package interfaz;

import control.Control;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaUsuarios extends JFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;

    private Control control;

    public VentanaUsuarios() {

        control = Control.getInstance();

        setTitle("Usuarios");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        inicializarComponentes();

        cargarTabla();

        setVisible(true);
    }

    private void inicializarComponentes() {

        setLayout(new BorderLayout());

        JPanel panelDatos = new JPanel(new GridLayout(4, 2));

        panelDatos.add(new JLabel("ID"));

        txtId = new JTextField();
        panelDatos.add(txtId);

        panelDatos.add(new JLabel("Nombre"));

        txtNombre = new JTextField();
        panelDatos.add(txtNombre);

        panelDatos.add(new JLabel("Telefono"));

        txtTelefono = new JTextField();
        panelDatos.add(txtTelefono);

        panelDatos.add(new JLabel("Correo"));

        txtCorreo = new JTextField();
        panelDatos.add(txtCorreo);

        add(panelDatos, BorderLayout.NORTH);

        modelo = new DefaultTableModel();

        tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnLimpiar);

        add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregarUsuario());

        btnModificar.addActionListener(e -> modificarUsuario());

        btnEliminar.addActionListener(e -> eliminarUsuario());

        btnBuscar.addActionListener(e -> buscarUsuario());

        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void agregarUsuario() {

        try {
            control.registrarUsuario(Integer.parseInt(txtId.getText()), txtNombre.getText(), txtTelefono.getText(), txtCorreo.getText());
            cargarTabla();
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos invalidos");
        }
    }

    private void modificarUsuario() {
        try {
            boolean siSeLogro = control.modificarUsuario(Integer.parseInt(txtId.getText()), txtNombre.getText(), txtTelefono.getText(), txtCorreo.getText());
            if (siSeLogro) {
                cargarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog( this, "Usuario no encontrado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos invalidos");
        }
    }

    private void eliminarUsuario() {
        try {
            boolean otraVezSeLogro = control.eliminarUsuario(Integer.parseInt(txtId.getText()));
            if (otraVezSeLogro) {
                cargarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this,"No se pudo eliminar");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ID invalido");
        }
    }

    private void buscarUsuario() {
        try {
            String[] datos = control.datosUsuario(Integer.parseInt(txtId.getText()));
            if (datos != null) {
                txtNombre.setText(datos[1]);
                txtTelefono.setText(datos[2]);
                txtCorreo.setText(datos[3]);
            } 
            else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ID inválido");
        }
    }
    
    private void cargarTabla() {
        modelo.setDataVector( control.datosUsuariosTabla(), new String[]{ "ID", "Nombre", "Telefono", "Correo"});
    }

    private void limpiarCampos() {

        txtId.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");

        txtId.requestFocus();
    }
}