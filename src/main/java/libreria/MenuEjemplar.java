package libreria;
import java.util.List;
import java.util.Scanner;

public class MenuEjemplar {
    private MemoriaEjemp memoriaEjemp;
    private Scanner scanner;

    public MenuEjemplar() {
        memoriaEjemp = new MemoriaEjemp();
        memoriaEjemp.Inicializar();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("----- Menú de Ejemplares -----");
            System.out.println("1. Agregar nuevo ejemplar");
            System.out.println("2. Ver todos los ejemplares");
            System.out.println("3. Actualizar ejemplar");
            System.out.println("4. Eliminar ejemplar");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    agregarEjemplar();
                    break;
                case 2:
                    verEjemplares();
                    break;
                case 3:
                    actualizarEjemplar();
                    break;
                case 4:
                    eliminarEjemplar();
                    break;
                case 0:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private void agregarEjemplar() {
        Ejemplar nuevoEjemplar = new Ejemplar();
        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = scanner.nextLine();
        // Aquí deberías buscar el libro por ISBN en tu sistema
        // Por ejemplo:
        // Libro libro = buscarLibroPorIsbn(isbn);
        // nuevoEjemplar.setIsbn(libro);

        nuevoEjemplar.setEstado("Disponible"); // Estado inicial

        int resultado = memoriaEjemp.agregarEjemplar(nuevoEjemplar);
        if (resultado == 1) {
            System.out.println("Ejemplar agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el ejemplar. Puede que ya exista o no sea válido.");
        }
    }

    private void verEjemplares() {
        List<Ejemplar> ejemplares = memoriaEjemp.getMemoria();
        if (ejemplares.isEmpty()) {
            System.out.println("No hay ejemplares registrados.");
        } else {
            System.out.println("Ejemplares registrados:");
            for (Ejemplar ejemplar : ejemplares) {
                System.out.println("ID: " + ejemplar.getId() + ", ISBN: " + ejemplar.getIsbn().getIsbn() +
                        ", Estado: " + ejemplar.getEstado());
            }
        }
    }

    private void actualizarEjemplar() {
        System.out.print("Ingrese el ID del ejemplar a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Ejemplar ejemplarExistente = null;
        for (Ejemplar ejemplar : memoriaEjemp.getMemoria()) {
            if (ejemplar.getId().equals(id)) {
                ejemplarExistente = ejemplar;
                break;
            }
        }

        if (ejemplarExistente != null) {
            System.out.print("Ingrese nuevo estado (actual: " + ejemplarExistente.getEstado() + "): ");
            String nuevoEstado = scanner.nextLine();
            ejemplarExistente.setEstado(nuevoEstado);

            Ejemplar ejemplarActualizado = memoriaEjemp.actualizarEjemplar(ejemplarExistente);
            if (ejemplarActualizado != null) {
                System.out.println("Ejemplar actualizado exitosamente.");
            } else {
                System.out.println("Error al actualizar el ejemplar.");
            }
        } else {
            System.out.println("Ejemplar no encontrado.");
        }
    }

    private void eliminarEjemplar() {
        System.out.print("Ingrese el ID del ejemplar a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Ejemplar ejemplarAEliminar = null;
        for (Ejemplar ejemplar : memoriaEjemp.getMemoria()) {
            if (ejemplar.getId().equals(id)) {
                ejemplarAEliminar = ejemplar;
                break;
            }
        }

        if (ejemplarAEliminar != null) {
            memoriaEjemp.eliminarEjemplar(ejemplarAEliminar);
            System.out.println("Ejemplar eliminado exitosamente.");
        } else {
            System.out.println("Ejemplar no encontrado.");
        }
    }


}