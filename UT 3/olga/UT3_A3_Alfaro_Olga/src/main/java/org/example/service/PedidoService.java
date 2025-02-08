package org.example.service;

import org.example.dao.PedidoDAO;
import org.example.model.Pedido;

public class PedidoService extends GenericServiceImpl<Pedido, Integer> {
    public PedidoService() {
        super(new PedidoDAO());
    }

}