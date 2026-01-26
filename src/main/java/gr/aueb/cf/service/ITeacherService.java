package gr.aueb.cf.service;

import gr.aueb.cf.model.Teacher;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for Teacher business logic.
 * Provides high-level operations for managing teachers.
 */
public interface ITeacherService {
    /**
     * Creates a new teacher.
     *
     * @param teacher the teacher to create
     * @return the created teacher with generated ID
     * @throws Exception if teacher creation fails
     */
    Teacher createTeacher(Teacher teacher) throws Exception;

    /**
     * Updates an existing teacher.
     *
     * @param teacher the teacher to update
     * @return the updated teacher
     * @throws Exception if teacher update fails
     */
    Teacher updateTeacher(Teacher teacher) throws Exception;

    /**
     * Deletes a teacher by ID.
     *
     * @param id the ID of the teacher to delete
     * @throws Exception if teacher deletion fails
     */
    void deleteTeacher(Long id) throws Exception;

    /**
     * Retrieves a teacher by ID.
     *
     * @param id the ID of the teacher
     * @return Optional containing the teacher if found, empty otherwise
     */
    Optional<Teacher> getTeacherById(Long id);

    /**
     * Retrieves all teachers.
     *
     * @return a list of all teachers
     */
    List<Teacher> getAllTeachers();

    /**
     * Finds teachers by lastname.
     *
     * @param lastname the lastname to search for
     * @return a list of teachers with the specified lastname
     */
    List<Teacher> getTeachersByLastname(String lastname);

    /**
     * Retrieves all active teachers.
     *
     * @return a list of active teachers
     */
    List<Teacher> getActiveTeachers();
}
