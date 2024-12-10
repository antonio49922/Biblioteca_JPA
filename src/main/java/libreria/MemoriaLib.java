package libreria;

import java.util.ArrayList;
import java.util.List;

public class MemoriaLib {
    private List<Libro> memoriaLibros;
    private DAO daoLibro;
    private Validaciones validaciones;

    public void Inicializar() {
        this.memoriaLibros = new ArrayList();
        this.daoLibro = new DAO();
        this.validaciones = new Validaciones();
    }

    public int agregarLibro(Libro libro) {
        if(!memoriaLibros.contains(libro) && validaciones.validarLibro(libro)) {
                memoriaLibros.add(libro);
                daoLibro.insert(libro);
                return 1;
            }
        return -1;
    }

    public Libro getLibro() {
        for(Libro libro : memoriaLibros) {
            return libro;
        }
        return null;
    }

    public void eliminarLibro(Libro libro) {
        if (memoriaLibros.contains(libro)) {
            memoriaLibros.remove(libro);
            daoLibro.delete(libro);
        };
    }

    public Libro actualizarLibro(Libro libro) {
        if (memoriaLibros.contains(libro)) {
            int indice = memoriaLibros.indexOf(libro);
            if (indice != -1) {
                memoriaLibros.set(indice, libro);
                daoLibro.update(libro);
                return libro;
            }
        }
        return null;
    }

    public List<Libro> getMemoriaLibros() {
        return memoriaLibros;
    }



}
