package org.example;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.conection.MYSQLConnection;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.out.println("-----------------------------------------");
        System.out.println("------- UT2_A3 - Metadatos en BDR -------");
        System.out.println("-----------------------------------------");

        // Obtenemos la conexión
        Connection connection = MYSQLConnection.getInstancia().getConnection();

        try {
            // Obtener metadatos de la BDR
            DatabaseMetaData dbMetaData = connection.getMetaData();
            mostrarMetadatos(dbMetaData);
        } catch (SQLException e) {
            logger.error("Ha ocurrido un error al obtener los metadatos: ", e.fillInStackTrace());
        }
    }

    // Función encargada de mostrar metadatos
    private static void mostrarMetadatos(DatabaseMetaData dbMetaData) throws SQLException {
        // Nombre y versión del SGBD
        String dbProductName = dbMetaData.getDatabaseProductName();
        String dbProductVersion = dbMetaData.getDatabaseProductVersion();
        System.out.println("\n\n-----------------------------------------");
        System.out.println("SGBD: " + dbProductName + "\t\tVersión: " + dbProductVersion);
        System.out.println("-----------------------------------------");

        // Usuario actual
        System.out.println("Usuario actual: " + dbMetaData.getUserName());
        System.out.println("-----------------------------------------\n");

        // Mostramos los metadatos de todas las tablas
        mostrarTablas(dbMetaData);
    }

    // Muestra las tablas de la BDR
    private static void mostrarTablas(DatabaseMetaData dbMetaData) throws SQLException {
        ResultSet rsTablas = dbMetaData.getTables(
                MYSQLConnection.getDB(), null, null, new String[] {"TABLE"});

        while (rsTablas.next()) {
            String tabla = rsTablas.getString("TABLE_NAME");
            System.out.println("TABLA: " + tabla);
            mostrarColumnas(dbMetaData, tabla);
            mostrarClavesPrimarias(dbMetaData, tabla);
            mostrarClavesAjenas(dbMetaData, tabla);
            System.out.println("\n\n");
        }
    }

    // Muestra las columnas de una tabla
    private static void mostrarColumnas(DatabaseMetaData dbMetaData, String tabla) throws SQLException {
        ResultSet rsColumnas = dbMetaData.getColumns(
                MYSQLConnection.getDB(), null, tabla, null);

        while (rsColumnas.next()) {
            System.out.println("\t" + rsColumnas.getString("COLUMN_NAME") + ": " + rsColumnas.getString("TYPE_NAME"));
        }
    }

    // Muestra las claves primarias de una tabla
    private static void mostrarClavesPrimarias(DatabaseMetaData dbMetaData, String tabla) throws SQLException {
        ResultSet rsPK = dbMetaData.getPrimaryKeys(
                MYSQLConnection.getDB(), null, tabla);
        System.out.println("\t\tClaves Primarias:");

        while (rsPK.next()) {
            System.out.println("\t\t\t" + rsPK.getString("COLUMN_NAME"));
        }
    }

    // Muestra las claves ajenas de una tabla
    private static void mostrarClavesAjenas(DatabaseMetaData dbMetaData, String tabla) throws SQLException {
        ResultSet rsFK = dbMetaData.getImportedKeys(
                MYSQLConnection.getDB(), null, tabla);
        System.out.println("\t\tClaves Ajenas:");

        while (rsFK.next()) {
            System.out.println("\t\t\t" + rsFK.getString("FKCOLUMN_NAME") + " --> " + rsFK.getString("PKTABLE_NAME"));
        }
    }
}
