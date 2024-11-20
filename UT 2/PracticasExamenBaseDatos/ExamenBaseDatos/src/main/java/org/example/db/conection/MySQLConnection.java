package org.example.db.conection;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {//MYSQLConnection
    private static final Logger logger = LogManager.getLogger(MySQLConnection.class.getName());//te pide un string le pasamos la clase y en concreto
    private static MySQLConnection instance;
    //conexion a la base de datos estructura {SERVER, PORT, DB, DBMS, URL, USER, PASSWORD}
    private Connection connection;
    private final String SEVER = "localhost";//servidor se puede sustituir por una ip para conectarse
    private final String PORT = "3309";// puerto en el que se encuentra la base de datos
    private static final String DB = "bdbasicas";
    private final String DBMS = "mysql";// base de datos
    private final String URL = "jdbc:" +DBMS +"://"+SEVER+ ":" +PORT+"/"+DB+"?allowMultiQueries=true";// base de datos
    private final String USER = "root";
    private final String PASSWORD = "root";



    private MySQLConnection() {
        try {
            // Intentamos establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.trace("Conexión establecida con la base de datos '{}'", DB);
        } catch (SQLException e) {
            logger.error("Error al conectar a la base de datos '{}': ", DB, e.fillInStackTrace());
        }
    }
    public static MySQLConnection getInstance() {
        if (instance == null) {
            instance = new MySQLConnection();
        }
        return instance;
    }
    // Método para obtener la conexión
    public Connection getConnection() {
        return connection;
    }

    public static String getDB() {
        return DB;
    }
}
