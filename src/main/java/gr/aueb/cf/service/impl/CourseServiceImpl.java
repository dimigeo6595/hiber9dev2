package gr.aueb.cf.service.impl;

import gr.aueb.cf.dao.ICourseDAO;
import gr.aueb.cf.dao.ITeacherDAO;
import gr.aueb.cf.dao.impl.CourseDAOImpl;
import gr.aueb.cf.dao.impl.TeacherDAOImpl;
import gr.aueb.cf.model.Course;
import gr.aueb.cf.model.Teacher;
import gr.aueb.cf.service.ICourseService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ICourseService.
 * Handles business logic for course operations.
 */
public class CourseServiceImpl implements ICourseService {
    private final ICourseDAO courseDAO;
    private final ITeacherDAO teacherDAO;

    public CourseServiceImpl() {
        this.courseDAO = new CourseDAOImpl();
        this.teacherDAO = new TeacherDAOImpl();
    }

    public CourseServiceImpl(ICourseDAO courseDAO, ITeacherDAO teacherDAO) {
        this.courseDAO = courseDAO;
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Course createCourse(Course course) throws Exception {
        try {
            validateCourse(course);
            // Check if course with same title already exists
            Optional<Course> existingCourse = courseDAO.getByTitle(course.getTitle());
            if (existingCourse.isPresent()) {
                throw new IllegalArgumentException("Course with title '" + course.getTitle() + "' already exists");
            }
            return courseDAO.insert(course);
        } catch (Exception e) {
            throw new Exception("Error creating course: " + e.getMessage(), e);
        }
    }

    @Override
    public Course updateCourse(Course course) throws Exception {
        try {
            validateCourse(course);
            if (course.getId() == null) {
                throw new IllegalArgumentException("Course ID cannot be null for update");
            }
            return courseDAO.update(course);
        } catch (Exception e) {
            throw new Exception("Error updating course: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteCourse(Long id) throws Exception {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Course ID cannot be null");
            }
            courseDAO.delete(id);
        } catch (Exception e) {
            throw new Exception("Error deleting course: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return courseDAO.getById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseDAO.getAll();
    }

    @Override
    public Optional<Course> getCourseByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return Optional.empty();
        }
        return courseDAO.getByTitle(title);
    }

    @Override
    public void addTeacherToCourse(Long courseId, Long teacherId) throws Exception {
        try {
            if (courseId == null || teacherId == null) {
                throw new IllegalArgumentException("Course ID and Teacher ID cannot be null");
            }

            Optional<Course> courseOpt = courseDAO.getById(courseId);
            if (courseOpt.isEmpty()) {
                throw new IllegalArgumentException("Course with ID " + courseId + " not found");
            }

            Optional<Teacher> teacherOpt = teacherDAO.getById(teacherId);
            if (teacherOpt.isEmpty()) {
                throw new IllegalArgumentException("Teacher with ID " + teacherId + " not found");
            }

            Course course = courseOpt.get();
            Teacher teacher = teacherOpt.get();

            // Use the bidirectional relationship management
            course.addTeacher(teacher);
            courseDAO.update(course);
        } catch (Exception e) {
            throw new Exception("Error adding teacher to course: " + e.getMessage(), e);
        }
    }

    @Override
    public void removeTeacherFromCourse(Long courseId, Long teacherId) throws Exception {
        try {
            if (courseId == null || teacherId == null) {
                throw new IllegalArgumentException("Course ID and Teacher ID cannot be null");
            }

            Optional<Course> courseOpt = courseDAO.getById(courseId);
            if (courseOpt.isEmpty()) {
                throw new IllegalArgumentException("Course with ID " + courseId + " not found");
            }

            Optional<Teacher> teacherOpt = teacherDAO.getById(teacherId);
            if (teacherOpt.isEmpty()) {
                throw new IllegalArgumentException("Teacher with ID " + teacherId + " not found");
            }

            Course course = courseOpt.get();
            Teacher teacher = teacherOpt.get();

            // Use the bidirectional relationship management
            course.removeTeacher(teacher);
            courseDAO.update(course);
        } catch (Exception e) {
            throw new Exception("Error removing teacher from course: " + e.getMessage(), e);
        }
    }

    /**
     * Validates course data before persistence.
     *
     * @param course the course to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        if (course.getTitle() == null || course.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Course title cannot be null or empty");
        }
    }
}
