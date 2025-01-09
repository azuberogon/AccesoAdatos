package dao;

import connection.HibernateUtil;
import model.Pedido;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

public class PedidoDAO extends GenericDaoImpl<Pedido,Long> {
    public PedidoDAO(){
        super(Pedido.class);
    }

    @Override
    public Pedido findById(Long pedidoID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Pedido pedido = session.get(Pedido.class, pedidoID);
            Hibernate.initialize(pedido.getCliente());
            Hibernate.initialize(pedido.getComercial());
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
                }
            }
            return pedidos;
        }
    }
}
