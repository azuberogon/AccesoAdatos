package dao;

import model.Pedido;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Pedido;

import java.util.List;

public class PedidoDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    /**
     * Obtiene una instancia de EntityManager.
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Método para obtener todos los pedidos.
     */
    public List<Pedido> obtenerTodos() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p", Pedido.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Método para obtener todos los pedidos con sus relaciones (Cliente y Comercial).
     */
    public List<Pedido> obtenerTodosConRelaciones() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Pedido> query = em.createQuery(
                    "SELECT p FROM Pedido p LEFT JOIN FETCH p.cliente LEFT JOIN FETCH p.comercial",
                    Pedido.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Guardar un pedido en la base de datos.
     */
    public void guardar(Pedido pedido) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Actualizar un pedido existente.
     */
    public void actualizar(Pedido pedido) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(pedido);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Eliminar un pedido por su ID.
     */
    public void eliminar(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Pedido pedido = em.find(Pedido.class, id);
            if (pedido != null) {
                em.remove(pedido);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Buscar un pedido por su ID.
     */
    public Pedido buscarPorId(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }
}

