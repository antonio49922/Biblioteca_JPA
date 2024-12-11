package libreria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Validaciones {

    public boolean validarLibro(Libro libro) {
        String ISBN = libro.getIsbn();
        String subISBN = ISBN.substring(0,3);
        int isbn = Integer.parseInt(subISBN);
        String titulo = libro.getTitulo();
        String autor = libro.getAutor();
        if (isbn == 979 && ISBN.length() == 13 && titulo != null && titulo.length() > 0 && autor != null && autor.length() > 0) {
            return true;
        }
        return false;
    }

    public boolean validarEjemplar(Ejemplar ejemplar) {
        MemoriaLib lib = new MemoriaLib();
        lib.Inicializar();
        String ISBN2 = String.valueOf(ejemplar.getIsbn());
        String estado= ejemplar.getEstado();
        if (ISBN2.length() == 13 && (estado.equals("Disponible") || estado.equals("Prestado") || estado.equals("Dañado"))) {
            for (Libro libro: lib.getMemoriaLibros()){
                String ISBN = String.valueOf(libro.getIsbn());
                if(ISBN.equals(ISBN2)){
                    if(contarEjemplares(ejemplar) != 0){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int contarEjemplares(Ejemplar ejemplar) {
        MemoriaEjemp em = new MemoriaEjemp();
        em.Inicializar();
        int contador = 0;
        for (Ejemplar ej: em.getMemoria()) {
            if(ej.getIsbn() == ejemplar.getIsbn() && ej.getEstado().equals("Disponible")){
                contador++;
            }
        }
        return contador;
    }

    public boolean validarUsuario(Usuario usuario) {
        String nombre = usuario.getNombre();
        String dni = usuario.getDni();
        if (nombre != null && nombre.length() > 0 && dni != null && dni.length() > 0) {
            return true;
        }
        return false;
    }

    public boolean validarPrestamo(Prestamo prestamo) {
        LocalDate inicio = prestamo.getFechaInicio();
        LocalDate fin = prestamo.getFechaDevolucion();
        if (!inicio.isAfter(LocalDate.now()) && !fin.isBefore(LocalDate.now())) {
            return true;
        }
        return false;
    }


    public List<Prestamo> validarTipoUser(Usuario usuario) {
        String tipo = usuario.getTipo();
        List<Prestamo> prestamos = new ArrayList<>();
        if (tipo.equals("normal")){
            for (Prestamo pres: usuario.getPrestamos()){
                if(pres.getUsuario().getId().equals(usuario.getId())){
                    prestamos.add(pres);
                }
            }
        }else if(tipo.equals("administrador")){
            for (Prestamo pres: usuario.getPrestamos()){
                prestamos.add(pres);
            }
        }
        return prestamos;
    }

    public void hacerPrestamo(Usuario usuario, Ejemplar ejemplar) throws Exception {
        prestamosUsuario(usuario);
        disponibilidadPrestamo(ejemplar);
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setEjemplar(ejemplar);
        prestamo.setFechaInicio(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusDays(15));
        ejemplar.setEstado("Prestado");
    }

    public void prestamosUsuario(Usuario usuario) throws Exception {
        if(usuario.getPenalizacionHasta() != null && usuario.getPenalizacionHasta().isAfter(LocalDate.now())){
            throw new Exception("El usuario tiene una penalización activa.");
        }
        int prestamosActuales =0;
        for (Prestamo prestamo: usuario.getPrestamos()){
            prestamosActuales++;
        }
        if(prestamosActuales >= 3){
            throw new Exception("Limite de prestamos alcanzado.");
        }
    }


    public void disponibilidadPrestamo(Ejemplar ejemplar) throws Exception {
        if(!ejemplar.getEstado().equals("Disponible")){
            throw new Exception("El ejemplar no está disponible para préstamo.");
        }
    }

    public void hacerDevolucion(Prestamo prestamo) throws Exception {
        prestamo.setFechaDevolucion(LocalDate.now());
        Ejemplar ejemplar = prestamo.getEjemplar();
        ejemplar.setEstado("Disponible");
    }


}


//ahora quiero que hagas una clase main con la que llamar a los menus que has creado, que aparezca un menu en el que elejiremos que menu queremos ver (el de libro, usuario, prestamo y el de ejemplar)