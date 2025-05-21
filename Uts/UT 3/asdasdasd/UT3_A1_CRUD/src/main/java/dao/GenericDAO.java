package dao;

import java.util.List;

public interface GenericDAO <T,ID>{
    void create(T entity) throws Exception;
    T findById(ID id);
    List<T> findAll();
    void update(T entity) throws Exception;
    void delete(T entity) throws Exception;
}
