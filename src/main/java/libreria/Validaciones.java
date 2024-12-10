package libreria;

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


}
