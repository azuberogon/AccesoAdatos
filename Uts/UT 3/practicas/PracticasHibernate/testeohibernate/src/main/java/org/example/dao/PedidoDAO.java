package org.example.dao;


import org.example.entity.PedidoEntity;

public class PedidoDAO  extends GenericDAOImpl<PedidoEntity, Long> {
    public PedidoDAO() {
        super(PedidoEntity.class);
    }

}
