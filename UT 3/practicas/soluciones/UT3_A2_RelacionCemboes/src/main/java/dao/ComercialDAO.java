package dao;

import connection.HibernateUtil;
import model.Cliente;
import model.Comercial;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

public class ComercialDAO extends GenericDaoImpl<Comercial,Long> {
    public ComercialDAO(){
        super(Comercial.class);
    }

    @Override
    public Comercial findById(Long comercialID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Comercial comercial = session.get(Comercial.class, comercialID);
            Hibernate.initialize(comercial.getPedidos());
            return comercial;
        }
    }

    @Override
    public List<Comercial> findAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Comercial> comerciales = session.createQuery("FROM Comercial", Comercial.class).list();
            if (!comerciales.isEmpty()){
                for(Comercial c: comerciales) {
                    Hibernate.initialize(c.getPedidos());
                }
            }
            return comerciales;
        }
    }
}
