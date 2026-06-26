package logica;

import java.io.Serializable;
import java.util.Date;

public class Alerta implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idAlerta;
    private String mensaje;
    private Date fechaAlerta;
    private String tipo;

    public Alerta() {
    }

    public Alerta(int idAlerta, String mensaje, Date fechaAlerta, String tipo) {
        this.idAlerta = idAlerta;
        this.mensaje = mensaje;
        this.fechaAlerta = fechaAlerta;
        this.tipo = tipo;
    }

    public int getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(int idAlerta) {
        this.idAlerta = idAlerta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaAlerta() {
        return fechaAlerta;
    }

    public void setFechaAlerta(Date fechaAlerta) {
        this.fechaAlerta = fechaAlerta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void mostrarAlerta() {
        System.out.println(mensaje);
    }
}