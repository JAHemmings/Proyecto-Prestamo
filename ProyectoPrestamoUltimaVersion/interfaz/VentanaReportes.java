package interfaz;

import control.Control;


import javax.swing.*;
import java.awt.*;

public class VentanaReportes extends JFrame {

    private JTextArea txtReporte;

    private JButton btnGenerar;
    private JButton btnCerrar;

    private Control control;

    public VentanaReportes() {

        control = Control.getInstance();

        setTitle("Reportes");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        inicializarComponentes();

        setVisible(true);
    }

    private void inicializarComponentes() {

        setLayout(new BorderLayout());

        txtReporte = new JTextArea();
        txtReporte.setEditable(false);

        JScrollPane scroll =
                new JScrollPane(txtReporte);

        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        btnGenerar = new JButton("Generar Reporte");
        btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnGenerar);
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        btnGenerar.addActionListener(
                e -> generarReporte());

        btnCerrar.addActionListener(
                e -> dispose());
    }

    private void generarReporte() {

        String reporte = "";
        reporte += "Usuarios registrados: " + control.cantidadUsuarios() + "\n";
        reporte += "Items registrados: " + control.cantidadItems() + "\n";
        reporte += "Prestamos activos: " + control.prestamosActivos() + "\n";
        reporte += "Prestamos vencidos: " + control.prestamosVencidos() + "\n";
        reporte += "Alertas generadas: " + control.cantidadAlertas() + "\n\n";
        txtReporte.setText(reporte);
    }
}