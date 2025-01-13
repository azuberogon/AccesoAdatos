package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Comercial;

import java.util.List;

public class ComercialDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidadDePersistencia");

    /**
     * Obtiene una instancia de EntityManager.
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Método para obtener todos los comerciales.
     */
    public List<Comercial> obtenerTodos() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Comercial> query = em.createQuery("SELECT c FROM Comercial c", Comercial.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Guardar un comercial en la base de datos.
     */
    public void guardar(Comercial comercial) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(comercial);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Actualizar un comercial existente.
     */
    public void actualizar(Comercial comercial) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(comercial);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Eliminar un comercial por su ID.
     */
    public void eliminar(Long id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Comercial comercial = em.find(Comercial.class, id);
            if (comercial != null) {
                em.remove(comercial);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    /**
     * Buscar un comercial por su ID.
     */
    public Comercial buscarPorId(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comercial.class, id);
        } finally {
            em.close();
        }
    }
}
