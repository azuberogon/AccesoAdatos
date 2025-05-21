package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dao.GenericDAO;

import java.util.List;

import static constants.GenericConstants.*;

public abstract class GenericServiceImpl<T,ID> implements GenericService<T, ID>{
    private GenericDAO<T, ID> dao;
    private final Logger logger = LogManager.getLogger(GenericServiceImpl.class);
    public GenericServiceImpl(GenericDAO<T, ID> dao) {
        this.dao = dao;
    }

    public GenericDAO<T, ID> getDao() {
        return dao;
    }

    @Override
    public int create(T entity) {
        if (entity == null) {
            logger.warn("No se pueden persistir objetos nulos");
            return ERROR_PARAM_NULL;
        }
        try {
            dao.create(entity);
            logger.trace("Objeto persistido correctamente");
        } catch (Exception e) {
            logger.error("Ha ocurrido un error inesperado al persistir el objeto en base de datos: ", e.fillInStackTrace());
            return ERROR_SQL_EXCEPTION;
        }
        return RESULTADO_OK;
    }

    @Override
    public T findById(ID id) {
        if (id == null) {
            logger.warn("No se puede buscar con un ID nulo");
            return null;
        }
        try {
            return dao.findById(id);
        } catch (Exception e) {
            logger.error("Ha ocurrido un error inesperado al buscar un objeto por su ID '{}'", id, e.fillInStackTrace());
            return null;
        }
    }

    @Override
    public List<T> findAll() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            logger.error("Ha ocurrido un error inesperado al obtener todos los elementos: ", e.fillInStackTrace());
            return null;
        }
    }

    @Override
    public int update(T entity) {
        if (entity == null) {
            logger.warn("No se puede actualizar una entidad nula");
            return ERROR_PARAM_NULL;
        }
        try {
            dao.update(entity);
            logger.trace("Objeto actualizado correctamente");
        } catch (Exception e) {
            logger.error("Ha ocurrido un error inesperado al actualizar el objeto en base de datos: ", e.fillInStackTrace());
            return ERROR_SQL_EXCEPTION;
        }
        return RESULTADO_OK;
    }

    @Override
    public int delete(T entity) {
        if (entity == null) {
            logger.warn("No se puede eliminar una entidad nula");
            return ERROR_PARAM_NULL;
        }
        try {
            dao.delete(entity);
            logger.trace("Objeto eliminado correctamente");
        } catch (Exception e) {
            logger.error("Ha ocurrido un error inesperado al eliminar el objeto de base de datos: ", e.fillInStackTrace());
            return ERROR_SQL_EXCEPTION;
        }
        return RESULTADO_OK;
    }
}
