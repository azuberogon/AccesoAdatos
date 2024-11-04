package org.example.dao;

import org.example.conection.MYSQLConnection;
import org.example.model.Factura;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO implements GenericDAO<Factura, Integer> {
   /* @Override
    public void insertar(Factura entity) throws SQLException {
        Connection connection = MYSQLConnection.getInstancia().getConnection();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Facturas (codigo, cuenta, destinatario, fecha_hora, importe) VALUES (?, ?, ?, ?, ?)");
        pstmt.setInt(1,entity.getCodigo());
        pstmt.setInt(2,entity.getCuenta());
        pstmt.setString(3,entity.getDestinatario());
        pstmt.setDate(4, new Date(System.currentTimeMillis()));
        pstmt.setDouble(5,entity.getImporte());
        pstmt.executeUpdate(); // para actualizar a la base de datos
        int filasAfectadas = pstmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Factura insertada correctamente.");
        } else {
            System.out.println("No se pudo insertar la factura.");
        }
    }*/
   @Override
   public void insertar(Factura entity) throws SQLException {
       String sql = "INSERT INTO Facturas (codigo, cuenta, destinatario, fecha_hora, importe) VALUES (?, ?, ?, ?, ?)";
       Connection connection = MYSQLConnection.getInstancia().getConnection();

       try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
           pstmt.setInt(1, entity.getCodigo());
           pstmt.setInt(2, entity.getCuenta());
           pstmt.setString(3, entity.getDestinatario());
           pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now())); // Si fecha_hora es ahora
           pstmt.setDouble(5, entity.getImporte());

           int filasAfectadas = pstmt.executeUpdate();
           if (filasAfectadas > 0) {
               System.out.println("Factura insertada correctamente.");
           } else {
               System.out.println("No se pudo insertar la factura.");
           }
       }
       // Nota: no se cierra la conexión aquí
   }


    @Override
    public List<Factura> obtenerTodos() throws SQLException {
        List<Factura> facturas = new ArrayList<>();
        Connection connection = MYSQLConnection.getInstancia().getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rsFacturas  = stmt.executeQuery("SELECT * from Facturas");
        while (rsFacturas.next()){
           facturas.add(new Factura(// la factura se hace como el constructor
                   rsFacturas.getInt("codigo"),
                   rsFacturas.getString("destinatario"),
                   rsFacturas.getInt("cuenta"),
                   rsFacturas.getDouble("importe"),
                   rsFacturas.getDate("fecha_hora").toLocalDate().atTime(rsFacturas.getTime("fecha_hora").toLocalTime())
           ));

        }

        return facturas;/*
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM Facturas";

        try (Connection connection = MYSQLConnection.getInstancia().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rsFacturas = stmt.executeQuery(sql)) {

            while (rsFacturas.next()) {
                // Manejo de posibles valores nulos en cada campo
                int codigo = rsFacturas.getInt("codigo");
                String destinatario = rsFacturas.getString("destinatario");
                if (rsFacturas.wasNull()) {
                    destinatario = "No especificado"; // Valor predeterminado si es null
                }
                int cuenta = rsFacturas.getInt("cuenta");
                double importe = rsFacturas.getDouble("importe");

                LocalDateTime fechaHora = null;
                if (rsFacturas.getTimestamp("fecha_hora") != null) {
                    fechaHora = rsFacturas.getTimestamp("fecha_hora").toLocalDateTime();
                }

                // Crear objeto Factura y añadirlo a la lista
                Factura factura = new Factura(codigo, destinatario, cuenta, importe, fechaHora);
                facturas.add(factura);
            }
        }

        return facturas;*/







    }
    @Override
    public Factura obtenerPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM Facturas WHERE codigo = ?"; // Consulta SQL para buscar por código
        Factura facturaSeleccionada = null;

        try (Connection connection = MYSQLConnection.getInstancia().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Configura el parámetro de la consulta con el id que buscas
            pstmt.setInt(1, id);

            // Ejecuta la consulta y obtiene el ResultSet
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) { // Si se encuentra un resultado, crea la factura
                    facturaSeleccionada = new Factura(
                            rs.getInt("codigo"),
                            rs.getString("destinatario"),
                            rs.getInt("cuenta"),
                            rs.getDouble("importe"),
                            rs.getTimestamp("fecha_hora").toLocalDateTime() // Convierte a LocalDateTime
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la factura con el código: " + id + " - Error: " + e);
            e.printStackTrace();
        }

        // Verifica si se encontró la factura; si no, muestra un mensaje de error o advertencia
        if (facturaSeleccionada == null) {
            System.out.println("No se encontró ninguna factura con el código: " + id);
        }

        return facturaSeleccionada; // Devuelve la factura si se encontró; de lo contrario, null
    }



    @Override
    public void actualizar(Factura entity) throws SQLException {
        String sql = "UPDATE facturas SET destinatario = ?, cuenta = ?, importe = ?, fecha_hora = ? WHERE codigo = ?";

        try (Connection con = MYSQLConnection.getInstancia().getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Asignar los valores a cada parámetro
            pstmt.setString(1, entity.getDestinatario()); // destinatario
            pstmt.setInt(2, entity.getCuenta());          // cuenta
            pstmt.setDouble(3, entity.getImporte());      // importe
            pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now())); // Establece la fecha y hora actual

            pstmt.setInt(5, entity.getCodigo());          // codigo (para la condición WHERE)

            // Ejecutar el UPDATE y verificar si se afectó alguna fila
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("La factura con código " + entity.getCodigo() + " fue actualizada correctamente.");
            } else {
                System.out.println("No se encontró ninguna factura con el código " + entity.getCodigo() + ".");
            }
        } catch (SQLException SQLE) {
            System.out.println("Error en la conexión a la base de datos: " + SQLE);

        }
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
