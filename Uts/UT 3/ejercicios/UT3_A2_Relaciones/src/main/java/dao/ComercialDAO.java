package dao;

import connection.HibernateUtil;
import model.Comercial;
import model.Pedido;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

public class ComercialDAO extends GenericDaoImpl<Comercial, Long> {
    public ComercialDAO() {
        super(Comercial.class);
    }

    @Override
    public Comercial findById(Long comercialID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Comercial comercial = session.get(Comercial.class, comercialID);
            Hibernate.initialize(comercial.getPedidos());
            return comercial;
        }
    }

    @Override
    public List<Comercial> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Comercial> comerciales = session.createQuery("FROM Comercial", Comercial.class).list();
            for (Comercial c : comerciales) {
                Hibernate.initialize(c.getPedidos());
            }
            return comerciales;
        }
    }

    // Método adicional: Obtener todos los pedidos gestionados por un comercial
    public List<Pedido> getPedidos(Long comercialID) {
        Comercial comercial = findById(comercialID);
        return comercial != null ? comercial.getPedidos() : null;
    }

    // Método adicional: Encontrar comerciales por nombre
    public List<Comercial> findByNombre(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Comercial WHERE nombre LIKE :nombre", Comercial.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .list();
        }
    }
}
