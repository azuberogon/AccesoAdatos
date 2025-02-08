package service;


import dao.PedidoDAO;
import model.Cliente;
import model.Pedido;

public class PedidoService  extends GenericServiceImpl<Pedido, Long> {
    public PedidoService() {
        super(new PedidoDAO());
    }
}

