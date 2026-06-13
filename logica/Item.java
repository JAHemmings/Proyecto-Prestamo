package logica;

import java.util.LinkedList;

public class Item {

    private int codigo;
    private String nombre;
    private String descripcion;
    private boolean disponible;
    private Prestamo prestamoActual;
    private TipoItem tipo;
    private LinkedList<Categoria> categorias;

    public Item() {
        categorias = new LinkedList<>();
        disponible = true;
    }

    public Item(int codigo, String nombre, String descripcion) {
        this();
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public TipoItem getTipo() {
        return tipo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public Prestamo getPrestamoActual() {
        return prestamoActual;
    }

    public void setPrestamoActual(Prestamo prestamoActual) {
        this.prestamoActual = prestamoActual;
    }

    public void eliminarPrestamoActual() {
        prestamoActual = null;
    }

    public LinkedList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(
            LinkedList<Categoria> categorias) {

        this.categorias = categorias;
    }

    public void agregarCategoria(Categoria categoria) {
        if (!categorias.contains(categoria)) {
            categorias.add(categoria);
        }
    }

    public void eliminarCategoria(Categoria categoria) {
        categorias.remove(categoria);
    }

    public void marcarPrestado() {
        disponible = false;
    }

    public void marcarDisponible() {
        disponible = true;
    }
}
