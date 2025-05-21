package org.example.service;

import org.example.dao.PedidoDao;
import org.example.entity.PedidoEntity;

public class PedidoService extends GenericServiceImpl<PedidoEntity, Long>{
    public PedidoService() {
        super(new PedidoDao());
    }
}