package libreria;

    import java.util.List;
    import java.util.Scanner;

    public class MenuUsuario {
        private MemoriaUsuario memoriaUsuario;
        private Validaciones validaciones;
        private Scanner scanner;

        public MenuUsuario() {
            memoriaUsuario = new MemoriaUsuario();
            memoriaUsuario.Inicializar();
            validaciones = new Validaciones();
            scanner = new Scanner(System.in);
        }

        public void mostrarMenu() {
            int opcion;
            do {
                System.out.println("----- Menú de Usuario -----");
                System.out.println("1. Agregar nuevo usuario");
                System.out.println("2. Mostrar usuarios");
                System.out.println("3. Actualizar usuario");
                System.out.println("4. Eliminar usuario");
                System.out.println("5. Ver préstamos");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        agregarUsuario();
                        break;
                    case 2:
                        mostrarUsuarios();
                        break;
                    case 3:
                        actualizarUsuario();
                        break;
                    case 4:
                        eliminarUsuario();
                        break;
                    case 5:
                        verPrestamos();
                        break;
                    case 0:
                        System.out.println("Saliendo del menú...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (opcion != 0);
        }

        private void agregarUsuario() {
            Usuario nuevoUsuario = new Usuario();
            System.out.print("Ingrese DNI: ");
            nuevoUsuario.setDni(scanner.nextLine());
            System.out.print("Ingrese Nombre: ");
            nuevoUsuario.setNombre(scanner.nextLine());
            System.out.print("Ingrese Email: ");
            nuevoUsuario.setEmail(scanner.nextLine());
            System.out.print("Ingrese Password: ");
            nuevoUsuario.setPassword(scanner.nextLine());
            System.out.print("Ingrese Tipo (normal/administrador): ");
            nuevoUsuario.setTipo(scanner.nextLine());

            int resultado = memoriaUsuario.agregarUsuario(nuevoUsuario);
            if (resultado == 1) {
                System.out.println("Usuario agregado exitosamente.");
            } else {
                System.out.println("Error al agregar el usuario. Puede que ya exista.");
            }
        }

        private void mostrarUsuarios() {
            List<Usuario> usuarios = memoriaUsuario.getMemoria();
            if (usuarios.isEmpty()) {
                System.out.println("No hay usuarios registrados.");
            } else {
                System.out.println("Usuarios registrados:");
                for (Usuario usuario : usuarios) {
                    System.out.println("ID: " + usuario.getId() + ", DNI: " + usuario.getDni() + ", Nombre: " + usuario.getNombre());
                }
            }
        }

        private void actualizarUsuario() {
            System.out.print("Ingrese el ID del usuario a actualizar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            Usuario usuarioExistente = null;
            for (Usuario usuario : memoriaUsuario.getMemoria()) {
                if (usuario.getId().equals(id)) {
                    usuarioExistente = usuario;
                    break;
                }
            }

            if (usuarioExistente != null) {
                System.out.print("Ingrese nuevo DNI (actual: " + usuarioExistente.getDni() + "): ");
                usuarioExistente.setDni(scanner.nextLine());
                System.out.print("Ingrese nuevo Nombre (actual: " + usuarioExistente.getNombre() + "): ");
                usuarioExistente.setNombre(scanner.nextLine());
                System.out.print("Ingrese nuevo Email (actual: " + usuarioExistente.getEmail() + "): ");
                usuarioExistente.setEmail(scanner.nextLine());
                System.out.print("Ingrese nueva Password (actual: " + usuarioExistente.getPassword() + "): ");
                usuarioExistente.setPassword(scanner.nextLine());
                System.out.print("Ingrese nuevo Tipo (actual: " + usuarioExistente.getTipo() + "): ");
                usuarioExistente.setTipo(scanner.nextLine());

                Usuario usuarioActualizado = memoriaUsuario.actualizarUsuario(usuarioExistente);
                if (usuarioActualizado != null) {
                    System.out.println("Usuario actualizado exitosamente.");
                } else {
                    System.out.println("Error al actualizar el usuario.");
                }
            } else {
                System.out.println("Usuario no encontrado.");
            }
        }

        private void eliminarUsuario() {
            System.out.print("Ingrese el ID del usuario a eliminar: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            Usuario usuarioAEliminar = null;
            for (Usuario usuario : memoriaUsuario.getMemoria()) {
                if (usuario.getId().equals(id)) {
                    usuarioAEliminar = usuario;
                    break;
                }
            }

            if (usuarioAEliminar != null) {
                memoriaUsuario.eliminarUsuario(usuarioAEliminar);
                System.out.println("Usuario eliminado exitosamente.");
            } else {
                System.out.println("Usuario no encontrado.");
            }
        }

        private void verPrestamos() {
            System.out.print("Ingrese el ID del usuario para ver sus préstamos: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            Usuario usuario = null;
            for (Usuario u : memoriaUsuario.getMemoria()) {
                if (u.getId().equals(id)) {
                    usuario = u;
                    break;
                }
            }

            if (usuario != null) {
                List<Prestamo> prestamos = validaciones.validarTipoUser (usuario);
                if (prestamos.isEmpty()) {
                    System.out.println("No hay préstamos registrados para este usuario.");
                } else {
                    System.out.println("Préstamos de " + usuario.getNombre() + ":");
                    for (Prestamo prestamo : prestamos) {
                        System.out.println("Ejemplar: " + prestamo.getEjemplar().getIsbn() + ", Fecha de inicio: " + prestamo.getFechaInicio() + ", Fecha de devolución: " + prestamo.getFechaDevolucion());
                    }
                }
            } else {
                System.out.println("Usuario no encontrado.");
            }
        }

        public static void main(String[] args) {
            MenuUsuario menu = new MenuUsuario();
            menu.mostrarMenu();
        }
    }
