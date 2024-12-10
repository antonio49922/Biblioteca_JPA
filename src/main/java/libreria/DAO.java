package libreria;

import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class DAO<T, ID> {
    private EntityManager em;
    private Class<T> entityClass;

    public DAO() {
        em = Conexion.getEntityManager();
        this.entityClass = entityClass;
    }

    public void insert(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public void update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    public void delete(T entity) {
        em.getTransaction().begin();
        em.remove(em.merge(entity));
        em.getTransaction().commit();
    }

    public T buscar(ID id) {
        return em.find(entityClass, id);
    }

    public List<T> buscar() {
        return (ArrayList<T>) em.createQuery("from " + entityClass.getSimpleName()).getResultList();
    }
}
