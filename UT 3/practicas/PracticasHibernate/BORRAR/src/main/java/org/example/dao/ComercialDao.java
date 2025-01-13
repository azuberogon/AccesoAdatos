package org.example.dao;

import org.example.entity.ComercialEntity;

public class ComercialDao extends GenericDaoImpl<ComercialEntity,Long> {
    public ComercialDao() {
        super(ComercialEntity.class);
    }
}
