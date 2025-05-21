import db.connection.MySQLConnection;
import db.model.*;
import db.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    private static final Scanner sc = new Scanner(System.in);

    private static final VideojuegoService videojuegoService = new VideojuegoService();
    private static final HistoriaService historiaService = new HistoriaService();
    private static final ClienteService clienteService = new ClienteService();
    private static final PedidoService pedidoService = new PedidoService();
    private static final ContieneService contieneService = new ContieneService();

    public static void main(String[] args) {
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
        System.out.println("---------------- UT2 EXAMEN --------------");
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");

        System.out.println("Se va a borrar y crear la BBDD entera");
        try {
            ejecutarSQL("src/main/resources/sql/ut2_examen.sql");
        } catch (IOException e) {
            logger.error("Error al leer el fichero: ", e.fillInStackTrace());
            System.exit(-1);
        } catch (SQLException e) {
            logger.error("Error al ejecutar el fichero de creación de videojuegos: ", e.fillInStackTrace());
            System.exit(-1);
        }
        System.out.println("BBDD Regenerada correctamente");

        tuTest();
    }

    private static void tuTest() {
        // TODO START

        /* Videojuego y Historia asociada */

        Historia h = new Historia(0, "null", "null");
        Videojuego v = new Videojuego(0, "null", "null", 300, 9.0, LocalDate.now(), true, h);
        videojuegoService.insertar(v);

        List<Integer> pedidos = new ArrayList<>();
        Cliente cliente = new Cliente(0, "daniel", "Medina", LocalDate.now(), pedidos);

        clienteService.insertar(cliente);

        List<Contiene> contienes = null;
        Pedido pedido = new Pedido(0, cliente, LocalDate.now(), null);

        // TODO END
    }


    private static void ejecutarSQL(String fichero) throws IOException, SQLException {
        String contenidoFicheroSQL = leerFicheroSQL(fichero);

        Connection connection = MySQLConnection.getInstance().getConnection();
        if (connection != null) {
            // Ejecutar todas las consultas en un único executeUpdate()
            try (PreparedStatement stmt = connection.prepareStatement(contenidoFicheroSQL)) {
                stmt.executeUpdate();
                logger.trace("Tablas creadas correctamente");
            }
        } else
            throw new SQLException("Error al conectar con la Base de Datos. No es posible crear las tablas");
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
