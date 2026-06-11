package logica;

import java.util.Date;
import java.util.LinkedList;

public class Prestamo {

    private int idPrestamo;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String estado;
    private boolean retornado;
    private Date fechaRetorno;

    private Usuario usuario;
    private LinkedList<Item> items;

    private Alerta alerta;

    public Prestamo() {
        items = new LinkedList<>();
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

    public boolean isRetornado() {
        return retornado;
    }

    public void setRetornado(boolean retornado) {
        this.retornado = retornado;
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

    public void agregarItem(Item item) {
        items.add(item);
        item.marcarPrestado();
    }

    public void removerItem(Item item) {
        items.remove(item);
        item.marcarDisponible();
    }

    public void finalizarPrestamo() {
        retornado = true;
        fechaRetorno = new Date();

        for (Item item : items) {
            item.marcarDisponible();
        }
    }

    public Alerta getAlerta() {
        return alerta;
    }

    public void setAlerta(Alerta alerta) {
        this.alerta = alerta;
    }
}
