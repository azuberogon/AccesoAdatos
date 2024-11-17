package es.mariana.ut2.a4;

import es.mariana.ut2.a4.db.connection.MYSQLConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.out.println("\n-----------------------------------------");
        System.out.println("------- UT2_A4 - Metadatos en BDR -------");
        System.out.println("-----------------------------------------");

        // Obtenemos la conexión y los metadatos de la BDR
        Connection connection = MYSQLConnection.getInstancia().getConnection();
        try {
            DatabaseMetaData dbMetaData = connection.getMetaData();
            mostrarMetadatos(dbMetaData);
        } catch (SQLException e) {
            logger.error("Ha ocurrido un error al obtener los metadatos: ", e.fillInStackTrace());
        }

    }

    // Función encargada de mostrar datos generales de la conexión,
    // todas las tablas, campos, claves primarias y claves foráneas
    // de cada tabla de la BDR
    private static void mostrarMetadatos(DatabaseMetaData dbMetaData) throws SQLException {
        //Primero vamos a obtener nombre y versión de la base del SGBD
        String dbProductName = dbMetaData.getDatabaseProductName();
        String dbProductVersion = dbMetaData.getDatabaseProductVersion();
        System.out.println("\n-----------------------------------------");
        System.out.println("SGBD: " + dbProductName + "\t\tVersión: " + dbProductVersion);
        System.out.println("-----------------------------------------");
        //Ahora vamos a obtener el usuario conectado
        System.out.println("Usuario actual: " + dbMetaData.getUserName());
        System.out.println("-----------------------------------------\n");

        //Mostramos en pantalla los nombres de las tablas
        listarTablas(dbMetaData);

        //Pedimos el nombre de la tabla mediante un mensaje y nos quedamos a la espera
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIntroduce el nombre de una tabla: ");
        //Guardamos la ruta y creamos un archivo a partir de ella
        String tabla = scanner.nextLine();
        System.out.println();
        if (tabla.isEmpty()) {
            mostrarTablas(dbMetaData, null);
        }
        else {
            mostrarTablas(dbMetaData, tabla);
        }

    }

    private static void mostrarTablas(DatabaseMetaData dbMetaData, String tableName) throws SQLException {
        // Obtengo todas las tablas de la base de datos
        ResultSet rsTablas = dbMetaData.getTables(
                MYSQLConnection.getDB(),
                null,
                tableName,
                new String[] {"TABLE"});

        while (rsTablas.next()) {
            String tabla = rsTablas.getString("TABLE_NAME");
            System.out.println("TABLA: " + tabla);
            mostrarColumnas(dbMetaData, tabla);
            mostrarClavesPrimarias(dbMetaData, tabla);
            mostrarClavesAjenas(dbMetaData, tabla);
            mostrarClavesPrimariasExportadas(dbMetaData, tabla);
            System.out.println("\n\n");
        }
    }

    private static void listarTablas(DatabaseMetaData dbMetaData) throws SQLException {
        // Obtengo todas las tablas de la base de datos
        ResultSet rsTablas = dbMetaData.getTables(
                MYSQLConnection.getDB(),
                null,
                null,
                new String[] {"TABLE"});
        System.out.println("TABLAS: ");
        while (rsTablas.next()) {
            String tabla = rsTablas.getString("TABLE_NAME");
            System.out.println("\t-> " + tabla);
        }
    }

    private static void mostrarColumnas(DatabaseMetaData dbMetaData, String tabla) throws SQLException {
        ResultSet rsColumnas = dbMetaData.getColumns(
                MYSQLConnection.getDB(),
                null,
                tabla,
                null);

        while (rsColumnas.next()) {
            System.out.println("\t" + rsColumnas.getString("COLUMN_NAME") +
                    ": " + rsColumnas.getString("TYPE_NAME"));
        }
    }

    private static void mostrarClavesPrimarias(DatabaseMetaData dbMetaData, String tabla) throws SQLException {
        ResultSet rsPK = dbMetaData.getPrimaryKeys(
                MYSQLConnection.getDB(),
                null,
                tabla);

        System.out.println("\t\tClaves Primarias:");
        while (rsPK.next()) {
            System.out.println("\t\t\t" + rsPK.getString("COLUMN_NAME"));
        }
    }

    private static void mostrarClavesAjenas(DatabaseMetaData dbMetaData, String tabla) throws SQLException {
        ResultSet rsFK = dbMetaData.getImportedKeys(
                MYSQLConnection.getDB(),
                null,
                tabla);

        System.out.println("\t\tClaves Ajenas:");
        while (rsFK.next()) {
            System.out.println("\t\t\t" + rsFK.getString("FKCOLUMN_NAME") +
                    " --> " + rsFK.getString("PKTABLE_NAME"));
        }
    }

    private static void mostrarClavesPrimariasExportadas(DatabaseMetaData dbMetaData, String tabla) throws SQLException {
        ResultSet rsFK = dbMetaData.getExportedKeys(
                MYSQLConnection.getDB(),
                null,
                tabla);

        System.out.println("\t\tClaves Exportadas:");
        while (rsFK.next()) {
            System.out.println("\t\t\t" + rsFK.getString("PKCOLUMN_NAME") +
                    " <-- " + rsFK.getString("FKTABLE_NAME"));
        }
    }
}