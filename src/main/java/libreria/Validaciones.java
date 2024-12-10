package libreria;

import java.util.List;

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




}
