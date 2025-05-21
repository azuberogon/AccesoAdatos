package dao;

import java.util.List;

public interface GenericDAO<T, K> {

    // Método para insertar un nuevo registro (CREATE)
    void create(T entity) throws Exception;

    // Método para obtener todos los registros (READ)
    List<T> findAll() throws Exception;

    // Método para obtener un registro por ID (READ)
    T findById(K id) throws Exception;

    // Método para actualizar un registro existente (UPDATE)
    void update(T entity) throws Exception;

    // Método para eliminar un registro por ID (DELETE)
    void delete(T entity) throws Exception;
}

