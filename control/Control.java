package control;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import logica.*;

public class Control {

    private static Control instance;

    private Map<Integer, Usuario> usuarios;
    private Map<Integer, Item> items;
    private Map<Integer, Prestamo> prestamos;

    private LinkedList<TipoItem> tipos;
    private LinkedList<Categoria> categorias;
    private LinkedList<Alerta> alertas;

    private int consecutivoPrestamo = 1;
    private int consecutivoAlerta = 1;

    private Control() {

        usuarios = new HashMap<>();
        items = new HashMap<>();
        tipos = new LinkedList<>();
        categorias = new LinkedList<>();
    }

    public static Control getInstance() {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getIdUsuario(), usuario);
    }

    public Usuario buscarUsuario(int idUsuario) {
        return usuarios.get(idUsuario);
    }

    public Usuario obtenerUsuario(int id) {
        return usuarios.get(id);
    }

    public Map<Integer, Usuario> obtenerUsuarios() {
        return usuarios;
    }


    public void registrarItem(Item item) {
        items.put(item.getCodigo(), item);
    }

    public Item buscarItem(int codigo) {
        return items.get(codigo);
    }

    public Item obtenerItem(int codigo) {
        return items.get(codigo);
    }

    public Map<Integer, Item> obtenerItems() {
        return items;
    }


    public void registrarTipo(TipoItem tipo) {
        tipos.add(tipo);
    }

    public TipoItem obtenerTipo(int id) {
        for (TipoItem t : tipos) {
            if (t.getIdTipo() == id) {
                return t;
            }
        }
        return null;
    }

    public LinkedList<TipoItem> obtenerTipos() {
        return tipos;
    }

    public void registrarCategoria(Categoria categoria) {
        categorias.add(categoria);
    }

    public Categoria obtenerCategoria(int id) {
        for (Categoria c : categorias) {
            if (c.getIdCategoria() == id) {
                return c;
            }
        }
        return null;
    }

    public LinkedList<Categoria> obtenerCategorias() {
        return categorias;
    }

    public LinkedList<Item> listarItemsDisponibles() {
        LinkedList<Item> lista = new LinkedList<>();
        for (Item item : items.values()) {
            if (item.isDisponible()) {
                lista.add(item);
            }
        }

        return lista;
    }
    
    public Prestamo crearPrestamo(Usuario usuario) {

        Prestamo prestamo = new Prestamo();

        prestamo.setIdPrestamo(consecutivoPrestamo++);
        prestamo.setUsuario(usuario);
        prestamo.setFechaPrestamo(new Date());
        prestamo.setEstado("ACTIVO");

        prestamos.put(prestamo.getIdPrestamo(), prestamo);

        usuario.agregarPrestamo(prestamo);

        return prestamo;
    }

    public void agregarItemPrestamo(Prestamo prestamo, Item item) {

        if (item.isDisponible()) {
            prestamo.agregarItem(item);
            item.marcarPrestado();
        }
    }

    public void finalizarPrestamo(Prestamo prestamo) {

        prestamo.finalizarPrestamo();
        prestamo.setEstado("FINALIZADO");
    }

    public Prestamo obtenerPrestamo(int id) {
        return prestamos.get(id);
    }

    public Map<Integer, Prestamo> obtenerPrestamos() {
        return prestamos;
    }

    public void generarAlerta(Prestamo prestamo, String mensaje, String tipo) {

        Alerta alerta = new Alerta();
        alerta.setIdAlerta(consecutivoAlerta++);
        alerta.setMensaje(mensaje);
        alerta.setTipo(tipo);
        alerta.setFechaAlerta(new Date());

        prestamo.setAlerta(alerta);

        alertas.add(alerta);
    }

    public LinkedList<Alerta> obtenerAlertas() {
        return alertas;
    }

    public LinkedList<Item> listarItemsPrestados() {
        LinkedList<Item> lista = new LinkedList<>();
        for (Item item : items.values()) {
            if (!item.isDisponible()) {
                lista.add(item);
            }
        }

        return lista;
    }

    public LinkedList<Prestamo> listarPrestamosActivos() {
        LinkedList<Prestamo> lista = new LinkedList<>();
        for (Prestamo p : prestamos.values()) {
            if (!p.isRetornado()) {
                lista.add(p);
            }
        }
        return lista;
    }

    public LinkedList<Prestamo> listarPrestamosVencidos() {
        LinkedList<Prestamo> lista = new LinkedList<>();
        Date hoy = new Date();
        for (Prestamo p : prestamos.values()) {
            if (!p.isRetornado() && p.getFechaDevolucion() != null && p.getFechaDevolucion().before(hoy)) {
                lista.add(p);
            }
        }
        return lista;
    }

    public LinkedList<Alerta> listarAlertasPendientes() {
        return alertas;
    }


    public Usuario reporteUsuario(int id) {
        return usuarios.get(id);
    }

    public Item reporteItem(int codigo) {
        return items.get(codigo);
    }

    public Categoria reporteCategoria(int id) {
        return obtenerCategoria(id);
    }

    public TipoItem reporteTipo(int id) {
        return obtenerTipo(id);
    }

}
