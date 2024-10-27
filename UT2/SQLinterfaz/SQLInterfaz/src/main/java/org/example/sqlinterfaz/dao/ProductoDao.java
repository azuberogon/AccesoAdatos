package org.example.sqlinterfaz.dao;

import org.example.sqlinterfaz.DataBase.Database;
import org.example.sqlinterfaz.dto.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.sqlinterfaz.DataBase.Database.connect;

public class ProductoDao {

    // Añadir producto con la nueva columna 'cantidad'
    public static void addProducto(String nombre, double precio, int cantidad) {
        String sql = "INSERT INTO Productos(nombre, precio, cantidad) VALUES(?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, precio);
            pstmt.setInt(3, cantidad);  // Insertar la cantidad
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    // Eliminar producto
    public static void deleteProducto(int id) {
        String sql = "DELETE FROM Productos WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Obtener todos los productos
    public static List<Producto> getAllProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM Productos";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad")  // Obtener la cantidad de la base de datos
                );
                productos.add(producto);
            }

            // Mensaje de depuración
            System.out.println("Productos cargados desde la base de datos: " + productos.size());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return productos;
    }
}
