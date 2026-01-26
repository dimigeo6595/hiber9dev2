package gr.aueb.cf.dao;

import gr.aueb.cf.model.Teacher;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object interface for Teacher entity.
 * Defines CRUD operations and custom queries for Teacher.
 */
public interface ITeacherDAO {
    /**
     * Persists a new teacher to the database.
     *
     * @param teacher the teacher to persist
     * @return the persisted teacher with generated ID
     */
    Teacher insert(Teacher teacher);

    /**
     * Updates an existing teacher in the database.
     *
     * @param teacher the teacher to update
     * @return the updated teacher
     */
    Teacher update(Teacher teacher);

    /**
     * Deletes a teacher from the database.
     *
     * @param id the ID of the teacher to delete
     */
    void delete(Long id);

    /**
     * Finds a teacher by ID.
     *
     * @param id the ID of the teacher
     * @return Optional containing the teacher if found, empty otherwise
     */
    Optional<Teacher> getById(Long id);

    /**
     * Retrieves all teachers from the database.
     *
     * @return a list of all teachers
     */
    List<Teacher> getAll();

    /**
     * Finds teachers by lastname.
     *
     * @param lastname the lastname to search for
     * @return a list of teachers with the specified lastname
     */
    List<Teacher> getByLastname(String lastname);

    /**
     * Finds active teachers.
     *
     * @return a list of active teachers
     */
    List<Teacher> getActiveTeachers();
}
