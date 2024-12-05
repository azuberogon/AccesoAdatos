package dao;

import model.Pedido;

public class PedidoDAO extends GenericDaoImpl<Pedido,Long> {
    public PedidoDAO(){
        super(Pedido.class);
    }
}
