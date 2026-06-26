package interfaz;

import control.Control;
import logica.TipoItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaTipos extends JFrame {

    private JTextField txtId;
    private JTextField txtNombre;

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;

    private Control control;

    public VentanaTipos() {

        control = Control.getInstance();

        setTitle("Gestión de Tipos");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        inicializarComponentes();

        cargarTabla();

        setVisible(true);
    }

    private void inicializarComponentes() {

        setLayout(new BorderLayout());

        JPanel panelDatos = new JPanel(new GridLayout(2, 2));

        panelDatos.add(new JLabel("ID"));

        txtId = new JTextField();
        panelDatos.add(txtId);

        panelDatos.add(new JLabel("Nombre"));

        txtNombre = new JTextField();
        panelDatos.add(txtNombre);

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

        btnAgregar.addActionListener(e -> agregarTipo());
        btnModificar.addActionListener(e -> modificarTipo());
        btnEliminar.addActionListener(e -> eliminarTipo());
        btnBuscar.addActionListener(e -> buscarTipo());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void agregarTipo() {
        try {
            int id = Integer.parseInt(txtId.getText());
            control.registrarTipo(id, txtNombre.getText());
            cargarTabla();
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }

    private void modificarTipo() {
        try {
            int id = Integer.parseInt(txtId.getText());
            boolean exito = control.modificarTipo(id, txtNombre.getText());
            if (exito) {
                cargarTabla();
                limpiarCampos();
            } 
            else {
                JOptionPane.showMessageDialog(this, "Tipo no encontrado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Datos inválidos");
        }
    }

    private void eliminarTipo() {

        try {
            int id = Integer.parseInt(txtId.getText());
            boolean exito = control.eliminarTipo(id);
            if (exito) {
                cargarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this,"Tipo no encontrado");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }

    private void buscarTipo() {

        try {
            int id = Integer.parseInt(txtId.getText());
            TipoItem tipo = control.obtenerTipo(id);
            if (tipo != null) {
                txtNombre.setText(tipo.getNombre());
            } 
            else {
                JOptionPane.showMessageDialog(this,"Tipo no encontrado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ID inválido");
        }
    }

    private void cargarTabla() {

        modelo.setDataVector(control.datosTiposTabla(), new String[]{"ID", "Nombre"});
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtId.requestFocus();
    }
}