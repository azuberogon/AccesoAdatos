package org.example.service;

import java.util.List;

public interface GenerciService<T,ID> {
    int create(T entity);
    T findById(ID id);
    List<T> findAll();
    int delete(T entity);
    int update(T entity);
}
