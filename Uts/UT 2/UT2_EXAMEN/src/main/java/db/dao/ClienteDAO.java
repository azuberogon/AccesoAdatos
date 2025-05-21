package db.dao;

import com.sun.java.accessibility.util.AccessibilityListenerList;
import db.connection.MySQLConnection;
import db.model.Cliente;
import db.model.Historia;
import db.model.Pedido;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements GenericDAO<Cliente, Integer> {

    @Override
    public int insertar(Cliente cliente) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();


        java.sql.Date date = Date.valueOf(cliente.getFechaNacimiento());
        String sql = "INSERT INTO Cliente (Nombre, Apellidos, F_nacimiento) VALUES (?, ?, ?);";
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, cliente.getNombre());
        stmt.setString(2, cliente.getApellidos());
        stmt.setDate(3, date);
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
    public List<Cliente> obtenerTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        // TODO START
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Cliente";
        PreparedStatement stmt = connection.prepareStatement(sql);
        List<Integer> pedidos = null;
        ResultSet rsClientes = stmt.executeQuery();
        while (rsClientes.next()) {
            clientes.add(new Cliente(rsClientes.getInt("ClienteID"),
                    rsClientes.getString("Nombre"),
                    rsClientes.getString("Apellidos"),
                    rsClientes.getDate("F_nacimiento").toLocalDate(),
                    pedidos));
        }
        // TODO END
        return clientes;
    }

    @Override
    public Cliente obtenerPorId(Integer id) throws SQLException {
        Cliente cliente = null;
        // TODO START
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Cliente WHERE ClienteID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        List<Integer> pedidos = null;
        ResultSet rsClientes = stmt.executeQuery();

        if (rsClientes.next()) {
            cliente = new Cliente(rsClientes.getInt("ClienteID"),
                    rsClientes.getString("Nombre"),
                    rsClientes.getString("Apellidos"),
                    rsClientes.getDate("F_nacimiento").toLocalDate(),
                    pedidos);
        }
        // TODO END
        return cliente;
    }

    @Override
    public void actualizar(Cliente cliente) throws SQLException {
        // TODO START
        Connection connection = MySQLConnection.getInstance().getConnection();

        java.sql.Date date = Date.valueOf(cliente.getFechaNacimiento());

        String sql = "UPDATE Cliente SET" +
                " Nombre = ?," +
                " Apellidos = ?," +
                " F_nacimiento = ?" +
                " WHERE ClienteID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, cliente.getNombre());
        stmt.setString(2, cliente.getApellidos());
        stmt.setDate(3, date);
        stmt.setInt(4, cliente.getClienteID());
        stmt.executeUpdate();
        // TODO END
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        // TODO START
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "DELETE FROM Cliente WHERE ClienteID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
       if (stmt.executeUpdate()>0){
           System.out.println("se metio en la base de datos");
       }
        // TODO END
    }
}
