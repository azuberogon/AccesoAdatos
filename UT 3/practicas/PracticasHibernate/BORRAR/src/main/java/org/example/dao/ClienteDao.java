package org.example.dao;

import org.example.entity.ClienteEntity;

public class ClienteDao extends GenericDaoImpl<ClienteEntity,Long>{
    public ClienteDao() {
        super(ClienteEntity.class);
    }
}
