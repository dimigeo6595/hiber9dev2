package gr.aueb.cf.service;

import gr.aueb.cf.model.Course;
import gr.aueb.cf.model.Teacher;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for Course business logic.
 * Provides high-level operations for managing courses.
 */
public interface ICourseService {
    /**
     * Creates a new course.
     *
     * @param course the course to create
     * @return the created course with generated ID
     * @throws Exception if course creation fails
     */
    Course createCourse(Course course) throws Exception;

    /**
     * Updates an existing course.
     *
     * @param course the course to update
     * @return the updated course
     * @throws Exception if course update fails
     */
    Course updateCourse(Course course) throws Exception;

    /**
     * Deletes a course by ID.
     *
     * @param id the ID of the course to delete
     * @throws Exception if course deletion fails
     */
    void deleteCourse(Long id) throws Exception;

    /**
     * Retrieves a course by ID.
     *
     * @param id the ID of the course
     * @return Optional containing the course if found, empty otherwise
     */
    Optional<Course> getCourseById(Long id);

    /**
     * Retrieves all courses.
     *
     * @return a list of all courses
     */
    List<Course> getAllCourses();

    /**
     * Finds a course by title.
     *
     * @param title the title to search for
     * @return Optional containing the course if found, empty otherwise
     */
    Optional<Course> getCourseByTitle(String title);

    /**
     * Adds a teacher to a course.
     *
     * @param courseId the ID of the course
     * @param teacherId the ID of the teacher
     * @throws Exception if the operation fails
     */
    void addTeacherToCourse(Long courseId, Long teacherId) throws Exception;

    /**
     * Removes a teacher from a course.
     *
     * @param courseId the ID of the course
     * @param teacherId the ID of the teacher
     * @throws Exception if the operation fails
     */
    void removeTeacherFromCourse(Long courseId, Long teacherId) throws Exception;
}
