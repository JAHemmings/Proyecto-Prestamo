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

    private LinkedList<TipoItem> tipos;
    private LinkedList<Categoria> categorias;

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
