import db.connection.MySQLConnection;
import db.model.Factura;
import db.service.FacturaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    private static final Scanner sc = new Scanner(System.in);
    private static final FacturaService facturaService = new FacturaService();

    public static void main (String[] args) {
        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        System.out.println("------- UT2 A6 - Capa de Servicios ------");
        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");

        System.out.println("¿Quieres crear la tabla de Facturas?");
        System.out.println("1-Sí");
        System.out.println("Cualquier otra cosa - No");
        String respuesta = sc.nextLine();
        if (respuesta.equals("1")) {
            try {
                ejecutarSQL("src/main/resources/SQL/facturas.sql");
            } catch (IOException e) {
                logger.error("Error al leer el fichero: ", e.fillInStackTrace());
                System.exit(-1);
            } catch (SQLException e) {
                logger.error("Error al ejecutar el fichero de creación de Facturas: ",
                        e.fillInStackTrace());
                System.exit(-1);
            }
        }
        opcionesUsuario();
    }

    private static void opcionesUsuario() {
        String opcion = "0";

        while (!opcion.equals("6")) {
            opcion = mostrarMenu();
            switch (opcion) {
                case "1":
                    anadirFactura();
                    break;
                case "2":
                    actualizarFactura();
                    break;
                case "3":
                    listarFactura();
                    break;
                case "4":
                    listarFacturas();
                    break;
                case "5":
                    eliminarFactura();
                    break;
                case "6":
                    System.out.println("Esperamos que la experiencia haya sido de su agrado. ¡Adios!");
                    break;
                default:
                    System.out.println("Opción no valida. Prueba otra vez...");
            }
        }
    }

    private static void eliminarFactura() {
        System.out.println("Introduzca el código de la factura que desea eliminar: ");
        int codigo;
        try {
            codigo = Integer.parseInt(sc.nextLine());
        } catch (InputMismatchException e) {
            logger.error("Error al obtener el código de la factura: ", e.fillInStackTrace());
            return;
        }

        int eliminar = facturaService.eliminar(codigo);
        if (eliminar == 0)
            System.out.println("Factura eliminada con éxito");
        else System.out.println("La factura no se ha podido eliminar. Código de error: " + eliminar);
    }

    private static void actualizarFactura() {
        System.out.println("Introduzca el código de la factura que desea actualizar: ");
        Factura factura = obtenerFactura();

        if (factura != null) {
            // Permitir actualizar únicamente el destinatario por simplificar código
            System.out.print("Introduzca el nuevo destinatario: ");
            factura.setDestinatario(sc.nextLine());
            facturaService.actualizar(factura);
        }
    }

    private static Factura obtenerFactura() {
        int codigo;
        try {
            codigo = Integer.parseInt(sc.nextLine());
        } catch (InputMismatchException e) {
            logger.error("Error al obtener el código de la factura: ", e.fillInStackTrace());
            return null;
        }

        Factura factura = facturaService.obtenerPorId(codigo);
        if (factura == null) {
            System.out.println("No existe ninguna factura con ese código");
            return null;
        }

        System.out.println("Se ha encontrado la siguiente factura con el código " + codigo + ": ");
        System.out.println(factura);
        return factura;
    }

    private static void listarFactura() {
        System.out.print("Introduzca el código de la factura de la cual solicita información: ");
        obtenerFactura();
    }

    private static void listarFacturas() {
        List<Factura> facturas = facturaService.obtenerTodos();
        System.out.println("Se han encontrado " + facturas.size() + " facturas:");
        for (Factura factura : facturas)
            System.out.println(factura);
    }

    private static void anadirFactura() {
        Factura factura = null;
        try {

            System.out.print("Introduzca el código de operación: ");
            Integer codigo = Integer.parseInt(sc.nextLine());

            System.out.print("Introduzca el destinatario: ");
            String destinatario = sc.nextLine();

            System.out.print("Introduzca la cuenta de cobro: ");
            Integer cuenta = Integer.parseInt(sc.nextLine());

            System.out.print("Introduzca el importe: ");
            Double importe = Double.parseDouble(sc.nextLine());

            //Fecha y hora la actual
            LocalDateTime fechaHora = LocalDateTime.now();

            factura = new Factura(codigo, destinatario, cuenta, importe, fechaHora);
        } catch (Exception e) {
            logger.error("Error al crear el Factura para insertar: ", e.fillInStackTrace());
            return;
        }

        int insertar = facturaService.insertar(factura);
        if (insertar == 0)
            System.out.println("Factura insertado correctamente");
        else System.out.println("La factura no se ha podido insertar. Código de error: " + insertar);
    }

    private static String mostrarMenu() {
        System.out.println("""
                
                **************************************************
                **************************************************
                *******************    MENÚ    *******************
                **************************************************
                **************************************************
                Elige una de las siguientes opciones:
                \t1 - Añadir factura
                \t2 - Actualizar factura
                \t3 - Consultar factura
                \t4 - Listar todas las facturas
                \t5 - Eliminar una factura
                \t6 - Salir""");
        return sc.nextLine();
    }

    private static void ejecutarSQL(String fichero) throws IOException, SQLException {
        String contenidoFicheroSQL = leerFicheroSQL(fichero);

        Connection connection = MySQLConnection.getInstance().getConnection();
        if (connection != null) {
            // Ejecutar todas las consultas en un único executeUpdate()
            try (PreparedStatement stmt = connection.prepareStatement(contenidoFicheroSQL)) {
                stmt.executeUpdate();
                logger.info("Tabla Facturas creada correctamente");
            }
        } else
            throw new SQLException("Error al conectar con la Base de Datos. No es posible crear la tabla Facturas");
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
