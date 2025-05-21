package db.dao;

import db.connection.MySQLConnection;
import db.model.Factura;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO implements GenericDAO<Factura, Integer> {
    @Override
    public void insertar(Factura Factura) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

//        Statement statement = connection.createStatement();
//        statement.execute("INSERT INTO Facturas (codigo, cuenta, destinatario, fecha_hora, importe) VALUES ("
//                + Factura.getCodigo() + ","
//                + Factura.getCuenta() + ","
//                + "'" + Factura.getDestinatario() + "',"
//                + "'" + Factura.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "',"
//                + Factura.getImporte() + ");");
//        statement.close();

        String consulta = "INSERT INTO Facturas (codigo, cuenta, destinatario, fecha_hora, importe)" +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement  pStatement = connection.prepareStatement(consulta);
        pStatement.setInt(1, Factura.getCodigo());
        pStatement.setInt(2, Factura.getCuenta());
        pStatement.setString(3, Factura.getDestinatario());
        pStatement.setString(4, Factura.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        pStatement.setDouble(5, Factura.getImporte());
        pStatement.execute();
        pStatement.close();

    }

    @Override
    public List<Factura> obtenerTodos() throws SQLException {
        List<Factura> Facturas = new ArrayList<>();
        Connection connection = MySQLConnection.getInstance().getConnection();

//        Statement stmt = connection.createStatement();
//        ResultSet rsFacturas = stmt.executeQuery("SELECT * FROM Facturas");
        String query = "SELECT * FROM Facturas";
        PreparedStatement pStatement = connection.prepareStatement(query);
        ResultSet rsFacturas = pStatement.executeQuery();

        while (rsFacturas.next()) {
            Facturas.add(new Factura(
                    rsFacturas.getInt("codigo"),
                    rsFacturas.getString("destinatario"),
                    rsFacturas.getInt("cuenta"),
                    rsFacturas.getDouble("importe"),
                    rsFacturas.getTimestamp("fecha_hora").toLocalDateTime()));
        }
//        stmt.close();
        pStatement.close();
        return Facturas;
    }

    @Override
    public Factura obtenerPorId(Integer codigo) throws SQLException {
        Factura Factura = null;
        Connection connection = MySQLConnection.getInstance().getConnection();

//        Statement stmt = connection.createStatement();
//        ResultSet rsFacturas = stmt.executeQuery("SELECT * FROM Facturas WHERE codigo = " + codigo);
        String query = "SELECT * FROM Facturas WHERE codigo = ?";
        PreparedStatement pStatement = connection.prepareStatement(query);
        pStatement.setInt(1, codigo);
        ResultSet rsFacturas = pStatement.executeQuery();

        if (rsFacturas.next()) {

            Factura = new Factura(
                    rsFacturas.getInt("codigo"),
                    rsFacturas.getString("destinatario"),
                    rsFacturas.getInt("cuenta"),
                    rsFacturas.getDouble("importe"),
                    rsFacturas.getTimestamp("fecha_hora").toLocalDateTime());
        }
//        stmt.close();
        pStatement.close();
        return Factura;
    }

    public List<Factura> obtenerPorDestinatario(String destinatario) throws SQLException {
        List<Factura> Facturas = new ArrayList<>();
        Connection connection = MySQLConnection.getInstance().getConnection();

//        Statement stmt = connection.createStatement();
//        ResultSet rsFacturas = stmt.executeQuery("SELECT * FROM Facturas WHERE destinatario = '" + destinatario + "'");

        String query = "SELECT * FROM Facturas WHERE destinatario LIKE ?";
        String busqueda = "%" + destinatario + "%";
        PreparedStatement pStatement = connection.prepareStatement(query);
        pStatement.setString(1, busqueda);
        ResultSet rsFacturas = pStatement.executeQuery();

        while (rsFacturas.next()) {

            Facturas.add(new Factura(
                    rsFacturas.getInt("codigo"),
                    rsFacturas.getString("destinatario"),
                    rsFacturas.getInt("cuenta"),
                    rsFacturas.getDouble("importe"),
                    rsFacturas.getTimestamp("fecha_hora").toLocalDateTime()));
        }
//        stmt.close();
        pStatement.close();
        return Facturas;
    }

    @Override
    public void actualizar(Factura Factura) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

//        Statement stmt = connection.createStatement();
//        stmt.execute("UPDATE Facturas SET " +
//                "cuenta = " + Factura.getCuenta() + ", " +
//                        "destinatario = '" + Factura.getDestinatario() + "', " +
//                        "fecha_hora = '" + Factura.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', " +
//                        "importe = " + Factura.getImporte() +
//                        " WHERE codigo = " + Factura.getCodigo());
//        stmt.close();

        String query = "UPDATE Facturas SET " +
                "cuenta = ?, " +
                "destinatario = ?, " +
                "fecha_hora = ?, " +
                "importe = ? " +
                "WHERE codigo = ?";
        PreparedStatement pStatement = connection.prepareStatement(query);
        pStatement.setInt(1, Factura.getCuenta());
        pStatement.setString(2, Factura.getDestinatario());
        pStatement.setString(3, Factura.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        pStatement.setDouble(4, Factura.getImporte());
        pStatement.setInt(5, Factura.getCodigo());
        pStatement.execute();
        pStatement.close();
    }

    @Override
    public void eliminar(Integer codigo) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

//        Statement stmt = connection.createStatement();
//        stmt.execute("DELETE FROM Facturas WHERE codigo = " + codigo);
//        stmt.close();
        String query = "DELETE FROM Facturas WHERE codigo = ?";
        PreparedStatement pStatement = connection.prepareStatement(query);
        pStatement.setInt(1, codigo);
        pStatement.execute();
        pStatement.close();
    }
}
