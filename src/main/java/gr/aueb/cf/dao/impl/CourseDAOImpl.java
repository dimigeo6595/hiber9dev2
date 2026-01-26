package gr.aueb.cf.dao.impl;

import gr.aueb.cf.dao.ICourseDAO;
import gr.aueb.cf.model.Course;
import gr.aueb.cf.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ICourseDAO using JPA EntityManager.
 */
public class CourseDAOImpl implements ICourseDAO {

    @Override
    public Course insert(Course course) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
            return course;
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
    public Course update(Course course) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Course updatedCourse = em.merge(course);
            em.getTransaction().commit();
            return updatedCourse;
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
            Course course = em.find(Course.class, id);
            if (course != null) {
                em.remove(course);
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
    public Optional<Course> getById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Course course = em.find(Course.class, id);
            return Optional.ofNullable(course);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Course> getAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c", Course.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Course> getByTitle(String title) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Course> query = em.createQuery(
                    "SELECT c FROM Course c WHERE c.title = :title", Course.class);
            query.setParameter("title", title);
            List<Course> results = query.getResultList();
            return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
        } finally {
            em.close();
        }
    }
}
