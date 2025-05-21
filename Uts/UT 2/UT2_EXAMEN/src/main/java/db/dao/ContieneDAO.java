package db.dao;

import db.connection.MySQLConnection;
import db.model.Contiene;
import db.model.ContieneID;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContieneDAO implements GenericDAO<Contiene, ContieneID> {

    @Override
    public int insertar(Contiene contiene) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "INSERT INTO Contiene (PedidoID, VideojuegoID, Cantidad) VALUES (?, ?, ?);";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, contiene.getContieneID().getPedidoID());
        stmt.setInt(2, contiene.getContieneID().getVideojuegoID());
        stmt.setInt(3, contiene.getCantidad());
        stmt.execute();

        return 1;
    }

    @Override
    public List<Contiene> obtenerTodos() throws SQLException {
        List<Contiene> contienes = new ArrayList<>();
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Contiene";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rsContienes = stmt.executeQuery();

        while (rsContienes.next()) {
            contienes.add(new Contiene(
                    new ContieneID(rsContienes.getInt("PedidoID"),
                            rsContienes.getInt("VideojuegoID")),
                    rsContienes.getInt("Cantidad")));
        }
        return contienes;
    }

    @Override
    public Contiene obtenerPorId(ContieneID contieneID) throws SQLException {
        Contiene contiene = null;
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Contiene WHERE PedidoID = ? AND VideojuegoID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, contieneID.getPedidoID());
        stmt.setInt(2, contieneID.getVideojuegoID());
        ResultSet rsVideojuegos = stmt.executeQuery();

        if (rsVideojuegos.next()) {
            contiene = new Contiene(
                    new ContieneID(rsVideojuegos.getInt("PedidoID"),
                            rsVideojuegos.getInt("VideojuegoID")),
                    rsVideojuegos.getInt("Cantidad"));
        }
        return contiene;
    }

    @Override
    public void actualizar(Contiene contiene) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "UPDATE Contiene SET" +
                " Cantidad = ?" +
                " WHERE PedidoID = ? AND VideojuegoID = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, contiene.getCantidad());
        stmt.setInt(2, contiene.getContieneID().getPedidoID());
        stmt.setInt(3, contiene.getContieneID().getVideojuegoID());
        stmt.execute();
    }

    @Override
    public void eliminar(ContieneID contieneID) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "DELETE FROM Contiene WHERE PedidoID = ? AND VideojuegoID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, contieneID.getPedidoID());
        stmt.setInt(2, contieneID.getVideojuegoID());
        stmt.execute();
    }

    public List<Contiene> obtenerContienesPedido(Integer pedidoID) throws SQLException {
        List<Contiene> contienes = new ArrayList<>();
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Contiene WHERE PedidoID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, pedidoID);
        ResultSet rsContienes = stmt.executeQuery();

        while (rsContienes.next()) {
            contienes.add(new Contiene(
                    new ContieneID(rsContienes.getInt("PedidoID"),
                            rsContienes.getInt("VideojuegoID")),
                    rsContienes.getInt("Cantidad")));
        }
        return contienes;
    }
}
