package org.example.service;

import org.example.dao.ClienteDAO;
import org.example.model.Cliente;

import java.util.List;

public class ClienteService extends GenericServiceImpl<Cliente, Integer> {
    public ClienteService() {
        super(new ClienteDAO());
    }

    public List<Cliente> findByNameApellidos(String txt){
        ClienteDAO clienteDAO = (ClienteDAO) getDao();
        return clienteDAO.findByNameApellidos(txt);
    }

}
