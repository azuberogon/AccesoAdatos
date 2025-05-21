package org.example.Service;

import java.util.List;

public interface GenericService<T,ID>{
    int create(T entity);

    T findById(ID id);
    List<T> findAll();
    int update(T entity);
    int delete(T entity);

}
