package dao;

import connection.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {
    private final Class<T> clazz;

    public GenericDAOImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void create(T entity) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (session.isConnected() && transaction != null) {
                transaction.rollback();
                session.close();
            }
            throw e;
        } finally { session.close(); }
    }

    @Override
    public T findById(ID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(clazz, id);
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM " + clazz.getName(), clazz).list();
        }
    }

    @Override
    public void update(T entity) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (session.isConnected() && transaction != null) {
                transaction.rollback();
                session.close();
            }
            throw e;
        } finally { session.close(); }
    }

    @Override
    public void delete(T entity) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (session.isConnected() && transaction != null) {
                transaction.rollback();
                session.close();
            }
            throw e;
        } finally { session.close(); }
    }
}
