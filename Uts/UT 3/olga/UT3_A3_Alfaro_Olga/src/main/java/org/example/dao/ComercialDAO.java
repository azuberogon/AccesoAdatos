package org.example.dao;

import org.example.connection.HibernateUtil;
import org.example.model.Comercial;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

public class ComercialDAO extends GenericDAOImpl<Comercial, Integer> {
    public ComercialDAO(){
        super(Comercial.class);
    }

    @Override
    public Comercial findById(Integer comercialID){
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


    public List<Comercial> findByNameApellidos(String txt) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String textobusqueda = "%" + txt + "%";
            String busquedanom = "FROM Comercial WHERE nombre LIKE '"  + textobusqueda + "'";
            List<Comercial> comercialesNom = session.createQuery(busquedanom, Comercial.class).list();
            List<Comercial> comerciales;
            comerciales = comercialesNom;

            String busquedaAp1 = "FROM Comercial WHERE apellido1 LIKE '"  + textobusqueda + "'";
            List<Comercial> comercialesAp1 = session.createQuery(busquedaAp1, Comercial.class).list();
            if(!comercialesAp1.isEmpty()){
                for(Comercial c: comercialesAp1){
                    comerciales.add(c);
                }
            }

            String busquedaAp2 = "FROM Comercial WHERE apellido2 LIKE '"  + textobusqueda + "'";
            List<Comercial> comercialesAp2 = session.createQuery(busquedaAp2, Comercial.class).list();
            if(!comercialesAp2.isEmpty()){
                for(Comercial c: comercialesAp2){
                    comerciales.add(c);
                }
            }

            if (!comerciales.isEmpty()){
                for(Comercial c:comerciales) {
                    Hibernate.initialize(c.getPedidos());
                }
            }
            return comerciales;
        }
    }
}
