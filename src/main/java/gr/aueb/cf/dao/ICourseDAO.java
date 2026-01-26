package gr.aueb.cf.dao;

import gr.aueb.cf.model.Course;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object interface for Course entity.
 * Defines CRUD operations and custom queries for Course.
 */
public interface ICourseDAO {
    /**
     * Persists a new course to the database.
     *
     * @param course the course to persist
     * @return the persisted course with generated ID
     */
    Course insert(Course course);

    /**
     * Updates an existing course in the database.
     *
     * @param course the course to update
     * @return the updated course
     */
    Course update(Course course);

    /**
     * Deletes a course from the database.
     *
     * @param id the ID of the course to delete
     */
    void delete(Long id);

    /**
     * Finds a course by ID.
     *
     * @param id the ID of the course
     * @return Optional containing the course if found, empty otherwise
     */
    Optional<Course> getById(Long id);

    /**
     * Retrieves all courses from the database.
     *
     * @return a list of all courses
     */
    List<Course> getAll();

    /**
     * Finds a course by title.
     *
     * @param title the title to search for
     * @return Optional containing the course if found, empty otherwise
     */
    Optional<Course> getByTitle(String title);
}
