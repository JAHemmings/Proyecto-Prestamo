package interfaz;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import control.Control;

public class VentanaNuevoPrestamo extends JFrame {

    private JPanel contentPane;
    private JTextField txtIdUsuario;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaNuevoPrestamo frame = new VentanaNuevoPrestamo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VentanaNuevoPrestamo() {

        setTitle("Nuevo Préstamo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 350, 220);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsuario = new JLabel("ID Usuario:");
        lblUsuario.setBounds(30,40,100,25);
        contentPane.add(lblUsuario);

        txtIdUsuario = new JTextField();
        txtIdUsuario.setBounds(130,40,150,25);
        contentPane.add(txtIdUsuario);

        JButton btnCrear = new JButton("Crear");
        btnCrear.setBounds(40,110,100,30);
        contentPane.add(btnCrear);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(170,110,100,30);
        contentPane.add(btnCerrar);

        btnCrear.addActionListener(e -> {

            try {

                int idUsuario = Integer.parseInt(txtIdUsuario.getText());
                Control.getInstance().crearPrestamo(idUsuario);
                JOptionPane.showMessageDialog(this,"Préstamo creado correctamente.");
                txtIdUsuario.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"Error al crear préstamo.");

            }

        });

        btnCerrar.addActionListener(e -> dispose());
    }
}