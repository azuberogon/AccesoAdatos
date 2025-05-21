package org.example.dao;

import org.example.connection.HibernateUtil;
import org.example.model.Producto;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

public class ProductoDAO extends GenericDAOImpl<Producto, Integer> {
    public ProductoDAO(){
        super(Producto.class);
    }

    @Override
    public void update(Producto prod){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Producto producto = session.get(Producto.class, prod.getId());
            Hibernate.initialize(producto.getContenidoEn());
        }
    }

    @Override
    public Producto findById(Integer productoID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Producto producto = session.get(Producto.class, productoID);
            Hibernate.initialize(producto.getContenidoEn());
            return producto;
        }
    }

    @Override
    public List<Producto> findAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Producto> productos = session.createQuery("FROM Producto", Producto.class).list();
            if (!productos.isEmpty()){
                for(Producto c: productos) {
                    Hibernate.initialize(c.getContenidoEn());
                }
            }
            return productos;
        }
    }

    public List<Producto> findByName(String txt) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String textobusqueda = "%" + txt + "%";
            String busqueda = "FROM Producto WHERE nombre LIKE '"  + textobusqueda + "'";
            List<Producto> productos = session.createQuery(busqueda, Producto.class).list();

            if (!productos.isEmpty()){
                for(Producto c:productos) {
                    Hibernate.initialize(c.getContenidoEn());
                }
            }
            return productos;
        }
    }

}
