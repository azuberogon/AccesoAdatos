package db.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    // Obtén una instancia del logger
    private static final Logger logger = LogManager.getLogger(MySQLConnection.class.getName());

    // La única instancia de la clase
    private static MySQLConnection instance;

    // El objeto Connection
    private Connection connection;

    // Datos de conexión

    //TODO START 3.- Modifica los parámetros necesarios para que el programa funcione correctamente
    private final String SERVER = "";
    private final String PORT = "";
    private static final String DB = "";
    private final String DBMS = "mysql";
    private final String URL = "jdbc:" + DBMS + "://"+ SERVER +":" + PORT + "/" + DB + "?allowMultiQueries=true";
    private final String USER = "root";
    private final String PASSWORD = "root";
    //TODO END


    // Constructor privado para evitar que se creen nuevas instancias
    private MySQLConnection() {
        try {
            // Intentamos establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.trace("Conexión establecida con la base de datos '{}'", DB);
        } catch (SQLException e) {
            logger.error("Error al conectar a la base de datos '{}': ", DB, e.fillInStackTrace());
        }
    }

    // Metodo para obtener la única instancia de la clase (Singleton)
    public static MySQLConnection getInstance() {
        if (instance == null) {
            instance = new MySQLConnection();
        }
        return instance;
    }

    // Metodo para obtener la conexión
    public Connection getConnection() {
        return connection;
    }

    public static String getDB() {
        return DB;
    }
}
