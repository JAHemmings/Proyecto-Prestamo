package interfaz;

import control.Control;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaAlertas extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JScrollPane scroll;
    private JButton btnActualizar;
    private JButton btnCerrar;
    private Control control;

    public VentanaAlertas() {
        control = Control.getInstance();
        setTitle("Alertas");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        inicializarComponentes();
        cargarTabla();
        setVisible(true);
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());
        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
        scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);
        JPanel panelBotones = new JPanel();
        btnActualizar = new JButton("Actualizar");
        btnCerrar = new JButton("Cerrar");
        panelBotones.add(btnActualizar);
        panelBotones.add(btnCerrar);
        add(panelBotones, BorderLayout.SOUTH);
        btnActualizar.addActionListener(e -> actualizarTabla());
        btnCerrar.addActionListener(e -> dispose());
    }

    private void cargarTabla() {
        modelo.setDataVector(control.datosAlertasTabla(),
                new String[]{"ID", "Mensaje", "Tipo", "Fecha"});
    }

    private void actualizarTabla() {
        control.verificarPrestamosVencidos();
        cargarTabla();
    }
}