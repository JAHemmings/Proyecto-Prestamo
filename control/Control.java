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

    private int consecutivoPrestamo;
    private int consecutivoAlerta;

    private Control() {

        usuarios = new HashMap<>();
        items = new HashMap<>();
        prestamos = new HashMap<>();
        tipos = new LinkedList<>();
        categorias = new LinkedList<>();
        alertas = new LinkedList<>();

        consecutivoPrestamo = 1;
        consecutivoAlerta = 1;
    }

    public static Control getInstance() {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }

    public void registrarUsuario(int id, String nombre, String telefono, String correo) {
        try {

            if (!validarId(id))
                throw new Exception("Id inválido");
            if (usuarios.containsKey(id))
                throw new Exception("Usuario ya existe");
            if (!validarTexto(nombre))
                throw new Exception("Nombre inválido");
            if (!validarTelefono(telefono))
                throw new Exception("Teléfono inválido");
            if (!validarCorreo(correo))
                throw new Exception("Correo inválido");

            Usuario usuario = new Usuario(id, nombre, telefono, correo);
            usuarios.put(id, usuario);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void registrarItem(int codigo, String nombre, String descripcion) {
        try {

            if (!validarId(codigo))
                throw new Exception("Código inválido");
            if (items.containsKey(codigo))
                throw new Exception("Item ya existe");
            if (!validarTexto(nombre))
                throw new Exception("Nombre inválido");
            Item item = new Item(codigo, nombre, descripcion);
            items.put(codigo, item);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void registrarTipo(int id, String nombre) {
        try {

            if (!validarId(id))
                throw new Exception("Id inválido");
            if (!validarTexto(nombre))
                throw new Exception("Nombre inválido");
            TipoItem tipo = new TipoItem(id, nombre);
            tipos.add(tipo);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void registrarCategoria(int id, String nombre, String descripcion) {
        try {

            if (!validarId(id))
                throw new Exception("Id inválido");
            if (!validarTexto(nombre))
                throw new Exception("Nombre inválido");
            Categoria categoria =
                    new Categoria(id, nombre, descripcion);
            categorias.add(categoria);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Prestamo crearPrestamo(int idUsuario) {
        try {
        	
            Usuario usuario = buscarUsuario(idUsuario);
            if (usuario == null)
                throw new Exception("Usuario no existe");
            Prestamo prestamo = new Prestamo();
            prestamo.setIdPrestamo(consecutivoPrestamo++);
            prestamo.setFechaPrestamo(new Date());
            prestamo.setEstado("ACTIVO");
            prestamos.put( prestamo.getIdPrestamo(), prestamo);
            asignarPrestamoUsuario( prestamo.getIdPrestamo(), idUsuario);
            return prestamo;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void finalizarPrestamo(int idPrestamo) {
        try {
        	
            Prestamo prestamo = obtenerPrestamo(idPrestamo);
            if (prestamo == null)
                throw new Exception("Préstamo inexistente");
            prestamo.finalizarPrestamo();
            prestamo.setEstado("FINALIZADO");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void asignarPrestamoUsuario(int idPrestamo, int idUsuario) {
        try {

            Prestamo prestamo = obtenerPrestamo(idPrestamo);
            Usuario usuario = obtenerUsuario(idUsuario);
            if (prestamo == null || usuario == null)
                throw new Exception("Datos inválidos");
            prestamo.setUsuario(usuario);
            usuario.agregarPrestamo(prestamo);
       
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void asignarItemPrestamo(int idPrestamo, int codigoItem) {
        try {

            Prestamo prestamo = obtenerPrestamo(idPrestamo);
            Item item = obtenerItem(codigoItem);
            if (prestamo == null || item == null)
                throw new Exception("Datos inválidos");

            if (!item.isDisponible())
                throw new Exception("Item prestado");

            prestamo.agregarItem(item);
            item.setPrestamoActual(prestamo);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void quitarItemPrestamo(int idPrestamo, int codigoItem) {
        try {

            Prestamo prestamo = obtenerPrestamo(idPrestamo);
            Item item = obtenerItem(codigoItem);

            if (prestamo == null || item == null)
                throw new Exception("Datos inválidos");

            prestamo.removerItem(item);
            item.eliminarPrestamoActual();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void asignarTipoItem(int codigoItem, int idTipo) {
        try {

            Item item = obtenerItem(codigoItem);
            TipoItem tipo = obtenerTipo(idTipo);

            if (item == null || tipo == null)
                throw new Exception("Datos inválidos");

            item.setTipo(tipo);
            tipo.agregarItem(item);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void asignarCategoriaItem(int codigoItem, int idCategoria) {
        try {

            Item item = obtenerItem(codigoItem);
            Categoria categoria = obtenerCategoria(idCategoria);

            if (item == null || categoria == null)
                throw new Exception("Datos inválidos");

            item.agregarCategoria(categoria);
            categoria.agregarItem(item);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void quitarCategoriaItem(int codigoItem,int idCategoria) {
        try {

            Item item = obtenerItem(codigoItem);
            Categoria categoria = obtenerCategoria(idCategoria);
            if (item == null || categoria == null)
                throw new Exception("Datos inválidos");

            item.eliminarCategoria(categoria);
            categoria.removerItem(item);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void generarAlerta(int idPrestamo, String mensaje, String tipo) {
        try {

            Prestamo prestamo = obtenerPrestamo(idPrestamo);
            if (prestamo == null)
                throw new Exception("Préstamo inválido");

            Alerta alerta = new Alerta();
            alerta.setIdAlerta(consecutivoAlerta++);
            alerta.setMensaje(mensaje);
            alerta.setTipo(tipo);
            alerta.setFechaAlerta(new Date());
            prestamo.setAlerta(alerta);
            alertas.add(alerta);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Usuario obtenerUsuario(int id) {
        return usuarios.get(id);
    }

    public Usuario buscarUsuario(int id) {
        return usuarios.get(id);
    }

    public Item obtenerItem(int codigo) {
        return items.get(codigo);
    }

    public Prestamo obtenerPrestamo(int id) {
        return prestamos.get(id);
    }

    public TipoItem obtenerTipo(int id) {
        for (TipoItem tipo : tipos) {
            if (tipo.getIdTipo() == id) {
                return tipo;
            }
        }
        return null;
    }

    public Categoria obtenerCategoria(int id) {
        for (Categoria categoria : categorias) {
            if (categoria.getIdCategoria() == id) {
                return categoria;
            }
        }
        return null;
    }


    private boolean validarCorreo(String correo) {
        return correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    private boolean validarTelefono(String telefono) {
        return telefono.matches("\\d{8}");
    }

    private boolean validarTexto(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    private boolean validarId(int id) {
        return id > 0;
    }
}
