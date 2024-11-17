package es.mariana.ut2.a4.db.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQLConnection {
    // Obtén una instancia del logger. Ver notas al final del documento
    private static final Logger logger = LogManager.getLogger(MYSQLConnection.class.getName());

    // La única instancia de la clase
    private static MYSQLConnection instance;

    // El objeto Connection
    private Connection connection;

    // Datos de conexión
    private final String PORT = "3306"; //puerto
    private final String SERVER = "localhost";
    private static final String DB = "bdempleadosoficinas_eneko";
    private final String DBMS = "mysql";
    private final String URL = "jdbc:" + DBMS + "://"+ SERVER + ":" + PORT + "/" + DB;
    private final String USER = "root";
    private final String PASSWORD = "root";

    // Constructor privado para evitar que se creen nuevas instancias
    private MYSQLConnection() {
        try {
            // Intentamos establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.trace("Conexión establecida con la base de datos '{}'", DB);
        } catch (SQLException e) {
            logger.error("Error al conectar a la base de datos '{}': "
                    , DB, e.fillInStackTrace());
        }
    }

    // Método para obtener la única instancia de la clase (Singleton)
    public static MYSQLConnection getInstancia() {
        if (instance == null) {
            instance = new MYSQLConnection();
        }
        return instance;
    }

    // Método para obtener la conexión. Sólo accedemos a la conexión a traves de nuestra única instancia
    public Connection getConnection() {
        return connection;
    }

    //Metodo para obtener el nombre de la BD
    public static String getDB() {
        return DB;
    }
}
