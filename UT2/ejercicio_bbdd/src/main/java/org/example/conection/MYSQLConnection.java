package org.example.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MYSQLConnection {
    // Logger para registrar eventos
    private static final Logger logger = LogManager.getLogger(MYSQLConnection.class.getName());

    // La única instancia de la clase (Singleton)
    private static MYSQLConnection instance;

    // Objeto Connection
    private Connection connection;

    // Datos de conexión
    private final String PORT = "3306";
    private final String SERVER = "localhost";
    private static final String DB = "bdEmpleadosOficinas_Aitor";
    private final String DBMS = "mysql";
    private final String URL = "jdbc:" + DBMS + "://" + SERVER + ":" + PORT + "/" + DB;
    private final String USER = "root";
    private final String PASSWORD = "root";

    // Constructor privado para evitar que se creen nuevas instancias
    private MYSQLConnection() {
        conectar(); // Establece la conexión cuando se crea la instancia
    }

    // Método para establecer una nueva conexión
    private void conectar() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("Conexión establecida con la base de datos '{}'", DB);
        } catch (SQLException e) {
            logger.error("Error al conectar a la base de datos '{}': ", DB, e);
        }
    }

    // Método para obtener la única instancia de la clase (Singleton)
    public static MYSQLConnection getInstancia() {
        if (instance == null) {
            instance = new MYSQLConnection();
        }
        return instance;
    }

    // Método para obtener la conexión, asegurando que esté abierta
    public Connection getConnection() {
        try {
            // Verifica si la conexión es nula o está cerrada
            if (connection == null || connection.isClosed()) {
                logger.warn("Conexión cerrada o nula, intentando reconectar...");
                conectar(); // Intenta restablecer la conexión
            }
        } catch (SQLException e) {
            logger.error("Error al verificar el estado de la conexión: ", e);
        }
        return connection;
    }

    // Método para obtener el nombre de la BD
    public static String getDB() {
        return DB;
    }
}
