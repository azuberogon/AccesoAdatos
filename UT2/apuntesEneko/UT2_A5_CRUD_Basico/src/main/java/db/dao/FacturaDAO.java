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

        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO Facturas (codigo, cuenta, destinatario, fecha_hora, importe) VALUES ("
                + Factura.getCodigo() + ","
                + Factura.getCuenta() + ","
                + "'" + Factura.getDestinatario() + "',"
                + "'" + Factura.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")) + "',"
                + Factura.getImporte() + ");");
        statement.close();
    }

    @Override
    public List<Factura> obtenerTodos() throws SQLException {
        List<Factura> Facturas = new ArrayList<>();
        Connection connection = MySQLConnection.getInstance().getConnection();

        Statement stmt = connection.createStatement();
        ResultSet rsFacturas = stmt.executeQuery("SELECT * FROM Facturas");

        while (rsFacturas.next()) {
            Facturas.add(new Factura(
                    rsFacturas.getInt("codigo"),
                    rsFacturas.getString("destinatario"),
                    rsFacturas.getInt("cuenta"),
                    rsFacturas.getDouble("importe"),
                    rsFacturas.getDate("fecha_hora").toLocalDate().atTime(rsFacturas.getTime("fecha_hora").toLocalTime())));
        }
        stmt.close();
        return Facturas;
    }

    @Override
    public Factura obtenerPorId(Integer codigo) throws SQLException {
        Factura Factura = null;
        Connection connection = MySQLConnection.getInstance().getConnection();

        Statement stmt = connection.createStatement();
        ResultSet rsFacturas = stmt.executeQuery("SELECT * FROM Facturas WHERE codigo = " + codigo);

        if (rsFacturas.next()) {

            Factura = new Factura(
                    rsFacturas.getInt("codigo"),
                    rsFacturas.getString("destinatario"),
                    rsFacturas.getInt("cuenta"),
                    rsFacturas.getDouble("importe"),
                    rsFacturas.getDate("fecha_hora").toLocalDate().atTime(rsFacturas.getTime("fecha_hora").toLocalTime()));
        }
        stmt.close();
        return Factura;
    }

    @Override
    public void actualizar(Factura Factura) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        Statement stmt = connection.createStatement();
        stmt.execute("UPDATE Facturas SET " +
                "cuenta = " + Factura.getCuenta() + ", " +
                        "destinatario = '" + Factura.getDestinatario() + "', " +
                        "fecha_hora = '" + Factura.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")) + "', " +
                        "importe = " + Factura.getImporte() +
                        " WHERE codigo = " + Factura.getCodigo());
        stmt.close();
    }

    @Override
    public void eliminar(Integer codigo) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        Statement stmt = connection.createStatement();
        stmt.execute("DELETE FROM Facturas WHERE codigo = " + codigo);
        stmt.close();
    }
}
