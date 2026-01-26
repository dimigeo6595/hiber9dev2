package gr.aueb.cf.service.impl;

import gr.aueb.cf.dao.ITeacherDAO;
import gr.aueb.cf.dao.impl.TeacherDAOImpl;
import gr.aueb.cf.model.Teacher;
import gr.aueb.cf.service.ITeacherService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of ITeacherService.
 * Handles business logic for teacher operations.
 */
public class TeacherServiceImpl implements ITeacherService {
    private final ITeacherDAO teacherDAO;

    public TeacherServiceImpl() {
        this.teacherDAO = new TeacherDAOImpl();
    }

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher createTeacher(Teacher teacher) throws Exception {
        try {
            validateTeacher(teacher);
            return teacherDAO.insert(teacher);
        } catch (Exception e) {
            throw new Exception("Error creating teacher: " + e.getMessage(), e);
        }
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) throws Exception {
        try {
            validateTeacher(teacher);
            if (teacher.getId() == null) {
                throw new IllegalArgumentException("Teacher ID cannot be null for update");
            }
            return teacherDAO.update(teacher);
        } catch (Exception e) {
            throw new Exception("Error updating teacher: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteTeacher(Long id) throws Exception {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Teacher ID cannot be null");
            }
            teacherDAO.delete(id);
        } catch (Exception e) {
            throw new Exception("Error deleting teacher: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Teacher> getTeacherById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return teacherDAO.getById(id);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherDAO.getAll();
    }

    @Override
    public List<Teacher> getTeachersByLastname(String lastname) {
        if (lastname == null || lastname.trim().isEmpty()) {
            throw new IllegalArgumentException("Lastname cannot be null or empty");
        }
        return teacherDAO.getByLastname(lastname);
    }

    @Override
    public List<Teacher> getActiveTeachers() {
        return teacherDAO.getActiveTeachers();
    }

    /**
     * Validates teacher data before persistence.
     *
     * @param teacher the teacher to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null");
        }
        if (teacher.getFirstname() == null || teacher.getFirstname().trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher firstname cannot be null or empty");
        }
        if (teacher.getLastname() == null || teacher.getLastname().trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher lastname cannot be null or empty");
        }
    }
}
