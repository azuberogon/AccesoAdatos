package db.service;

import db.dao.GenericDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constants.GenericConstants.ERROR_PARAM_NULL;
import static constants.GenericConstants.ERROR_SQL_EXCEPTION;

public class GenericServiceImpl<T, K> implements GenericService<T, K> {

    // DAO genérico a utilizar para lanzar las distintas operaciones
    private final GenericDAO<T, K> genericDAO;

    // Crea una instancia del Logger
    private static final Logger logger = LogManager.getLogger(GenericServiceImpl.class);

    // Constructor que recibe como parámetro cualquier DAO que implemente el genérico
    public GenericServiceImpl(GenericDAO<T, K> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public int insertar(T entity) {
        int id;
        try {
            id = genericDAO.insertar(entity);

            logger.info("Inserción correcta de {}", entity.getClass().getName());
        } catch (SQLException e) {
            logger.error("Error al insertar {} en la BD: ",
                    entity.getClass().getName(), e.fillInStackTrace());
            return ERROR_SQL_EXCEPTION;
        }
        return id;
    }

    @Override
    public List<T> obtenerTodos() {
        List<T> resultado = new ArrayList<>();
        try {
            resultado = genericDAO.obtenerTodos();
        } catch (SQLException e) {
            logger.error("Error al obtener todos los registros en la BD: ", e.fillInStackTrace());
        }
        return resultado;
    }

    @Override
    public T obtenerPorId(K id) {
        if (id == null) {
            logger.warn("No se puede buscar con valores nulos");
            return null;
        }

        T resultado = null;
        try {
            resultado = genericDAO.obtenerPorId(id);
        } catch (SQLException e) {
            logger.error("Error al obtener el registro '{}' en la BD: ", id, e.fillInStackTrace());
        }
        return resultado;
    }

    @Override
    public int actualizar(T entity) {
        try {
            genericDAO.actualizar(entity);
            logger.info("Actualización correcta de {}", entity.getClass().getName());
        }
        catch (SQLException e) {
            logger.error("Error al actualizar {} en la BD: ",
                    entity.getClass().getName(), e.fillInStackTrace());
            return ERROR_SQL_EXCEPTION;
        }
        return 0;
    }

    @Override
    public int eliminar(K id) {
        if (id == null) {
            logger.warn("No se puede eliminar un id nulo");
            return ERROR_PARAM_NULL;
        }

        try {
            genericDAO.eliminar(id);
            logger.info("Eliminación correcta de id {}", id);
        } catch (SQLException e) {
            logger.error("Error al eliminar id {} en la BD: ",
                    id, e.fillInStackTrace());
            return ERROR_SQL_EXCEPTION;
        }
        return 0;
    }

    public GenericDAO<T, K> getGenericDAO() {
        return genericDAO;
    }
}
