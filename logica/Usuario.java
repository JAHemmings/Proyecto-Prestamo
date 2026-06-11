package logica;

import java.util.LinkedList;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String telefono;
    private String correo;

    private LinkedList<Prestamo> prestamos;

    public Usuario() {
        prestamos = new LinkedList<>();
    }

    public Usuario(int idUsuario, String nombre, String telefono, String correo) {
        this();
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void agregarPrestamo(Prestamo p) {
        prestamos.add(p);
    }

    public void eliminarPrestamo(Prestamo p) {
        prestamos.remove(p);
    }
}