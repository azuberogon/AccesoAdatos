package db.service;

import java.util.List;

public interface GenericService<T, K> {

    // Metodo para insertar un nuevo registro (CREATE)
    int insertar(T entity);

    // Metodo para obtener todos los registros (READ)
    List<T> obtenerTodos();

    // Metodo para obtener un registro por ID (READ)
    T obtenerPorId(K id);

    // Metodo para actualizar un registro existente (UPDATE)
    int actualizar(T entity);

    // Metodo para eliminar un registro por ID (DELETE)
    int eliminar(K id);
}