package interfaz;

import control.Control;
import logica.Categoria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaCategorias extends JFrame {

    private JTextField txtId;
    private JTextField txtNombre;

    private JTextArea txtDescripcion;

    private JTable tabla;
    private DefaultTableModel modelo;

    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;

    private Control control;

    public VentanaCategorias() {
        control = Control.getInstance();
        setTitle("Gestión de Categorías");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        inicializarComponentes();
        cargarTabla();
        setVisible(true);
    }

    private void inicializarComponentes() {

        setLayout(new BorderLayout());

        JPanel panelDatos = new JPanel(new GridLayout(3, 2));

        panelDatos.add(new JLabel("ID"));

        txtId = new JTextField();
        panelDatos.add(txtId);

        panelDatos.add(new JLabel("Nombre"));

        txtNombre = new JTextField();
        panelDatos.add(txtNombre);

        panelDatos.add(new JLabel("Descripción"));

        txtDescripcion = new JTextArea(3, 20);

        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);

        panelDatos.add(scrollDescripcion);

        add(panelDatos, BorderLayout.NORTH);

        modelo = new DefaultTableModel();

        tabla = new JTable(modelo);

        JScrollPane scrollTabla =
                new JScrollPane(tabla);

        add(scrollTabla, BorderLayout.CENTER);

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

        btnAgregar.addActionListener(e -> agregarCategoria());

        btnModificar.addActionListener(e -> modificarCategoria());

        btnEliminar.addActionListener(e -> eliminarCategoria());

        btnBuscar.addActionListener(e -> buscarCategoria());

        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void agregarCategoria() {
        try {
            int id = Integer.parseInt(txtId.getText());
            control.registrarCategoria(id, txtNombre.getText(), txtDescripcion.getText());
            cargarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }

    private void modificarCategoria() {
        try {
            int id = Integer.parseInt(txtId.getText());
            boolean exito = control.modificarCategoria(id, txtNombre.getText(), txtDescripcion.getText());
            if (exito) {
                cargarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Categoría no encontrada");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }

    private void eliminarCategoria() {
        try {
            int id = Integer.parseInt(txtId.getText());
            boolean exito = control.eliminarCategoria(id);
            if (exito) {
                cargarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Categoría no encontrada");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }

    private void buscarCategoria() {

        try {
            int id = Integer.parseInt(txtId.getText());
            Categoria categoria =control.obtenerCategoria(id);
            if (categoria != null) {
                txtNombre.setText(categoria.getNombre());
                txtDescripcion.setText(categoria.getDescripcion());
            } else {
                JOptionPane.showMessageDialog(this, "Categoría no encontrada");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"ID inválido");
        }
    }

    private void cargarTabla() {
        modelo.setDataVector(control.datosCategoriasTabla(), new String[]{"ID", "Nombre", "Descripción"});
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtId.requestFocus();
    }
}