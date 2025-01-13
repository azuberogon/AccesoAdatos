package org.example.service;


import org.example.dao.ClienteDao;
import org.example.entity.ClienteEntity;

public class ClienteService extends GenericServiceImpl<ClienteEntity, Long>{
    public ClienteService() {
        super(new ClienteDao());
    }
}