package db.dao;

import db.connection.MySQLConnection;
import db.model.Cliente;
import db.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO implements GenericDAO<Pedido, Integer> {

    @Override
    public int insertar(Pedido pedido) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "INSERT INTO Pedido (ClienteID, Fecha) VALUES (?, ?);";
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, pedido.getCliente().getClienteID());
        stmt.setDate(2, pedido.getFecha() != null ?
                Date.valueOf(pedido.getFecha()): null);
        stmt.execute();

        // Obtener la clave generada (el ID autoincremental)
        int idGenerado = -1;
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
        }
        return idGenerado;
    }

    @Override
    public List<Pedido> obtenerTodos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Pedido";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rsPedidos = stmt.executeQuery();

        while (rsPedidos.next()) {
            pedidos.add(new Pedido(
                    rsPedidos.getInt("PedidoID"),
                    new Cliente(rsPedidos.getInt("ClienteID"), null, null, null, null),
                    // Si la fecha no es null la paso a LocalDate, sino guardo null
                    rsPedidos.getDate("Fecha") != null ?
                            rsPedidos.getDate("Fecha").toLocalDate() : null,
                    null));
        }
        return pedidos;
    }

    @Override
    public Pedido obtenerPorId(Integer id) throws SQLException {
        Pedido pedido = null;
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Pedido WHERE PedidoID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rsPedidos = stmt.executeQuery();

        if (rsPedidos.next()) {
            pedido = new Pedido(
                    rsPedidos.getInt("PedidoID"),
                    new Cliente(rsPedidos.getInt("ClienteID"), null, null, null, null),
                    // Si la fecha no es null la paso a LocalDate, sino guardo null
                    rsPedidos.getDate("Fecha") != null ?
                            rsPedidos.getDate("Fecha").toLocalDate() : null,
                    null);
        }
        return pedido;
    }

    @Override
    public void actualizar(Pedido pedido) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "UPDATE Pedido SET" +
                " Fecha = ?" +
                " WHERE PedidoID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setDate(1, pedido.getFecha() != null ?
                Date.valueOf(pedido.getFecha()) : null);
        stmt.setInt(2, pedido.getPedidoID());
        stmt.execute();
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "DELETE FROM Pedido WHERE PedidoID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
    }

    public List<Pedido> obtenerPedidosCliente(Integer clienteID) throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Pedido WHERE ClienteID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, clienteID);
        ResultSet rsPedidos = stmt.executeQuery();

        while (rsPedidos.next()) {
            pedidos.add(new Pedido(
                    rsPedidos.getInt("PedidoID"),
                    new Cliente(rsPedidos.getInt("ClienteID"), null, null, null, null),
                    // Si la fecha no es null la paso a LocalDate, sino guardo null
                    rsPedidos.getDate("Fecha") != null ?
                            rsPedidos.getDate("Fecha").toLocalDate() : null,
                    null));
        }
        return pedidos;
    }
}
