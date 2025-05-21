package service;

import dao.ClienteDAO;
import model.Cliente;


public class ClienteService extends GenericServiceImpl<Cliente, Integer> {
    public ClienteService() {
        super(new ClienteDAO());
    }

    public Cliente findByIdWithPedidos(int clienteID) {
        ClienteDAO clienteDAO = (ClienteDAO) getDao();
        return clienteDAO.findByIdWithPedidos(clienteID);
    }
}