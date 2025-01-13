package db.dao;

import db.connection.MySQLConnection;
import db.model.Historia;
import db.model.Videojuego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideojuegoDAO implements GenericDAO<Videojuego, Integer> {

    @Override
    public int insertar(Videojuego videojuego) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "INSERT INTO VIDEOJUEGO (Titulo, Plataforma, Anio_lanzamiento, Precio, Fecha_actualizacion, Disponible, HistoriaID)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, videojuego.getTitulo());
        stmt.setString(2, videojuego.getPlataforma());
        stmt.setInt(3, videojuego.getAnioLanzamiento());
        stmt.setDouble(4, videojuego.getPrecio());
        stmt.setDate(5, videojuego.getFechaActualizacion() != null ?
                Date.valueOf(videojuego.getFechaActualizacion()): null);
        stmt.setBoolean(6, videojuego.isDisponible());
        stmt.setInt(7, videojuego.getHistoria().getHistoriaID());
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
    public List<Videojuego> obtenerTodos() throws SQLException {
        List<Videojuego> videojuegos = new ArrayList<>();
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Videojuego";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rsVideojuegos = stmt.executeQuery();

        while (rsVideojuegos.next()) {
            videojuegos.add(new Videojuego(
                    rsVideojuegos.getInt("VideojuegoID"),
                    rsVideojuegos.getString("Titulo"),
                    rsVideojuegos.getString("Plataforma"),
                    rsVideojuegos.getInt("Anio_lanzamiento"),
                    rsVideojuegos.getDouble("Precio"),
                    // Si la fecha no es null la paso a LocalDate, sino guardo null
                    rsVideojuegos.getDate("Fecha_actualizacion") != null ?
                            rsVideojuegos.getDate("Fecha_actualizacion").toLocalDate() : null,
                    rsVideojuegos.getBoolean("Disponible"),
                    new Historia(rsVideojuegos.getInt("HistoriaID"), null, null)));
        }
        return videojuegos;
    }

    @Override
    public Videojuego obtenerPorId(Integer id) throws SQLException {
        Videojuego videojuego = null;
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Videojuego WHERE VideojuegoID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rsVideojuegos = stmt.executeQuery();

        if (rsVideojuegos.next()) {
            videojuego = new Videojuego(
                    rsVideojuegos.getInt("VideojuegoID"),
                    rsVideojuegos.getString("Titulo"),
                    rsVideojuegos.getString("Plataforma"),
                    rsVideojuegos.getInt("Anio_lanzamiento"),
                    rsVideojuegos.getDouble("Precio"),
                    // Si la fecha no es null la paso a LocalDate, sino guardo null
                    rsVideojuegos.getDate("Fecha_actualizacion") != null ?
                            rsVideojuegos.getDate("Fecha_actualizacion").toLocalDate() : null,
                    rsVideojuegos.getBoolean("Disponible"),
                    new Historia(rsVideojuegos.getInt("HistoriaID"), null, null));
        }
        return videojuego;
    }

    @Override
    public void actualizar(Videojuego videojuego) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "UPDATE Videojuego SET" +
                " Titulo = ?," +
                " Plataforma = ?," +
                " Anio_lanzamiento = ?," +
                " Precio = ?," +
                " Fecha_actualizacion = ?," +
                " Disponible = ?," +
                " HistoriaID = ?" +
                " WHERE VideojuegoID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, videojuego.getTitulo());
        stmt.setString(2, videojuego.getPlataforma());
        stmt.setInt(3, videojuego.getAnioLanzamiento());
        stmt.setDouble(4, videojuego.getPrecio());
        stmt.setDate(5, videojuego.getFechaActualizacion() != null ?
                Date.valueOf(videojuego.getFechaActualizacion()) : null);
        stmt.setBoolean(6, videojuego.isDisponible());
        stmt.setInt(7, videojuego.getHistoria().getHistoriaID());
        stmt.setInt(8, videojuego.getVideojuegoID());
        stmt.execute();
    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        String sql = "DELETE FROM Videojuego WHERE VideojuegoID = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.execute();
    }
}
