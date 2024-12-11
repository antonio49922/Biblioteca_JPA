package libreria;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Menú de Libros");
            System.out.println("2. Menú de Usuarios");
            System.out.println("3. Menú de Préstamos");
            System.out.println("4. Menú de Ejemplares");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    MenuLibro menuLibro = new MenuLibro();
                    menuLibro.mostrarMenu();
                    break;
                case 2:
                    MenuUsuario menuUsuario = new MenuUsuario();
                    menuUsuario.mostrarMenu();
                    break;
                case 3:
                    MenuPrestamo menuPrestamo = new MenuPrestamo();
                    menuPrestamo.mostrarMenu();
                    break;
                case 4:
                    MenuEjemplar menuEjemplar = new MenuEjemplar();
                    menuEjemplar.mostrarMenu();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
