package org.example.db.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.db.dao.GenericDao;
import org.example.db.model.Factura;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static org.example.constants.GenericConstants.ERROR_SQL_EXCEPTION;

public class GenericServiceImpl<T,K> implements GenericService <T,K> {

    private final GenericDao<T, K> genericDao;

    // Crea una instancia del Logger
    private static final Logger logger = LogManager.getLogger(GenericServiceImpl.class);

    // Constructor que recibe como parámetro cualquier DAO que implemente el genérico
    public GenericServiceImpl(GenericDao<T, K> genericDAO) {
        this.genericDao = genericDAO;
    }


    @Override
    public int insertar(T entity) {
        try{
            genericDao.insertar(entity);
            logger.info("Inserción correcta de {}", entity.getClass().getName());
        }catch (SQLException sqlException) {
            logger.info("Error al inserta en la base de datos Factura:"+ entity.getClass().getName()+"  error :"+sqlException.fillInStackTrace());
            return ERROR_SQL_EXCEPTION;
        }
        return 0;
    }

    @Override
    public List<T> obtenerTodos() {
        List<T> resultado = new ArrayList<>();
        try{

            resultado = genericDao.obtenerTodos();
        }catch (SQLException SQLE){
            logger.error("Error al obtner todos los registros de la base de datos" +SQLE.fillInStackTrace());
        }
        return resultado;
    }

    @Override
    public T obtenerPorId(K id) {
        T resultado = null;
        try {
            resultado = genericDao.obtenerPorId(id);
        }catch (SQLException sqlException){
            logger.error("Error al obtener el registro '{}' en la base de adtos ", id, sqlException.fillInStackTrace());
        }
        return resultado;
    }

    @Override
    public int actualizar(T entity) {
        try {
            genericDao.actualizar(entity);
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

        try{
            genericDao.eliminar(id);
            logger.info("Eliminación correcta de id {}", id);
        }catch (SQLException SQLE){


            logger.error("Error al eliminar id {} en la BD: ",
                    id, SQLE.fillInStackTrace());
            return ERROR_SQL_EXCEPTION;
        }
        return 0;
    }
    public GenericDao<T, K> getGenericDAO() {
        return genericDao;
    }
}
