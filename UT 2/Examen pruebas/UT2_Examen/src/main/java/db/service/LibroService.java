package db.service;

import constants.GenericConstants;
import db.connection.MySQLConnection;
import db.dao.LibroDAO;
import db.model.Libro;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.GenericUtils;
import static constants.GenericConstants.*;
import static constants.LibroConstants.*;
import static utils.GenericUtils.esValidoParaDecimal;
import static utils.GenericUtils.limitarString;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LibroService extends GenericServiceImpl<Libro, Integer> {

    // Crea una instancia del Logger
    private static final Logger logger = LogManager.getLogger(LibroService.class);

    public LibroService() {
        super(new LibroDAO());
    }

    @Override
    public int actualizar(Libro libro) {

        //TODO START 6.- Modifica el método para que las actualizaciones las haga mediante una transacción
        int comprobarLibro = comprobarLibro(libro);
        if (comprobarLibro != 0)
            return comprobarLibro;
        Connection con = MySQLConnection.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            return super.actualizar(libro);

        }catch (SQLException e){
            logger.error(e.fillInStackTrace());
        }finally {
            try{
                con.setAutoCommit(true);
                logger.info("Auto commit activado.");
            } catch (SQLException e) {
                logger.error("Error en la activación del auto commit: ", e.fillInStackTrace());
            }
        }
        return ERROR_SQL_EXCEPTION;
        //TODO END
    }


    //Metodo de servicio para el DAO obtenerPorAutor.
    // Busca el parámetro de entrada en el campo autor y devuelve una lista de libros
    public List<Libro> obtenerPorAutor(String autor) {

        List<Libro> libros = new ArrayList<>();

        //TODO START 7.- Escribe un método de servicio que valide y haga uso del método DAO que hace busquedas por autor.
        // Devolvera una lista de libros que coincidan con la búsqueda (o una lista vacia si no hay matchs).

        Connection con = MySQLConnection.getInstance().getConnection();
        LibroDAO libroDAO = new LibroDAO();
        try {
            con.setAutoCommit(false);
            libros.addAll(libroDAO.obtenerPorAutor(autor));
        }catch (SQLException sqlException){
            System.out.println("error en la base de datos");
        }

        //TODO END

        return libros;
    }

    //Metodo para validar los datos de entrada de los libros
    private int comprobarLibro(Libro libro) {
        // Comprobar Libro en conjunto
        if (libro == null) {
            logger.error("No se pueden insertar libros que no existen");
            return ERROR_PARAM_NULL;
        }

        // Comprobar el isbn
        if (libro.getIsbn() == null) {
            logger.error("No se admite un código ISBN nulo");
            return ERROR_CODIGO_NULO;
        }


        //TODO START 8.- Haz la validación de los datos para titulo.
        // Además de lo que exige la base de datos, tampoco admitiremos títulos en blanco.
        // Haz uso del metodo auxiliar GeneralUtils.limitarString que recorta cadenas de caracteres.
// Comprobar el titulo
        if (libro.getTitulo() == null) {
            logger.error("No se admite un  Titulo nulo");
            return ERROR_TITULO_NULO;
        }
        if (libro.getTitulo().isBlank() || libro.getTitulo().isEmpty()) {
            logger.error("No se admite un Titulo blanco o vacio");
            return ERROR_TITULO_NULO;
        }




        //TODO END



        // Comprobar el genero
        if (libro.getGenero() == null
                || libro.getGenero().isEmpty()
                || libro.getGenero().isBlank()) {
            logger.error("El género no puede estar vacío o ser nulo");
            return ERROR_GENERO_NULO;
        } else if (libro.getGenero().length() > TABLE_GENERO_MAX_LONG)
            libro.setGenero(limitarString(libro.getGenero(),
                    TABLE_GENERO_MAX_LONG));


        // Comprobar autor

        //TODO START 9.- Haz la validación para autor.
        // Haz uso del metodo auxiliar GenericUtils.limitarString que recorta cadenas de caracteres.
        if (libro.getTitulo().length() > TABLE_TITULO_MAX_LONG ){
            libro.setTitulo(limitarString(libro.getTitulo(),TABLE_TITULO_MAX_LONG));
        }
        if (libro.getGenero().length() > TABLE_GENERO_MAX_LONG ){
            libro.setGenero(limitarString(libro.getGenero(),TABLE_GENERO_MAX_LONG));
        }
        if (libro.getAutor().length() > TABLE_TITULO_MAX_LONG ){
            libro.setAutor(limitarString(libro.getAutor(),TABLE_TITULO_MAX_LONG));
        }
        if (!esValidoParaDecimal(libro.getPrecio(),TABLE_PRECIO_MAX_ENTEROS)){
            libro.setPrecio(-1);
        }


        //TODO END



        // Comprobar precio
        if (libro.getPrecio() == null) {
            logger.error("No se admite precio nulo o vacío");
            return ERROR_PRECIO_NULO;
        }
        else if (!esValidoParaDecimal(libro.getPrecio(), TABLE_PRECIO_MAX_ENTEROS)) {
            logger.error("No se admiten valores con más de " + TABLE_PRECIO_MAX_ENTEROS + " números enteros");
            return ERROR_PRECIO_DEMASIADO_GRANDE;
        }

        return RESULTADO_OK;
    }



    @Override
    public Libro obtenerPorId(Integer isbn) {

        if (isbn == null) {
            logger.error("No se puede buscar con valores nulos");
            return null;
        }
        return super.obtenerPorId(isbn);
    }

    @Override
    public int insertar(Libro libro) {
        int comprobarLibro = comprobarLibro(libro);
        if (comprobarLibro != 0) return comprobarLibro;

        return super.insertar(libro);
    }


    @Override
    public int eliminar(Integer isbn) {
        if (isbn == null) {
            logger.error("No se puede eliminar un código nulo");
            return GenericConstants.ERROR_PARAM_NULL;
        }
        return super.eliminar(isbn);
    }

}
