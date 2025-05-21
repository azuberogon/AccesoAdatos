package dao;

import connection.HibernateUtil;
import model.Pedido;
import model.PedidoProducto;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PedidoDAO extends GenericDaoImpl<Pedido, Long> {
    public PedidoDAO() {
        super(Pedido.class);
    }

    @Override
    public Pedido findById(Long pedidoID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Pedido pedido = session.get(Pedido.class, pedidoID);
            Hibernate.initialize(pedido.getCliente());
            Hibernate.initialize(pedido.getComercial());
            Hibernate.initialize(pedido.getPedidoProductos());
            return pedido;
        }
    }

    @Override
    public List<Pedido> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Pedido> pedidos = session.createQuery("FROM Pedido", Pedido.class).list();
            for (Pedido p : pedidos) {
                Hibernate.initialize(p.getCliente());
                Hibernate.initialize(p.getComercial());
                Hibernate.initialize(p.getPedidoProductos());
            }
            return pedidos;
        }
    }

    // Método adicional: Encontrar pedidos por cliente
    public List<Pedido> findByCliente(Long clienteID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pedido WHERE cliente.clienteID = :clienteID", Pedido.class)
                    .setParameter("clienteID", clienteID)
                    .list();
        }
    }

    // Método adicional: Encontrar pedidos por comercial
    public List<Pedido> findByComercial(Long comercialID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Pedido WHERE comercial.comercialID = :comercialID", Pedido.class)
                    .setParameter("comercialID", comercialID)
                    .list();
        }
    }

    // Método transaccional: Añadir producto a un pedido
    public void addProductoToPedido(Long pedidoID, PedidoProducto pedidoProducto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Pedido pedido = findById(pedidoID);
            if (pedido != null) {
                pedidoProducto.setPedido(pedido);
                session.save(pedidoProducto);
                pedido.getPedidoProductos().add(pedidoProducto);
                pedido.calcularTotal();
                session.update(pedido);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}
