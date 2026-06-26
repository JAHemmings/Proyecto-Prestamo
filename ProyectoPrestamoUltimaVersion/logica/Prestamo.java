package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class Prestamo implements Serializable{

    private int idPrestamo;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String estado;
    private Date fechaRetorno;

    private Usuario usuario;
    private LinkedList<Item> items;

    private Alerta alerta;

    public Prestamo() {

        items = new LinkedList<>();
        estado = "ACTIVO";
    }

    public Prestamo(int idPrestamo, Date fechaPrestamo, Date fechaDevolucion) {
        this();
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaRetorno() {
        return fechaRetorno;
    }

    public void setFechaRetorno(Date fechaRetorno) {
        this.fechaRetorno = fechaRetorno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public void setItems(LinkedList<Item> items) {
        this.items = items;
    }

    public Alerta getAlerta() {
        return alerta;
    }

    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }

    public void agregarItem(Item item) {
        if (item != null && !items.contains(item)) {
            items.add(item);
            item.setPrestamoActual(this);
            item.marcarPrestado();
        }
    }

    public void removerItem(Item item) {
        if (item != null) {
            items.remove(item);
            item.eliminarPrestamoActual();
            item.marcarDisponible();
        }
    }

    public void finalizarPrestamo() {
        estado = "FINALIZADO";
        fechaRetorno = new Date();
        for (Item item : items) {
            item.eliminarPrestamoActual();
            item.marcarDisponible();
        }
    }

    public boolean estaActivo() {
        return estado.equalsIgnoreCase("ACTIVO");
    }

    public boolean estaFinalizado() {
        return estado.equalsIgnoreCase("FINALIZADO");
    }

    public boolean estaVencido() {
        if (fechaDevolucion == null) {
            return false;
        }
        return new Date().after(fechaDevolucion) && estado.equalsIgnoreCase("ACTIVO");
    }
}