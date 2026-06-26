package control;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import logica.*;

public class Control implements Serializable{

    private static final long serialVersionUID = 1L;
    private static transient Control instance;
    private static final String ARCHIVO = "prestamos.dat";

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
            cargarDatos();
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

    public String[] datosUsuario(int id) {
        Usuario usuario = usuarios.get(id);
        if (usuario == null) {
            return null;
        }
        return new String[] {
                String.valueOf(usuario.getIdUsuario()),
                usuario.getNombre(),
                usuario.getTelefono(),
                usuario.getCorreo()
        };
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
    
    public String[][] datosCategoriasTabla() {
        String[][] datos = new String[categorias.size()][3];
        int fila = 0;
        for (Categoria categoria : categorias) {
            datos[fila][0] = String.valueOf(categoria.getIdCategoria());
            datos[fila][1] = categoria.getNombre();
            datos[fila][2] = categoria.getDescripcion();
            fila++;
        }

        return datos;
    }
    
    public String[][] datosTiposTabla() {
        String[][] datos = new String[tipos.size()][2];
        int fila = 0;
        for (TipoItem tipo : tipos) {
            datos[fila][0] = String.valueOf(tipo.getIdTipo());
            datos[fila][1] = tipo.getNombre();
            fila++;
        }
        return datos;
    }
    
    public String[][] datosAlertasTabla() {
        String[][] datos = new String[alertas.size()][4];
        int fila = 0;
        for (Alerta alerta : alertas) {
            datos[fila][0] = String.valueOf(alerta.getIdAlerta());
            datos[fila][1] = alerta.getMensaje();
            datos[fila][2] = alerta.getTipo();
            datos[fila][3] = alerta.getFechaAlerta().toString();
            fila++;
        }
        return datos;
    }

    public String[][] datosPrestamosTabla() {
        String[][] datos = new String[prestamos.size()][5];
        int fila = 0;
        for (Prestamo p : prestamos.values()) {
            datos[fila][0] = String.valueOf(p.getIdPrestamo());
            if (p.getUsuario() != null) {
                datos[fila][1] = p.getUsuario().getNombre();
            } else {
                datos[fila][1] = "";
            }
            datos[fila][2] = p.getEstado();
            datos[fila][3] = String.valueOf(p.getFechaPrestamo());
            datos[fila][4] = String.valueOf(p.getFechaRetorno());
            fila++;
        }
        
        return datos;
    }
    
    public String[][] datosUsuariosTabla() {
        String[][] datos = new String[usuarios.size()][4];
        int fila = 0;
        for (Usuario usuario : usuarios.values()) {
            datos[fila][0] = String.valueOf(usuario.getIdUsuario());
            datos[fila][1] = usuario.getNombre();
            datos[fila][2] = usuario.getTelefono();
            datos[fila][3] = usuario.getCorreo();
            fila++;
        }

        return datos;
    }
    
    public int cantidadUsuarios() {
        return usuarios.size();
    }

    public int cantidadItems() {
        return items.size();
    }

    public int cantidadPrestamos() {
        return prestamos.size();
    }

    public int cantidadAlertas() {
        return alertas.size();
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
    public void guardarDatos() {

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            salida.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean modificarUsuario(int id, String nombre, String telefono, String correo) {
        Usuario usuario = usuarios.get(id);
        if (usuario == null)
            return false;
        usuario.setNombre(nombre);
        usuario.setTelefono(telefono);
        usuario.setCorreo(correo);
        guardarDatos();
        return true;
    }
    
    public boolean eliminarUsuario(int id) {
        Usuario usuario = usuarios.get(id);
        if (usuario == null)
            return false;
        if (!usuario.getPrestamos().isEmpty())
            return false;
        usuarios.remove(id);
        guardarDatos();
        return true;
    }
    
    public boolean modificarItem(int codigo, String nombre, String descripcion) {
        Item item = items.get(codigo);
        if (item == null)
            return false;
        item.setNombre(nombre);
        item.setDescripcion(descripcion);
        guardarDatos();
        return true;
    }
    
    public boolean eliminarItem(int codigo) {
        Item item = items.get(codigo);
        if (item == null)
            return false;
        if (!item.isDisponible())
            return false;
        items.remove(codigo);
        guardarDatos();
        return true;
    }
    
    public boolean modificarCategoria(int id, String nombre, String descripcion) {
        Categoria categoria = obtenerCategoria(id);
        if (categoria == null)
            return false;
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        guardarDatos();
        return true;
    }
    
    public boolean eliminarCategoria(int id) {
        Categoria categoria = obtenerCategoria(id);
        if (categoria == null)
            return false;
        categorias.remove(categoria);
        guardarDatos();
        return true;
    }
    
    public boolean modificarTipo(int id, String nombre) {
        TipoItem tipo = obtenerTipo(id);
        if (tipo == null)
            return false;
        tipo.setNombre(nombre);
        guardarDatos();
        return true;
    }
    
    public boolean eliminarTipo(int id) {
        TipoItem tipo = obtenerTipo(id);
        if (tipo == null)
            return false;
        tipos.remove(tipo);
        guardarDatos();
        return true;
    }
    
    public boolean eliminarPrestamo(int idPrestamo) {
        Prestamo prestamo = prestamos.get(idPrestamo);
        if (prestamo == null)
            return false;
        if (prestamo.estaActivo())
            return false;
        prestamos.remove(idPrestamo);
        guardarDatos();
        return true;
    }
    
    public void verificarPrestamosVencidos() {
        for (Prestamo p : prestamos.values()) {
            if (p.estaVencido()) {
                if (p.getAlerta() == null) {
                    generarAlerta(p.getIdPrestamo(), "Préstamo vencido", "VENCIMIENTO");
                }
            }
        }
    }
    
    public static void cargarDatos() {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            instance = (Control) entrada.readObject();
        } catch (Exception e) {
            instance = new Control();

        }
    }
    
    public int prestamosActivos() {
        int contador = 0;
        for (Prestamo p : prestamos.values()) {
            if (p.estaActivo()) {
                contador++;
            }
        }
        return contador;
    }
    
    public int prestamosVencidos() {
        int contador = 0;
        for (Prestamo p : prestamos.values()) {
            if (p.estaVencido()) {
                contador++;
            }
        }
        return contador;
    }
    
    public int itemsDisponibles() {
        int contador = 0;
        for (Item item : items.values()) {
            if (item.isDisponible()) {
                contador++;
            }
        }
        return contador;
    }
    
    public String[][] datosReporteGeneral() {
        String[][] datos = new String[5][2];

        datos[0][0] = "Usuarios";
        datos[0][1] = String.valueOf(cantidadUsuarios());

        datos[1][0] = "Items";
        datos[1][1] = String.valueOf(cantidadItems());

        datos[2][0] = "Prestamos Activos";
        datos[2][1] = String.valueOf(prestamosActivos());

        datos[3][0] = "Prestamos Vencidos";
        datos[3][1] = String.valueOf(prestamosVencidos());

        datos[4][0] = "Alertas";
        datos[4][1] = String.valueOf(cantidadAlertas());
        return datos;
    }
    
    public String generarReporteTexto() {
        String reporte = "";
        reporte += "USUARIOS: " + cantidadUsuarios() + "\n";
        reporte += "ITEMS: " + cantidadItems() + "\n";
        reporte += "PRESTAMOS ACTIVOS: " + prestamosActivos() + "\n";
        reporte += "PRESTAMOS VENCIDOS: " + prestamosVencidos() + "\n";
        reporte += "ALERTAS: " + cantidadAlertas() + "\n";
        return reporte;
    }
    
    public LinkedList<Categoria> getCategorias() {
        return categorias;
    }

    public LinkedList<TipoItem> getTipos() {
        return tipos;
    }
    
}

