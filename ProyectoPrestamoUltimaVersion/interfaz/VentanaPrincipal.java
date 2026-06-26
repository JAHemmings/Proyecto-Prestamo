package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import control.Control;
public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaPrincipal frame = new VentanaPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VentanaPrincipal() {

        setTitle("Sistema de Control de Préstamos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 380);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("SISTEMA DE CONTROL DE PRÉSTAMOS");
        lblTitulo.setForeground(new Color(0, 0, 128));
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(20, 20, 445, 35);
        contentPane.add(lblTitulo);

        JButton btnAdministracion = new JButton("Administración");
        btnAdministracion.setBounds(145, 90, 180, 35);
        contentPane.add(btnAdministracion);

        JButton btnPrestamos = new JButton("Préstamos");
        btnPrestamos.setBounds(145, 145, 180, 35);
        contentPane.add(btnPrestamos);

        JButton btnReportes = new JButton("Reportes");
        btnReportes.setBounds(145, 200, 180, 35);
        contentPane.add(btnReportes);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(145, 255, 180, 35);
        contentPane.add(btnSalir);
        btnSalir.addActionListener(e -> {
        	Control.getInstance().guardarDatos();
        	dispose();});;

        btnAdministracion.addActionListener(e -> {new VentanaAdministracion().setVisible(true);});

        btnPrestamos.addActionListener(e -> {new VentanaPrestamos().setVisible(true);});

        btnReportes.addActionListener(e -> {new VentanaReportes().setVisible(true);});

        btnSalir.addActionListener(e -> {dispose();});
       
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
    }
    
}