package db.dao;

import db.connection.MySQLConnection;
import db.model.Historia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriaDAO implements GenericDAO<Historia, Integer> {

    @Override
    public int insertar(Historia historia) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "INSERT INTO Historia (Titulo, Descripcion) VALUES (?, ?);";
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, historia.getTitulo());
        stmt.setString(2, historia.getDescripcion());
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
    public List<Historia> obtenerTodos() throws SQLException {
        List<Historia> historias = new ArrayList<>();
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Historia";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rsHistorias = stmt.executeQuery();

        while (rsHistorias.next()) {
            historias.add(new Historia(
                    rsHistorias.getInt("HistoriaID"),
                    rsHistorias.getString("Titulo"),
                    rsHistorias.getString("Descripcion")));
        }
        return historias;
    }

    @Override
    public Historia obtenerPorId(Integer id) throws SQLException {
        Historia historia = null;
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Historia WHERE HistoriaID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rsHistorias = stmt.executeQuery();

        if (rsHistorias.next()) {
            historia = new Historia(
                    rsHistorias.getInt("HistoriaID"),
                    rsHistorias.getString("Titulo"),
                    rsHistorias.getString("Descripcion"));
        }
        return historia;
    }

    @Override
    public void actualizar(Historia historia) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "UPDATE Historia SET" +
                " Titulo = ?," +
                " Descripcion = ?" +
                " WHERE HistoriaID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, historia.getTitulo());
        stmt.setString(2, historia.getDescripcion());
        stmt.setInt(3, historia.getHistoriaID());
        stmt.execute();
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "DELETE FROM Historia WHERE HistoriaID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
    }
}
