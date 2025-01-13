package dao;

import model.Cliente;


public class ClienteDAO extends GenericDaoImpl<Cliente,Long> {
    public ClienteDAO(){
        super(Cliente.class);
    }
}
