package org.example.db.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface GenericDAO<T,K> {

    int insertar(T entity) throws SQLException;
    List<T> obtenerTodos() throws SQLException;
    T obtenerPorId(K id) throws SQLException;
    int actualizar(T entity) throws SQLException;
    int eliminar(K id) throws SQLException;


}
