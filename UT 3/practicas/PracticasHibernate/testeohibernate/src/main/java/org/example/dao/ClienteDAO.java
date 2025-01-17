package org.example.dao;

import org.example.entity.ClienteEntity;

public class ClienteDAO  extends GenericDAOImpl<ClienteEntity, Long> {
    public ClienteDAO() {
        super(ClienteEntity.class);
    }

}
