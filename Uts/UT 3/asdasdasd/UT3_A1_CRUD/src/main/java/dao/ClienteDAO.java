package dao;

import com.mysql.cj.xdevapi.Client;
import model.Cliente;

public class ClienteDAO extends GenericDAOImpl<Cliente, Long> {
    public ClienteDAO() {
        super(Cliente.class);
    }
}

