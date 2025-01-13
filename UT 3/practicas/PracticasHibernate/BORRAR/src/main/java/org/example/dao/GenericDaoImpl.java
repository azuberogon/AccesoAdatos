package org.example.dao;


import org.example.conection.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GenericDaoImpl <T,ID> implements GenericDAO<T,ID>{

    private final Class<T> clazz;

    public GenericDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void create(T entity) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){ // arbimos la transaccion
            transaction = session.beginTransaction(); // aqui empiza la transaccion
            session.persist(entity); // aqui en el objeto sesion lo cargamos con el objeto entity y le decimos que este objeto lo gurde en la base de datos...
            transaction.commit();
        }catch (Exception e){
            if (transaction != null)transaction.rollback(); // si la transaccion es diferente de null que haga un rollback pero si es null no funciona por que no cerrara nada
            throw e;
        }
    }

    @Override
    public List<T> findAll() throws Exception {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM "+clazz.getName(),clazz).list();//clazz.genName() es el nombre de la clase que hace referencia a el nombre de la tabla
        }

    }

    @Override
    public T findById(ID id) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(clazz,id);//el get necesita un y un id
        }

    }

    @Override
    public void update(T entity) throws Exception {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.merge(entity);// el update que te lo hace hibernete
            transaction.commit(); //para aplicar los cambios
        }catch (Exception e){
            if (transaction != null ) transaction.rollback();
        }
    }

    @Override
    public void delete(T entity) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();

        }catch (Exception e){

        }
    }
}
