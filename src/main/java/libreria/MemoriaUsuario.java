package libreria;

import java.util.ArrayList;
import java.util.List;

public class MemoriaUsuario {
    private List<Usuario> memoria;
    private DAO dao;
    private Validaciones validaciones;

    public void Inicializar() {
        this.memoria = new ArrayList();
        this.dao = new DAO();
        this.validaciones = new Validaciones();
    }

    public int agregarUsuario(Usuario usuario) {
        int existente = buscarUsuario(usuario.getDni());

        if(!memoria.contains(usuario) && validaciones.validarUsuario(usuario) && existente == -1) {
            memoria.add(usuario);
            dao.insert(usuario);
            return 1;
        }
        return -1;
    }

    public int buscarUsuario(String dni) {
        for (Usuario usuario : memoria) {
            if (usuario.getDni().equals(dni)) {
                return 1;
            }
        }
        return -1;
    }

    public Usuario getUsuario() {
        for(Usuario usuario : memoria) {
            return usuario;
        }
        return null;
    }

    public void eliminarUsuario(Usuario usuario) {
        if (memoria.contains(usuario)) {
            memoria.remove(usuario);
            dao.delete(usuario);
        };
    }

    public Usuario actualizarUsuario(Usuario usuario) {
        if (memoria.contains(usuario)) {
            int indice = memoria.indexOf(usuario);
            if (indice != -1) {
                memoria.set(indice, usuario);
                dao.update(usuario);
                return usuario;
            }
        }
        return null;
    }
    public List<Usuario> getMemoria() {
        return memoria;
    }
}
