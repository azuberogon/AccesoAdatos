package org.example.service;

import org.example.dao.ComercialDao;
import org.example.entity.ComercialEntity;

public class ComercialService extends GenericServiceImpl<ComercialEntity, Long>{
    public ComercialService() {
        super(new ComercialDao());
    }
}