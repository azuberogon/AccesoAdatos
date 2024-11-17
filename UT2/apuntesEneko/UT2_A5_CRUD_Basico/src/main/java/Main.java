import db.connection.MySQLConnection;
import db.dao.FacturaDAO;
import db.model.Factura;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main (String[] args) {
        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        System.out.println("---------- UT2 A5 - CRUD Básico ---------");
        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");

        Scanner sc = new Scanner(System.in);
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
                logger.error("Error al ejecutar el fichero de creación de Facturas: ", e.fillInStackTrace());
                System.exit(-1);
            }
        }

        System.out.println("¿Quieres probar el CRUD de Facturas?");
        System.out.println("1-Sí");
        System.out.println("Cualquier otra cosa - No");
        respuesta = sc.nextLine();
        if (respuesta.equals("1"))
            testCRUD();
    }

    private static void testCRUD() {
        FacturaDAO facturaDAO = new FacturaDAO();
        insertarFactura(facturaDAO);
        consultarTodasLasFacturas(facturaDAO);
        actualizarFactura(facturaDAO);
        consultarFactura(facturaDAO);
        eliminarFactura(facturaDAO);
    }

    private static void eliminarFactura(FacturaDAO facturaDAO) {
        System.out.println("\n\n\n--------------------------------------");
        System.out.println("   5 - Delete - Eliminar Factura   ");
        System.out.println("--------------------------------------");

        try {
            facturaDAO.eliminar(27792);
            // IMPORTANTE: si lo ejecutáis varias veces sin volver a crear la tabla fallará
            logger.info("(Delete) Has eliminado a Mark Zuckerberg, código 27792, con éxito.");
        } catch (SQLException e) {
            logger.error("Error al eliminar la factura con código 27792. " +
                    "Mark Zuckerberg es más resistente de lo esperado:\n", e.fillInStackTrace());
        }
    }

    private static void actualizarFactura(FacturaDAO facturaDAO) {
        System.out.println("\n\n\n---------------------------------------");
        System.out.println("   4 - Update - Modificar Factura   ");
        System.out.println("---------------------------------------");

        System.out.println("Primero obtenemos la factura de tito Gates (código 354503)");
        Factura Factura;
        try {
            Factura = facturaDAO.obtenerPorId(354503);
        } catch (SQLException e) {
            logger.error("Error al obtener Factura código 354503, alias Bill Gates: ", e.fillInStackTrace());
            return;
        }

        try {
            System.out.println("Incrementamos la factura de Guillermo Puertas, código "
                    + Factura.getCodigo() + ", unos cuantos ceros (algo así como 5 o 6 ceros)");
            Factura.setImporte(Factura.getImporte() * 100000.0);
            facturaDAO.actualizar(Factura);
            logger.info("(Update) Factura actualizada con éxito. ¡Chin chin chin!");
        } catch (SQLException e) {
            logger.error("Error al actualizar la factura de tiito Gates (codigo 354503). " +
                    "Es un tacaño: ", e.fillInStackTrace());
        }
    }

    private static void consultarTodasLasFacturas(FacturaDAO facturaDAO) {
        System.out.println("\n\n\n------------------------------------------------");
        System.out.println("   3 - Read - Consultar todas las Facturas   ");
        System.out.println("------------------------------------------------");

        try {
            List<Factura> Facturas = facturaDAO.obtenerTodos();
            for (Factura Factura : Facturas)
                System.out.println(Factura + "\n------------------------------------------\n");
            logger.info("(Read) Factura leídas.");
        } catch (SQLException e) {
            logger.error("Error al obtener todas las Facturas: ", e.fillInStackTrace());
        }
    }

    private static void consultarFactura(FacturaDAO facturaDAO) {
        System.out.println("\n\n\n----------------------------------------");
        System.out.println("   2 - Read - Consultar una Factura   ");
        System.out.println("----------------------------------------");

        try {
            Factura Factura = facturaDAO.obtenerPorId(354503);
            System.out.println(Factura);
        } catch (SQLException e) {
            logger.error("Error al obtener la factura de Guillermo Puertas (codigo 354503): ", e.fillInStackTrace());
        }
    }

    private static void insertarFactura(FacturaDAO facturaDAO) {
        System.out.println("\n\n\n-----------------------------------------");
        System.out.println("   1 - Create - Insertar una Factura   ");
        System.out.println("-----------------------------------------");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.DATE, 24);
        LocalDateTime localDateTime = LocalDateTime.now();

        Factura Factura = new Factura(
                666666,
                "Lucifer",
                66666666,
                666.66,
                localDateTime);
        try {
            facturaDAO.insertar(Factura);
            logger.info("(Create) Factura insertada con éxito. Lucifer entra en el club de morosos.");
        } catch (SQLException e) {
            logger.error("Error al insertar a Lucifer en el club: ", e.fillInStackTrace());
        }
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
