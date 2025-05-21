package dao;

import connection.HibernateUtil;
import model.Cliente;
import org.hibernate.Hibernate;
import org.hibernate.Session;

public class ClienteDAO extends GenericDAOImpl<Cliente, Integer> {
    public ClienteDAO() {
        super(Cliente.class);
    }

    public Cliente findByIdWithPedidos(int clienteID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Cliente cliente = session.get(Cliente.class, clienteID);
            // Cargar los pedidos mientras la sesión está activa
            Hibernate.initialize(cliente.getPedidos());
            return cliente;
        }
    }
}