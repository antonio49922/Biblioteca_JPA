package libreria;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuPrestamo {
    private MemoriaPrestamo memoriaPrestamo;
    private Validaciones validaciones;
    private Scanner scanner;

    public MenuPrestamo() {
        memoriaPrestamo = new MemoriaPrestamo();
        memoriaPrestamo.Inicializar();
        validaciones = new Validaciones();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("----- Menú de Préstamos -----");
            System.out.println("1. Agregar nuevo préstamo");
            System.out.println("2. Ver todos los préstamos");
            System.out.println("3. Actualizar préstamo");
            System.out.println("4. Eliminar préstamo");
            System.out.println("5. Hacer préstamo");
            System.out.println("6. Hacer devolución");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    agregarPrestamo();
                    break;
                case 2:
                    verPrestamos();
                    break;
                case 3:
                    actualizarPrestamo();
                    break;
                case 4:
                    eliminarPrestamo();
                    break;
                case 5:
                    hacerPrestamo();
                    break;
                case 6:
                    hacerDevolucion();
                    break;
                case 0:
                    System.out.println("Saliendo del menú...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private void agregarPrestamo() {
        Prestamo nuevoPrestamo = new Prestamo();
        // Aquí puedes agregar lógica para establecer el usuario y el ejemplar
        // Por ejemplo, puedes pedir el ID del usuario y del ejemplar
        System.out.print("Ingrese ID del usuario: ");
        int usuarioId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        // Aquí deberías buscar el usuario por ID en tu sistema

        System.out.print("Ingrese ID del ejemplar: ");
        int ejemplarId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        // Aquí deberías buscar el ejemplar por ID en tu sistema

        // Asignar usuario y ejemplar al nuevo préstamo
        // nuevoPrestamo.setUsuario(usuario);
        // nuevoPrestamo.setEjemplar(ejemplar);
        nuevoPrestamo.setFechaInicio(LocalDate.now());
        nuevoPrestamo.setFechaDevolucion(LocalDate.now().plusDays(15)); // Por ejemplo, 15 días de préstamo

        int resultado = memoriaPrestamo.agregarPrestamo(nuevoPrestamo);
        if (resultado == 1) {
            System.out.println("Préstamo agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el préstamo. Puede que ya exista o no sea válido.");
        }
    }

    private void verPrestamos() {
        List<Prestamo> prestamos = memoriaPrestamo.getMemoria();
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
        } else {
            System.out.println("Préstamos registrados:");
            for (Prestamo prestamo : prestamos) {
                System.out.println("ID: " + prestamo.getId() + ", Usuario ID: " + prestamo.getUsuario().getId() +
                        ", Ejemplar ID: " + prestamo.getEjemplar().getId() +
                        ", Fecha de inicio: " + prestamo.getFechaInicio() +
                        ", Fecha de devolución: " + prestamo.getFechaDevolucion());
            }
        }
    }

    private void actualizarPrestamo() {
        System.out.print("Ingrese el ID del préstamo a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Prestamo prestamoExistente = null;
        for (Prestamo prestamo : memoriaPrestamo.getMemoria()) {
            if (prestamo.getId().equals(id)) {
                prestamoExistente = prestamo;
                break;
            }
        }

        if (prestamoExistente != null) {
            // Aquí puedes actualizar los campos que desees
            System.out.print("Ingrese nueva fecha de devolución (actual: " + prestamoExistente.getFechaDevolucion() + "): ");
            String nuevaFecha = scanner.nextLine();
            // Aquí deberías convertir la nueva fecha a un objeto `LocalDate` y establecerla en el préstamo existente
            prestamoExistente.setFechaDevolucion(LocalDate.parse(nuevaFecha));

            Prestamo prestamoActualizado = memoriaPrestamo.actualizarPrestamo(prestamoExistente);
            if (prestamoActualizado != null) {
                System.out.println("Préstamo actualizado exitosamente.");
            } else {
                System.out.println("Error al actualizar el préstamo.");
            }
        } else {
            System.out.println("Préstamo no encontrado.");
        }
    }

    private void eliminarPrestamo() {
        System.out.print("Ingrese el ID del préstamo a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Prestamo prestamoAEliminar = null;
        for (Prestamo prestamo : memoriaPrestamo.getMemoria()) {
            if (prestamo.getId().equals(id)) {
                prestamoAEliminar = prestamo;
                break;
            }
        }

        if (prestamoAEliminar != null) {
            memoriaPrestamo.eliminarPrestamo(prestamoAEliminar);
            System.out.println("Préstamo eliminado exitosamente.");
        } else {
            System.out.println("Préstamo no encontrado.");
        }
    }

    private void hacerPrestamo() {
        // Aquí puedes implementar la lógica para hacer un préstamo
        System.out.print("Ingrese ID del usuario: ");
        int usuarioId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese ID del ejemplar: ");
        int ejemplarId = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        // Aquí deberías buscar el usuario y el ejemplar por ID en tu sistema
        // Luego, puedes llamar a la función de validación y hacer el préstamo
        // validaciones.hacerPrestamo(usuario, ejemplar);
    }

    private void hacerDevolucion() {
        System.out.print("Ingrese el ID del préstamo a devolver: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Prestamo prestamoADevolver = null;
        for (Prestamo prestamo : memoriaPrestamo.getMemoria()) {
            if (prestamo.getId().equals(id)) {
                prestamoADevolver = prestamo;
                break;
            }
        }

        if (prestamoADevolver != null) {
            try {
                validaciones.hacerDevolucion(prestamoADevolver);
                memoriaPrestamo.eliminarPrestamo(prestamoADevolver); // Eliminar el préstamo de la memoria
                System.out.println("Devolución realizada exitosamente.");
            } catch (Exception e) {
                System.out.println("Error al realizar la devolución: " + e.getMessage());
            }
        } else {
            System.out.println("Préstamo no encontrado.");
        }
    }

}
