package org.example.db.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO <T, K>{

    void insertar(T entity) throws SQLException; // insertar recive un objeto
    List<T> obtenerTodos() throws SQLException;
}
