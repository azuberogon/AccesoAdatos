package dao;

import model.Cliente;
import model.Pedido;

public class PedidoDAO extends GenericDAOImpl<Pedido, Long> {
    public PedidoDAO() {
        super(Pedido.class);
    }
}
