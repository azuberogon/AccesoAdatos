package org.example.dao;

import org.example.conection.MYSQLConnection;
import org.example.model.Factura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO implements GenericDAO<Factura, Integer> {
    @Override
    public void insertar(Factura entity) throws SQLException {
        Connection connection = MYSQLConnection.getInstancia().getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Facturas (codigo, cuenta, destinatario, fecha_hora, importe) VALUES (?, ?, ?, ?, ?)");
        pstmt.setInt(1,entity.getCodigo());
        pstmt.setInt(2,entity.getCuenta());
        pstmt.setString(3,entity.getDestinatario());
        pstmt.setDate(4, new Date(System.currentTimeMillis()));
        pstmt.setDouble(5,entity.getPrecio());
        pstmt.executeUpdate(); // para actualizar a la base de datos
    }

    @Override
    public List<Factura> obtenerTodos() throws SQLException {
        List<Factura> facturas = new ArrayList<>();
        Connection connection = MYSQLConnection.getInstancia().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rsFacturas  = stmt.executeQuery("SELECT * from Facturas");
        while (rsFacturas.next()){
           facturas.add(new Factura(// la factura se ahace como el constructor
                   rsFacturas.getInt("codigo"),
                   rsFacturas.getString("destinatario"),
                   rsFacturas.getInt("cuenta"),
                   rsFacturas.getDouble("importe"),
                   rsFacturas.getDate("fecha_hora").toLocalDate().atTime(rsFacturas.getTime("fecha_hora").toLocalTime())
           ));

        }

        return facturas;
    }
    @Override
    public Factura obtenerPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM Facturas WHERE codigo = ?"; // Query de búsqueda en SQL
        Factura facturaSeleccionada = null;

        // Usa try-with-resources para cerrar automáticamente PreparedStatement y ResultSet
        try (Connection connection = MYSQLConnection.getInstancia().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id); // Se establece el valor del parámetro

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    facturaSeleccionada = new Factura(
                            rs.getInt("codigo"),
                            rs.getString("destinatario"),
                            rs.getInt("cuenta"),
                            rs.getDouble("importe"),
                            rs.getDate("fecha_hora").toLocalDate().atTime(rs.getTime("fecha_hora").toLocalTime())
                    );
                }
            }
        }

        return facturaSeleccionada; // Devuelve la factura seleccionada, si se encontró; de lo contrario, null
    }


    @Override
    public void actualizar(Factura entity) throws SQLException {
        
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        String sql = "DELETE FROM facturas WHERE ID = ?";

        try(Connection conn = MYSQLConnection.getInstancia().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            int filasAfectadas =  pstmt.executeUpdate(); // se observa si alguna fila se modifica;
            if (filasAfectadas > 0){
                System.out.println("Factura con el ID: "+id+" fue eliminada correctamente");
            }else {
                System.out.println("No se encontró ninguna factura con el ID: " + id + ".");
            }
        }
    }
}
