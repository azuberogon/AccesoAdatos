package db.dao;

import db.connection.MySQLConnection;
import db.model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO implements GenericDAO<Libro, Integer> {

    @Override
    public void actualizar(Libro libro) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        //TODO START 4.- Cambia el método para evitar que se puedan realizar inyecciones de SQL
        Statement stmt = connection.createStatement();
        stmt.execute("UPDATE Libros SET " +
                "titulo = '" + libro.getTitulo() + "', " +
                "genero = '" + libro.getGenero() + "', " +
                "autor = '" + libro.getAutor() + "', " +
                "precio = " + libro.getPrecio() +
                " WHERE codigo = " + libro.getIsbn());
        stmt.close();
        //TODO END
    }

    public List<Libro> obtenerPorAutor(String autor) throws SQLException {
        List<Libro> libros = new ArrayList<>();
        Connection connection = MySQLConnection.getInstance().getConnection();

        //TODO START 5.- Escribe un método que haga una consulta a la base de datos
        // que devuelva una lista con los libros cuyo autor es similar
        // al de la cadena introducida como parámetro.
        // Ampliacion: Si la cadena es nula, buscaremos aquellos que tengan null el autor.
        // Debes usar PreparedStatement para las consultas


        //TODO END

        return libros;
    }


    @Override
    public List<Libro> obtenerTodos() throws SQLException {
        List<Libro> libros = new ArrayList<>();
        Connection connection = MySQLConnection.getInstance().getConnection();

        Statement stmt = connection.createStatement();
        ResultSet rsLibros = stmt.executeQuery("SELECT * FROM Libros");

        while (rsLibros.next()) {
            libros.add(new Libro(
                    rsLibros.getInt("isbn"),
                    rsLibros.getString("titulo"),
                    rsLibros.getString("genero"),
                    rsLibros.getString("autor"),
                    rsLibros.getDouble("precio")));
        }
        stmt.close();
        return libros;
    }

    @Override
    public Libro obtenerPorId(Integer isbn) throws SQLException {
        Libro Libro = null;
        Connection connection = MySQLConnection.getInstance().getConnection();

        Statement stmt = connection.createStatement();
        ResultSet rsLibros = stmt.executeQuery("SELECT * FROM Libros WHERE isbn = " + isbn);

        if (rsLibros.next()) {

            Libro = new Libro(
                    rsLibros.getInt("isbn"),
                    rsLibros.getString("titulo"),
                    rsLibros.getString("genero"),
                    rsLibros.getString("autor"),
                    rsLibros.getDouble("precio"));
        }
        stmt.close();
        return Libro;
    }


    @Override
    public void insertar(Libro Libro) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO Libros (isbn, titulo, genero, autor, precio) VALUES ("
                + Libro.getIsbn() + ","
                + "'" + Libro.getTitulo() + "' ,"
                + "'" + Libro.getGenero() + "',"
                + "'" + Libro.getAutor() + "',"
                + Libro.getPrecio() + ");");
        statement.close();
    }

    @Override
    public void eliminar(Integer isbn) throws SQLException {
        Connection connection = MySQLConnection.getInstance().getConnection();

        Statement stmt = connection.createStatement();
        stmt.execute("DELETE FROM Libros WHERE isbn = " + isbn);
        stmt.close();
    }
}
