package interfaz;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import control.Control;

public class VentanaFinalizarPrestamo extends JFrame {

    private JPanel contentPane;
    private JTextField txtIdPrestamo;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaFinalizarPrestamo frame = new VentanaFinalizarPrestamo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VentanaFinalizarPrestamo() {

        setTitle("Finalizar Préstamo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100,100,340,220);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblPrestamo = new JLabel("ID Préstamo:");
        lblPrestamo.setBounds(30,50,100,25);
        contentPane.add(lblPrestamo);

        txtIdPrestamo = new JTextField();
        txtIdPrestamo.setBounds(140,50,130,25);
        contentPane.add(txtIdPrestamo);

        JButton btnFinalizar = new JButton("Finalizar");
        btnFinalizar.setBounds(40,120,100,30);
        contentPane.add(btnFinalizar);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(170,120,100,30);
        contentPane.add(btnCerrar);

        btnFinalizar.addActionListener(e -> {

            try {

                Control.getInstance().finalizarPrestamo(Integer.parseInt(txtIdPrestamo.getText()));
                JOptionPane.showMessageDialog(this,"Préstamo finalizado.");
                txtIdPrestamo.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"No fue posible finalizar el préstamo.");
            }

        });

        btnCerrar.addActionListener(e -> dispose());

    }
}