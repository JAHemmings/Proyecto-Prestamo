package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class VentanaAdministracion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				VentanaAdministracion frame = new VentanaAdministracion();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public VentanaAdministracion() {

		setTitle("Administración");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 400);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("ADMINISTRACIÓN");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(50, 20, 300, 30);
		contentPane.add(lblTitulo);

		JButton btnUsuarios = new JButton("Personas");
		btnUsuarios.setBounds(110, 80, 180, 30);
		contentPane.add(btnUsuarios);

		JButton btnItems = new JButton("Ítems");
		btnItems.setBounds(110, 130, 180, 30);
		contentPane.add(btnItems);

		JButton btnCategorias = new JButton("Categorías");
		btnCategorias.setBounds(110, 180, 180, 30);
		contentPane.add(btnCategorias);

		JButton btnTipos = new JButton("Tipos");
		btnTipos.setBounds(110, 230, 180, 30);
		contentPane.add(btnTipos);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(110, 290, 180, 30);
		contentPane.add(btnCerrar);

		btnUsuarios.addActionListener(e -> new VentanaUsuarios().setVisible(true));
		btnItems.addActionListener(e -> new VentanaItems().setVisible(true));
		btnCategorias.addActionListener(e -> new VentanaCategorias().setVisible(true));
		btnTipos.addActionListener(e -> new VentanaTipos().setVisible(true));

		btnCerrar.addActionListener(e -> dispose());
	}
}