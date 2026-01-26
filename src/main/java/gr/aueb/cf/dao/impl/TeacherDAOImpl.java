package gr.aueb.cf.dao.impl;

import gr.aueb.cf.dao.ITeacherDAO;
import gr.aueb.cf.model.Teacher;
import gr.aueb.cf.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ITeacherDAO using JPA EntityManager.
 */
public class TeacherDAOImpl implements ITeacherDAO {

    @Override
    public Teacher insert(Teacher teacher) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(teacher);
            em.getTransaction().commit();
            return teacher;
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
    public Teacher update(Teacher teacher) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Teacher updatedTeacher = em.merge(teacher);
            em.getTransaction().commit();
            return updatedTeacher;
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
            Teacher teacher = em.find(Teacher.class, id);
            if (teacher != null) {
                em.remove(teacher);
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
    public Optional<Teacher> getById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Teacher teacher = em.find(Teacher.class, id);
            return Optional.ofNullable(teacher);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Teacher> getAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t", Teacher.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Teacher> getByLastname(String lastname) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Teacher> query = em.createQuery(
                    "SELECT t FROM Teacher t WHERE t.lastname = :lastname", Teacher.class);
            query.setParameter("lastname", lastname);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Teacher> getActiveTeachers() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Teacher> query = em.createQuery(
                    "SELECT t FROM Teacher t WHERE t.active = true", Teacher.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
