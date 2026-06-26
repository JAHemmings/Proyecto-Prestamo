package interfaz;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import control.Control;

public class VentanaModificarPrestamo extends JFrame {

    private JPanel contentPane;
    private JTextField txtIdPrestamo;
    private JTextField txtCodigoItem;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaModificarPrestamo frame = new VentanaModificarPrestamo();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VentanaModificarPrestamo() {

        setTitle("Modificar Préstamo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100,100,400,260);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblPrestamo = new JLabel("ID Préstamo:");
        lblPrestamo.setBounds(30,40,100,25);
        contentPane.add(lblPrestamo);

        txtIdPrestamo = new JTextField();
        txtIdPrestamo.setBounds(150,40,150,25);
        contentPane.add(txtIdPrestamo);

        JLabel lblItem = new JLabel("Código Item:");
        lblItem.setBounds(30,80,100,25);
        contentPane.add(lblItem);

        txtCodigoItem = new JTextField();
        txtCodigoItem.setBounds(150,80,150,25);
        contentPane.add(txtCodigoItem);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(20,150,100,30);
        contentPane.add(btnAgregar);

        JButton btnQuitar = new JButton("Quitar");
        btnQuitar.setBounds(140,150,100,30);
        contentPane.add(btnQuitar);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(260,150,100,30);
        contentPane.add(btnCerrar);

        btnAgregar.addActionListener(e -> {

            try {
                Control.getInstance().asignarItemPrestamo(Integer.parseInt(txtIdPrestamo.getText()),Integer.parseInt(txtCodigoItem.getText()));
                JOptionPane.showMessageDialog(this,"Item agregado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"No fue posible agregar el item.");
            }

        });

        btnQuitar.addActionListener(e -> {
            try {
                Control.getInstance().quitarItemPrestamo(Integer.parseInt(txtIdPrestamo.getText()),
                        Integer.parseInt(txtCodigoItem.getText()));
                JOptionPane.showMessageDialog(this, "Item eliminado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"No fue posible quitar el item.");
            }
        });

        btnCerrar.addActionListener(e -> dispose());

    }
}