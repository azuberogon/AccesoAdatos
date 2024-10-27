package org.example.sqlinterfaz.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:productos.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Conexión establecida con SQLite");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Productos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nombre TEXT NOT NULL, "
                + "precio REAL NOT NULL)";
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla creada o ya existente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
