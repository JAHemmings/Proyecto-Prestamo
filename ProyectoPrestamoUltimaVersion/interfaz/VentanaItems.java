package interfaz;


import control.Control;
import logica.Categoria;
import logica.TipoItem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;

public class VentanaItems extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JComboBox<Categoria> cbCategoria;
    private JComboBox<TipoItem> cbTipo;

    private void cargarCombostacobell() {
        Control control = Control.getInstance();
        cbCategoria.removeAllItems();
        for (Categoria categoria : control.getCategorias()) {
            cbCategoria.addItem(categoria);
        }
        cbTipo.removeAllItems();
        for (TipoItem tipo : control.getTipos()) {
            cbTipo.addItem(tipo);
        }
    }
    
    public VentanaItems() {
        setTitle("Gestión de Items");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 550);

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

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(20, 100, 80, 25);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(100, 100, 300, 25);
        contentPane.add(txtDescripcion);
        txtDescripcion.setColumns(10);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(20, 140, 80, 25);
        contentPane.add(lblCategoria);


        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(320, 140, 50, 25);
        contentPane.add(lblTipo);

        cbCategoria = new JComboBox<>();
        cbCategoria.setBounds(100, 140, 180, 25);
        contentPane.add(cbCategoria);

        cbTipo = new JComboBox<>();
        cbTipo.setBounds(360, 140, 180, 25);
        contentPane.add(cbTipo);

        cargarCombostacobell();

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
        scrollPane.setBounds(20, 250, 740, 230);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Código", "Nombre", "Descripción", "Categoría", "Tipo"}));

        scrollPane.setViewportView(table);
    }
}