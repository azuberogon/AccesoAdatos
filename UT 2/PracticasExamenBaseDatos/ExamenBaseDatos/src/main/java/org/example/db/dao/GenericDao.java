package org.example.db.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T, K> {

    void insertar (T entity) throws SQLException;
    List<T> obtenerTodos() throws SQLException;

    T obtenerPorId(K id) throws SQLException;
    void actualizar(T entity) throws SQLException;
    void eliminar(K id) throws SQLException;





}
