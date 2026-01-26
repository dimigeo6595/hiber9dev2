package gr.aueb.cf.dao.impl;

import gr.aueb.cf.dao.IRegionDAO;
import gr.aueb.cf.model.Region;
import gr.aueb.cf.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of IRegionDAO using JPA EntityManager.
 */
public class RegionDAOImpl implements IRegionDAO {

    @Override
    public Region insert(Region region) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(region);
            em.getTransaction().commit();
            return region;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Region update(Region region) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Region updatedRegion = em.merge(region);
            em.getTransaction().commit();
            return updatedRegion;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Region region = em.find(Region.class, id);
            if (region != null) {
                em.remove(region);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Region> getById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Region region = em.find(Region.class, id);
            return Optional.ofNullable(region);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Region> getAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Region> query = em.createQuery("SELECT r FROM Region r", Region.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Region> getByTitle(String title) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Region> query = em.createQuery(
                    "SELECT r FROM Region r WHERE r.title = :title", Region.class);
            query.setParameter("title", title);
            List<Region> results = query.getResultList();
            return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
        } finally {
            em.close();
        }
    }
}
