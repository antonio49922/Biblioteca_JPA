package libreria;

    import java.util.List;
    import java.util.Scanner;

    public class MenuLibro {
        private MemoriaLib memoriaLib;
        private Scanner scanner;

        public MenuLibro() {
            memoriaLib = new MemoriaLib();
            memoriaLib.Inicializar();
            scanner = new Scanner(System.in);
        }

        public void mostrarMenu() {
            int opcion;
            do {
                System.out.println("----- Menú de Libros -----");
                System.out.println("1. Insertar nuevo libro");
                System.out.println("2. Ver todos los libros");
                System.out.println("3. Actualizar libro");
                System.out.println("4. Eliminar libro");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        insertarLibro();
                        break;
                    case 2:
                        verLibros();
                        break;
                    case 3:
                        actualizarLibro();
                        break;
                    case 4:
                        eliminarLibro();
                        break;
                    case 0:
                        System.out.println("Saliendo del menú...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (opcion != 0);
        }

        private void insertarLibro() {
            Libro nuevoLibro = new Libro();
            System.out.print("Ingrese ISBN: ");
            nuevoLibro.setIsbn(scanner.nextLine());
            System.out.print("Ingrese Título: ");
            nuevoLibro.setTitulo(scanner.nextLine());
            System.out.print("Ingrese Autor: ");
            nuevoLibro.setAutor(scanner.nextLine());

            int resultado = memoriaLib.agregarLibro(nuevoLibro);
            if (resultado == 1) {
                System.out.println("Libro agregado exitosamente.");
            } else {
                System.out.println("Error al agregar el libro. Puede que ya exista o no sea válido.");
            }
        }

        private void verLibros() {
            List<Libro> libros = memoriaLib.getMemoriaLibros();
            if (libros.isEmpty()) {
                System.out.println("No hay libros registrados.");
            } else {
                System.out.println("Libros registrados:");
                for (Libro libro : libros) {
                    System.out.println("ISBN: " + libro.getIsbn() + ", Título: " + libro.getTitulo() + ", Autor: " + libro.getAutor());
                }
            }
        }

        private void actualizarLibro() {
            System.out.print("Ingrese el ISBN del libro a actualizar: ");
            String isbn = scanner.nextLine();

            Libro libroExistente = null;
            for (Libro libro : memoriaLib.getMemoriaLibros()) {
                if (libro.getIsbn().equals(isbn)) {
                    libroExistente = libro;
                    break;
                }
            }

            if (libroExistente != null) {
                System.out.print("Ingrese nuevo Título (actual: " + libroExistente.getTitulo() + "): ");
                libroExistente.setTitulo(scanner.nextLine());
                System.out.print("Ingrese nuevo Autor (actual: " + libroExistente.getAutor() + "): ");
                libroExistente.setAutor(scanner.nextLine());

                Libro libroActualizado = memoriaLib.actualizarLibro(libroExistente);
                if (libroActualizado != null) {
                    System.out.println("Libro actualizado exitosamente.");
                } else {
                    System.out.println("Error al actualizar el libro.");
                }
            } else {
                System.out.println("Libro no encontrado.");
            }
        }

        private void eliminarLibro() {
            System.out.print("Ingrese el ISBN del libro a eliminar: ");
            String isbn = scanner.nextLine();

            Libro libroAEliminar = null;
            for (Libro libro : memoriaLib.getMemoriaLibros()) {
                if (libro.getIsbn().equals(isbn)) {
                    libroAEliminar = libro;
                    break;
                }
            }

            if (libroAEliminar != null) {
                memoriaLib.eliminarLibro(libroAEliminar);
                System.out.println("Libro eliminado exitosamente.");
            } else {
                System.out.println("Libro no encontrado.");
            }
        }


    }

