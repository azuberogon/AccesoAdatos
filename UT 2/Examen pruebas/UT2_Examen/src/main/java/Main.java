import db.connection.MySQLConnection;
import db.model.Libro;
import db.service.LibroService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {

    //Creamos tanto el logger como el scanner para capturar teclado que usaremos a lo largo del programa
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    private static final Scanner sc = new Scanner(System.in);

    //Creamos un LibroService que usaremos durante el programa
    private static final LibroService LIBRO_SERVICE = new LibroService();

    public static void main (String[] args) {
        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        System.out.println("--------------- UT2 Examen --------------");
        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");

        //Menú que nos permite recargar la tabla para hacer nuestras pruebas y seguidamente carga el menú principal
        System.out.println("¿Quieres crear la tabla de Libros?");
        System.out.println("1-Sí");
        System.out.println("Cualquier otra cosa - No");
        String respuesta = sc.nextLine();
        if (respuesta.equals("1")) {
            try {
                ejecutarSQL("src/main/resources/SQL/libros.sql");
            } catch (IOException e) {
                logger.error("Error al leer el fichero: ", e.fillInStackTrace());
                System.exit(-1);
            } catch (SQLException e) {
                logger.error("Error al ejecutar el fichero de creación de Libros: ",
                        e.fillInStackTrace());
                System.exit(-1);
            }
        }
        opcionesUsuario();
    }

    //Opciones del menú
    //TODO START 10.- Modifica el método para que incluya la nueva operación de búsqueda por autor
    private static void opcionesUsuario() {
        String opcion = "0";

        while (!opcion.equals("s")) {
            opcion = mostrarMenu();
            switch (opcion) {
                case "1":
                    anadirLibro();
                    break;
                case "2":
                    actualizarLibro();
                    break;
                case "3":
                    listarLibro();
                    break;
                case "4":
                    listarLibros();
                    break;
                case "5":
                    eliminarLibro();
                    break;
                case "6":
                    buscarAutor();
                    break;
                case "s":
                    System.out.println("Esperamos que la experiencia haya sido de su agrado. ¡Adios!");
                    break;
                default:
                    System.out.println("Opción no valida. Prueba otra vez...");
            }
        }
    }
    //TODO END


    //Menú de texto
    //TODO START 11.- Modifica el método para que el menú incluya la nueva operación de búsqueda por autor
    private static String mostrarMenu() {
        System.out.println("""
                
                **************************************************
                **************************************************
                *******************    MENÚ    *******************
                **************************************************
                **************************************************
                Elige una de las siguientes opciones:
                \t1 - Añadir libro
                \t2 - Actualizar libro
                \t3 - Consultar libro por ISBN
                \t4 - Listar todos los libros
                \t5 - Eliminar un libro
                \t6 - Buscar por autor
                \ts - Salir""");
        return sc.nextLine();
    }
    //TODO END


    //Metodo lanzado desde el menú para hacer búsquedas por autor
    private static void buscarAutor(){
        System.out.print("Introduzca el texto que desea buscar en autor: ");
        //Guardamos la cadena a buscar aqui
        String autor = sc.nextLine();
        LibroService libroService = new LibroService();

        if (autor.isEmpty() || autor.isBlank()) {
            logger.info("Búsqueda por autor vacía. Buscaremos autores nulos");
            autor = null;
        }
        List<Libro> libros = libroService.obtenerPorAutor(autor);
        for (Libro libro : libros){
            System.out.println(libro);
        }


        //TODO START 12.- Termina de escribir el método que haga uso del método de la clase de servicio obtenerPorAutor
        // para buscar libros que coincidan con lo previamente guardado en autor.
        // Si no encuentra ninguno se notifica y si hay resultados se imprimen por pantalla.



        //TODO END
    }




    //Metodo para eliminar libros desde el menú
    private static void eliminarLibro() {
        System.out.println("Introduzca el código ISBN del libro que desea eliminar: ");
        int codigo;
        try {
            codigo = Integer.parseInt(sc.nextLine());
        } catch (InputMismatchException e) {
            logger.error("Error al obtener el código ISBN del libro: ", e.fillInStackTrace());
            return;
        }

        int eliminar = LIBRO_SERVICE.eliminar(codigo);
        if (eliminar == 0)
            System.out.println("Libro eliminado con éxito");
        else System.out.println("El libro no se ha podido eliminar. Código de error: " + eliminar);
    }

    //Metodo para actualizar libros desde el menu
    private static void actualizarLibro() {
        System.out.println("Introduzca el código ISBN del libro que desea actualizar: ");
        Libro libro = obtenerLibro();

        if (libro != null) {
            System.out.print("Introduzca el nuevo título: ");
            libro.setTitulo(sc.nextLine());
            LIBRO_SERVICE.actualizar(libro);
        }
    }

    //Metodo para buscar un libro por ISBN
    private static Libro obtenerLibro() {
        int isbn;
        try {
            isbn = Integer.parseInt(sc.nextLine());
        } catch (InputMismatchException e) {
            logger.error("Error al obtener el código ISBN del libro: ", e.fillInStackTrace());
            return null;
        }

        Libro libro = LIBRO_SERVICE.obtenerPorId(isbn);
        if (libro == null) {
            System.out.println("No existe ningún libro con ese código ISBN");
            return null;
        }

        System.out.println("Se ha encontrado el siguiente libro con el código ISBN " + isbn + ": ");
        System.out.println(libro);
        return libro;
    }

    //Metodo usado por el menú para buscar un libro por ISBN
    private static void listarLibro() {
        System.out.print("Introduzca el código ISBN del libro del cual solicita información: ");
        obtenerLibro();
    }

    //Metodo usado por el menu para listar todos los libros
    private static void listarLibros() {
        List<Libro> libros = LIBRO_SERVICE.obtenerTodos();
        System.out.println("Se han encontrado " + libros.size() + " libros:");
        for (Libro libro : libros)
            System.out.println(libro);
    }

    //Metodo usado por el menú para añadir un libro a la bd
    private static void anadirLibro() {
        Libro libro = null;
        try {

            System.out.print("Introduzca el código ISBN del libro: ");
            Integer isbn = Integer.parseInt(sc.nextLine());

            System.out.print("Introduzca el título: ");
            String titulo = sc.nextLine();

            System.out.print("Introduzca el género: ");
            String tipo = sc.nextLine();

            System.out.print("Introduzca el autor: ");
            String autor = sc.nextLine();
            if (autor.isBlank() || autor.isEmpty()) autor = null;

            System.out.print("Introduzca el precio: ");
            Double precio = Double.parseDouble(sc.nextLine());

            libro = new Libro(isbn, titulo, tipo, autor, precio);
        } catch (Exception e) {
            logger.error("Error al crear el nuevo libro: ", e.fillInStackTrace());
            return;
        }

        int insertar = LIBRO_SERVICE.insertar(libro);
        if (insertar == 0)
            System.out.println("Libro insertado correctamente");
        else System.out.println("El libro no se ha podido insertar. Código de error: " + insertar);
    }






    //Metodos para crear y poblar la tabla
    private static void ejecutarSQL(String fichero) throws IOException, SQLException {
        String contenidoFicheroSQL = leerFicheroSQL(fichero);

        Connection connection = MySQLConnection.getInstance().getConnection();
        if (connection != null) {
            // Ejecutar todas las consultas en un único executeUpdate()
            try (PreparedStatement stmt = connection.prepareStatement(contenidoFicheroSQL)) {
                stmt.executeUpdate();
                logger.info("Tabla Libros creada correctamente");
            }
        } else
            throw new SQLException("Error al conectar con la Base de Datos. No es posible crear la tabla Libros");
    }

    private static String leerFicheroSQL(String sqlFilePath) throws IOException {
        // Leer el archivo SQL y construir el script en un StringBuilder
        StringBuilder script = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(sqlFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Añadir cada línea al script con el separador
                //      de línea del sistema (independiente de Windows o Linux)
                script.append(line).append(System.lineSeparator());
            }
        }

        return script.toString();
    }


}
