package gr.aueb.cf.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Utility class for managing JPA EntityManagerFactory and EntityManager instances.
 * Provides singleton pattern for EntityManagerFactory to ensure efficient resource management.
 */
public class JPAUtil {
    private static final String PERSISTENCE_UNIT_NAME = "schoolPU";
    private static EntityManagerFactory emf;

    /**
     * Private constructor to prevent instantiation.
     */
    private JPAUtil() {
    }

    /**
     * Gets the EntityManagerFactory instance (singleton pattern).
     * Creates it if it doesn't exist.
     *
     * @return the EntityManagerFactory instance
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return emf;
    }

    /**
     * Creates a new EntityManager instance.
     *
     * @return a new EntityManager instance
     */
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    /**
     * Closes the EntityManagerFactory and releases all resources.
     * Should be called when the application shuts down.
     */
    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
