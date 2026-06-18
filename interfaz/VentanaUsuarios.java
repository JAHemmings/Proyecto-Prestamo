package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class VentanaUsuarios extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtTelefono;

    public VentanaUsuarios() {
        setTitle("Gestión de Usuarios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 500);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 20, 80, 25);
        contentPane.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(100, 20, 150, 25);
        contentPane.add(txtCodigo);
        txtCodigo.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 80, 25);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 60, 200, 25);
        contentPane.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(20, 100, 80, 25);
        contentPane.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(100, 100, 250, 25);
        contentPane.add(txtCorreo);
        txtCorreo.setColumns(10);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 140, 80, 25);
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(100, 140, 150, 25);
        contentPane.add(txtTelefono);
        txtTelefono.setColumns(10);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(20, 190, 100, 30);
        contentPane.add(btnAgregar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(130, 190, 100, 30);
        contentPane.add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(240, 190, 100, 30);
        contentPane.add(btnEliminar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(350, 190, 100, 30);
        contentPane.add(btnBuscar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(460, 190, 100, 30);
        contentPane.add(btnLimpiar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 240, 690, 200);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Código", "Nombre", "Correo", "Teléfono"}));

        scrollPane.setViewportView(table);
    }
}
