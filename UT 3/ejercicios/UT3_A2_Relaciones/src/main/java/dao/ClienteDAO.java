package dao;

import connection.HibernateUtil;
import model.Cliente;
import model.Pedido;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClienteDAO extends GenericDaoImpl<Cliente, Long> {
    public ClienteDAO() {
        super(Cliente.class);
    }

    @Override
    public Cliente findById(Long clienteID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Cliente cliente = session.get(Cliente.class, clienteID);
            Hibernate.initialize(cliente.getPedidos());
            return cliente;
        }
    }

    @Override
    public List<Cliente> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Cliente> clientes = session.createQuery("FROM Cliente", Cliente.class).list();
            for (Cliente c : clientes) {
                Hibernate.initialize(c.getPedidos());
            }
            return clientes;
        }
    }

    // Método adicional: Encontrar clientes por ciudad
    public List<Cliente> findByCiudad(String ciudad) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Cliente WHERE ciudad = :ciudad", Cliente.class)
                    .setParameter("ciudad", ciudad)
                    .list();
        }
    }

    // Método adicional: Obtener todos los pedidos de un cliente
    public List<Pedido> getPedidos(Long clienteID) {
        Cliente cliente = findById(clienteID);
        return cliente != null ? cliente.getPedidos() : null;
    }

    // Método transaccional: Eliminar cliente y sus pedidos asociados
    public void deleteClienteWithPedidos(Long clienteID) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Cliente cliente = findById(clienteID);
            if (cliente != null) {
                session.delete(cliente);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}
