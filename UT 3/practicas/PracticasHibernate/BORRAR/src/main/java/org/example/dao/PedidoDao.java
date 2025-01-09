package org.example.dao;

import org.example.entity.PedidoEntity;

public class PedidoDao extends GenericDaoImpl<PedidoEntity,Long>{
    public PedidoDao() {
        super(PedidoEntity.class);
    }
}
