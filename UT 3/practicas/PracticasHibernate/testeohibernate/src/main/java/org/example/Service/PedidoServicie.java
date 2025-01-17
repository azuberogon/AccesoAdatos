package org.example.Service;

import org.example.dao.ComercialDAO;
import org.example.dao.PedidoDAO;
import org.example.entity.ComercialEntity;
import org.example.entity.PedidoEntity;

public class PedidoServicie extends GenericServiceImpl<PedidoEntity, Long>{
    public PedidoServicie() {
        super(new PedidoDAO());
    }
}
