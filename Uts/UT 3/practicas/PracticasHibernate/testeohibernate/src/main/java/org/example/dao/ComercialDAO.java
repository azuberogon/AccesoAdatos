package org.example.dao;


import org.example.entity.ComercialEntity;

public class ComercialDAO extends GenericDAOImpl<ComercialEntity, Long> {
    public ComercialDAO() {
        super(ComercialEntity.class);
    }
}
