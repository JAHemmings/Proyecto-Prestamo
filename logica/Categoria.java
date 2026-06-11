package logica;

import java.util.LinkedList;

public class Categoria {

    private int idCategoria;
    private String nombre;
    private String descripcion;

    private LinkedList<Item> items;

    public Categoria() {
        items = new LinkedList<>();
    }

    public Categoria(int idCategoria, String nombre, String descripcion) {
        this();
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public void setItems(LinkedList<Item> items) {
        this.items = items;
    }

    public void agregarItem(Item item) {
        items.add(item);
    }

    public void removerItem(Item item) {
        items.remove(item);
    }
}
