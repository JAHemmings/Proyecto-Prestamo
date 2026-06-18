package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaPrincipal frame = new VentanaPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VentanaPrincipal() {
        setTitle("Sistema de Control de Préstamos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
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

        JButton btnUsuarios = new JButton("Usuarios");
        btnUsuarios.setBounds(145, 80, 180, 35);
        contentPane.add(btnUsuarios);

        JButton btnItems = new JButton("Items");
        btnItems.setBounds(145, 130, 180, 35);
        contentPane.add(btnItems);

        JButton btnPrestamos = new JButton("Préstamos");
        btnPrestamos.setBounds(145, 180, 180, 35);
        contentPane.add(btnPrestamos);

        JButton btnTipos = new JButton("Tipos");
        btnTipos.setBounds(145, 230, 180, 35);
        contentPane.add(btnTipos);

        JButton btnCategorias = new JButton("Categorías");
        btnCategorias.setBounds(145, 280, 180, 35);
        contentPane.add(btnCategorias);

        JButton btnAlertas = new JButton("Alertas");
        btnAlertas.setBounds(145, 330, 180, 35);
        contentPane.add(btnAlertas);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(145, 390, 180, 35);
        contentPane.add(btnSalir);

        btnUsuarios.addActionListener(e -> {
            new VentanaUsuarios().setVisible(true);
        });

        btnItems.addActionListener(e -> {
            new VentanaItems().setVisible(true);
        });

        btnPrestamos.addActionListener(e -> {
            new VentanaPrestamos().setVisible(true);
        });

        btnTipos.addActionListener(e -> {
            new VentanaTipos().setVisible(true);
        });

        btnCategorias.addActionListener(e -> {
            new VentanaCategorias().setVisible(true);
        });

        btnAlertas.addActionListener(e -> {
            new VentanaAlertas().setVisible(true);
        });

        btnSalir.addActionListener(e -> {
            dispose();
        });
    }
}