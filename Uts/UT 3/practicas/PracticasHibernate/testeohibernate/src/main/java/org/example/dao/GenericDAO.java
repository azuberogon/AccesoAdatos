package org.example.dao;


import java.util.List;

public interface GenericDAO <T, K>{

    void create(T entity) throws Exception;
    T findById(K id);
    List<T> findAll();
    void update(T entity) throws Exception;
    void delete(T entity) throws Exception;






}
