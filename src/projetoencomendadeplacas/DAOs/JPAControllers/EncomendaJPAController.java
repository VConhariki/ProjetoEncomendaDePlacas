package projetoencomendadeplacas.DAOs.JPAControllers;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import projetoencomendadeplacas.Entities.Encomenda;

public class EncomendaJPAController implements Serializable{
    public EncomendaJPAController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Encomenda encomenda) throws Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(encomenda);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEncomenda(encomenda.getId()) != null) {
                throw new Exception("Encomenda " + encomenda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Encomenda encomenda) throws EntityNotFoundException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            encomenda = em.merge(encomenda);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = encomenda.getId();
                if (findEncomenda(id) == null) {
                    throw new EntityNotFoundException("The encomenda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
        
    public void destroy(Integer id) throws EntityNotFoundException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Encomenda encomenda;
            try {
                encomenda = em.getReference(Encomenda.class, id);
                encomenda.getId();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("The encomenda with id " + id + " no longer exists.\nTrace: " + enfe.getMessage());
            }
            em.remove(encomenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Encomenda> findEncomendaEntities() {
        return findEncomendaEntities(true, -1, -1);
    }

    public List<Encomenda> findEncomendaEntities(int maxResults, int firstResult) {
        return findEncomendaEntities(false, maxResults, firstResult);
    }

    private List<Encomenda> findEncomendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Encomenda.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public Encomenda findEncomenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Encomenda.class, id);
        } finally {
            em.close();
        }
    }
}
