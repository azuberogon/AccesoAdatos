package org.example.dao;

import org.example.connection.HibernateUtil;
import org.example.model.Pedido;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

public class PedidoDAO extends GenericDAOImpl<Pedido, Integer> {
    public PedidoDAO(){
        super(Pedido.class);
    }

    @Override
    public void update(Pedido pedi) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Pedido pedido = session.get(Pedido.class, pedi.getId());
            Hibernate.initialize(pedido.getContiene());
        }
    }

    @Override
    public Pedido findById(Integer pedidoID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Pedido pedido = session.get(Pedido.class, pedidoID);
            Hibernate.initialize(pedido.getCliente());
            Hibernate.initialize(pedido.getComercial());
            Hibernate.initialize(pedido.getContiene());
            return pedido;
        }
    }

    @Override
    public List<Pedido> findAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Pedido> pedidos = session.createQuery("FROM Pedido", Pedido.class).list();
            if (!pedidos.isEmpty()){
                for(Pedido pedido : pedidos) {
                    Hibernate.initialize(pedido.getCliente());
                    Hibernate.initialize(pedido.getComercial());
                    Hibernate.initialize(pedido.getContiene());
                }
            }
            return pedidos;
        }
    }


}
