package dao;

import model.Pedido;

public class PedidoDAO extends GenericDAOImpl<Pedido, Integer> {
    public PedidoDAO() {
        super(Pedido.class);
    }
}