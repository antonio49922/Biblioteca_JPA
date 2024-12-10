package libreria;

import java.util.ArrayList;
import java.util.List;

public class MemoriaEjemp {
    private List<Ejemplar> memoria;
    private DAO dao;
    private Validaciones validaciones;

    public void Inicializar() {
        this.memoria = new ArrayList();
        this.dao = new DAO();
        this.validaciones = new Validaciones();
    }

    public int agregarEjemplar(Ejemplar ejemplar) {
            if(!memoria.contains(ejemplar) && validaciones.validarEjemplar(ejemplar)) {
            memoria.add(ejemplar);
            dao.insert(ejemplar);
            return 1;
        }
        return -1;
    }

    public Ejemplar getEjemplar() {
        for(Ejemplar ejemplar : memoria) {
            return ejemplar;
        }
        return null;
    }

    public void eliminarEjemplar(Ejemplar ejemplar) {
        if (memoria.contains(ejemplar)) {
            memoria.remove(ejemplar);
            dao.delete(ejemplar);
        };
    }

    public Ejemplar actualizarEjemplar(Ejemplar ejemplar) {
        if (memoria.contains(ejemplar)) {
            int indice = memoria.indexOf(ejemplar);
            if (indice != -1) {
                memoria.set(indice, ejemplar);
                dao.update(ejemplar);
                return ejemplar;
            }
        }
        return null;
    }
    public List<Ejemplar> getMemoria() {
        return memoria;
    }

}
