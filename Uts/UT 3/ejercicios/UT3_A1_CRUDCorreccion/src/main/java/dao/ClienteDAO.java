package dao;


import model.Cliente;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;


import model.Cliente;


import java.util.List;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import java.util.List;

public class ClienteDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    /**
     * Obtiene una instancia de EntityManager.
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Método para obtener todos los clientes.
     */
    public List<Cliente> obtenerTodos() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Método para obtener todos los clientes con sus pedidos.
     */
    public List<Cliente> obtenerTodosConPedidos() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos", Cliente.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Guardar un cliente en la base de datos.
     */
    public void guardar(Cliente cliente) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Actualizar un cliente existente.
     */
    public void actualizar(Cliente cliente) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cliente);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Eliminar un cliente por su ID.
     */
    public void eliminar(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
                em.remove(cliente);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Buscar un cliente por su ID.
     */
    public Cliente buscarPorId(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }
}
