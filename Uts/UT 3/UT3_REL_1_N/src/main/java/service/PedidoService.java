package service;

import dao.PedidoDAO;
import model.Pedido;

public class PedidoService extends GenericServiceImpl<Pedido, Integer> {
    public PedidoService() {
        super(new PedidoDAO());
    }
}