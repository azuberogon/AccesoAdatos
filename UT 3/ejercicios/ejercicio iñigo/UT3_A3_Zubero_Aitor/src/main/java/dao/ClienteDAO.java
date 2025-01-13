package dao;

import connection.HibernateUtil;
import model.Cliente;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;


public class ClienteDAO extends GenericDaoImpl<Cliente,Long> {
    public ClienteDAO(){
        super(Cliente.class);
    }

    @Override
    public Cliente findById(Long clienteID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Cliente cliente = session.get(Cliente.class, clienteID);
            Hibernate.initialize(cliente.getPedidos());
            return cliente;
        }
    }

    @Override
    public List<Cliente>findAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Cliente> clientes = session.createQuery("FROM Cliente", Cliente.class).list();
            if (!clientes.isEmpty()){
                for(Cliente c:clientes) {
                    Hibernate.initialize(c.getPedidos());
                }
            }
            return clientes;
        }
    }
}
