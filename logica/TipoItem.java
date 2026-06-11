package logica;

import java.util.LinkedList;

public class TipoItem {

    private int idTipo;
    private String nombre;

    private LinkedList<Item> items;

    public TipoItem() {
        items = new LinkedList<>();
    }

    public TipoItem(int idTipo, String nombre) {
        this();
        this.idTipo = idTipo;
        this.nombre = nombre;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
