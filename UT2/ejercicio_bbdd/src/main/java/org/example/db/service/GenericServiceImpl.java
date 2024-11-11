package org.example.db.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.db.dao.GenericDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.example.constansts.GenericConstants.ERROR_SQL_EXCEPTION;


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
        try {
            genericDAO.insertar(entity);
            logger.info("Inserción correcta de {}", entity.getClass().getName());
        } catch (SQLException e) {
            logger.error("Error al insertar {} en la BD: ", entity.getClass().getName(), e.fillInStackTrace());
            return ERROR_SQL_EXCEPTION;
        }
        return 0;
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
        T entity = null;
        try {
            entity = genericDAO.obtenerPorId(id);
        } catch (SQLException e) {
            logger.error("Error al obtener el registro con ID {} en la BD: ", id, e.fillInStackTrace());
        }
        return entity;
    }

    @Override
    public int actualizar(T entity) {
        try {
            genericDAO.actualizar(entity);
            logger.info("Actualización correcta de {}", entity.getClass().getName());
        } catch (SQLException e) {
            logger.error("Error al actualizar {} en la BD: ", entity.getClass().getName(), e.fillInStackTrace());
            return ERROR_SQL_EXCEPTION;
        }
        return 0;
    }

    @Override
    public int eliminar(K id) {
        try {
            genericDAO.eliminar(id);
            logger.info("Eliminación correcta del registro con ID {}", id);
        } catch (SQLException e) {
            logger.error("Error al eliminar el registro con ID {} en la BD: ", id, e.fillInStackTrace());
            return ERROR_SQL_EXCEPTION;
        }
        return 0;
    }

}