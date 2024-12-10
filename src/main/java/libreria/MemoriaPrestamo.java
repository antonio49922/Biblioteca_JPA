package libreria;

import java.util.ArrayList;
import java.util.List;

public class MemoriaPrestamo {
    private List<Prestamo> memoria;
    private DAO dao;
    private Validaciones validaciones;

    public void Inicializar() {
        this.memoria = new ArrayList();
        this.dao = new DAO();
        this.validaciones = new Validaciones();
    }

    public int agregarPrestamo(Prestamo prestamo) {
        if(!memoria.contains(prestamo) && validaciones.validarUsuario(usuario)) {
            memoria.add(prestamo);
            dao.insert(prestamo);
            return 1;
        }
        return -1;
    }

    public Prestamo getPrestamo() {
        for(Prestamo prestamo : memoria) {
            return prestamo;
        }
        return null;
    }

    public void eliminarPrestamo(Prestamo prestamo) {
        if (memoria.contains(prestamo)) {
            memoria.remove(prestamo);
            dao.delete(prestamo);
        };
    }

    public Prestamo actualizarPrestamo(Prestamo prestamo) {
        if (memoria.contains(prestamo)) {
            int indice = memoria.indexOf(prestamo);
            if (indice != -1) {
                memoria.set(indice, prestamo);
                dao.update(prestamo);
                return prestamo;
            }
        }
        return null;
    }
    public List<Prestamo> getMemoria() {
        return memoria;
    }
}
