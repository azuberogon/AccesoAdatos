package service;

import dao.ClienteDAO;
import dao.ComercialDAO;
import model.Cliente;


public class ClienteService extends GenericServiceImpl<Cliente, Long> {
    public ClienteService() {
        super(new ClienteDAO());
    }
}