package org.example.dao;

import org.example.connection.HibernateUtil;
import org.example.model.Cliente;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.List;

public class ClienteDAO extends GenericDAOImpl<Cliente, Integer> {
    public ClienteDAO(){
        super(Cliente.class);
    }

    public List<Cliente> findByNameApellidos(String txt) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String textobusqueda = "%" + txt + "%";
            String busquedanom = "FROM Cliente WHERE nombre LIKE '"  + textobusqueda + "'";
            List<Cliente> clientesNom = session.createQuery(busquedanom, Cliente.class).list();
            List<Cliente> clientes;
            clientes = clientesNom;

            String busquedaAp1 = "FROM Cliente WHERE apellido1 LIKE '"  + textobusqueda + "'";
            List<Cliente> clientesAp1 = session.createQuery(busquedaAp1, Cliente.class).list();
            if(!clientesAp1.isEmpty()){
                for(Cliente c: clientesAp1){
                    clientes.add(c);
                }
            }

            String busquedaAp2 = "FROM Cliente WHERE apellido2 LIKE '"  + textobusqueda + "'";
            List<Cliente> clientesAp2 = session.createQuery(busquedaAp2, Cliente.class).list();
            if(!clientesAp2.isEmpty()){
                for(Cliente c: clientesAp2){
                    clientes.add(c);
                }
            }


            if (!clientes.isEmpty()){
                for(Cliente c:clientes) {
                    Hibernate.initialize(c.getPedidos());
                }
            }
            return clientes;
        }
    }

    @Override
    public Cliente findById(Integer clienteID){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Cliente cliente = session.get(Cliente.class, clienteID);
            Hibernate.initialize(cliente.getPedidos());
            return cliente;
        }
    }

    @Override
    public List<Cliente>findAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Cliente> clientes = session.createQuery("FROM Cliente", Cliente.class).list();
            if (!clientes.isEmpty()){
                for(Cliente c:clientes) {
                    Hibernate.initialize(c.getPedidos());
                }
            }
            return clientes;
        }
    }


}
