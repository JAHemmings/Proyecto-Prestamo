package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class VentanaPrestamos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				VentanaPrestamos frame = new VentanaPrestamos();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public VentanaPrestamos() {

		setTitle("Préstamos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 350);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("PRÉSTAMOS");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(50, 20, 300, 30);
		contentPane.add(lblTitulo);

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(110, 80, 180, 30);
		contentPane.add(btnNuevo);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(110, 130, 180, 30);
		contentPane.add(btnModificar);

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(110, 180, 180, 30);
		contentPane.add(btnFinalizar);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(110, 240, 180, 30);
		contentPane.add(btnCerrar);

		btnNuevo.addActionListener(e -> new VentanaNuevoPrestamo().setVisible(true));
		btnModificar.addActionListener(e -> new VentanaModificarPrestamo().setVisible(true));
		btnFinalizar.addActionListener(e -> new VentanaFinalizarPrestamo().setVisible(true));
		btnCerrar.addActionListener(e -> dispose());
	}
}