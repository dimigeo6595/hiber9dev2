package gr.aueb.cf.service.impl;

import gr.aueb.cf.dao.IRegionDAO;
import gr.aueb.cf.dao.ITeacherDAO;
import gr.aueb.cf.dao.impl.RegionDAOImpl;
import gr.aueb.cf.dao.impl.TeacherDAOImpl;
import gr.aueb.cf.model.Region;
import gr.aueb.cf.model.Teacher;
import gr.aueb.cf.service.IRegionService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of IRegionService.
 * Handles business logic for region operations.
 */
public class RegionServiceImpl implements IRegionService {
    private final IRegionDAO regionDAO;
    private final ITeacherDAO teacherDAO;

    public RegionServiceImpl() {
        this.regionDAO = new RegionDAOImpl();
        this.teacherDAO = new TeacherDAOImpl();
    }

    public RegionServiceImpl(IRegionDAO regionDAO, ITeacherDAO teacherDAO) {
        this.regionDAO = regionDAO;
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Region createRegion(Region region) throws Exception {
        try {
            validateRegion(region);
            // Check if region with same title already exists
            Optional<Region> existingRegion = regionDAO.getByTitle(region.getTitle());
            if (existingRegion.isPresent()) {
                throw new IllegalArgumentException("Region with title '" + region.getTitle() + "' already exists");
            }
            return regionDAO.insert(region);
        } catch (Exception e) {
            throw new Exception("Error creating region: " + e.getMessage(), e);
        }
    }

    @Override
    public Region updateRegion(Region region) throws Exception {
        try {
            validateRegion(region);
            if (region.getId() == null) {
                throw new IllegalArgumentException("Region ID cannot be null for update");
            }
            return regionDAO.update(region);
        } catch (Exception e) {
            throw new Exception("Error updating region: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteRegion(Long id) throws Exception {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Region ID cannot be null");
            }
            regionDAO.delete(id);
        } catch (Exception e) {
            throw new Exception("Error deleting region: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Region> getRegionById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return regionDAO.getById(id);
    }

    @Override
    public List<Region> getAllRegions() {
        return regionDAO.getAll();
    }

    @Override
    public Optional<Region> getRegionByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return Optional.empty();
        }
        return regionDAO.getByTitle(title);
    }

    @Override
    public void addTeacherToRegion(Long regionId, Long teacherId) throws Exception {
        try {
            if (regionId == null || teacherId == null) {
                throw new IllegalArgumentException("Region ID and Teacher ID cannot be null");
            }

            Optional<Region> regionOpt = regionDAO.getById(regionId);
            if (regionOpt.isEmpty()) {
                throw new IllegalArgumentException("Region with ID " + regionId + " not found");
            }

            Optional<Teacher> teacherOpt = teacherDAO.getById(teacherId);
            if (teacherOpt.isEmpty()) {
                throw new IllegalArgumentException("Teacher with ID " + teacherId + " not found");
            }

            Region region = regionOpt.get();
            Teacher teacher = teacherOpt.get();

            // Use the bidirectional relationship management
            region.addTeacher(teacher);
            regionDAO.update(region);
        } catch (Exception e) {
            throw new Exception("Error adding teacher to region: " + e.getMessage(), e);
        }
    }

    @Override
    public void removeTeacherFromRegion(Long regionId, Long teacherId) throws Exception {
        try {
            if (regionId == null || teacherId == null) {
                throw new IllegalArgumentException("Region ID and Teacher ID cannot be null");
            }

            Optional<Region> regionOpt = regionDAO.getById(regionId);
            if (regionOpt.isEmpty()) {
                throw new IllegalArgumentException("Region with ID " + regionId + " not found");
            }

            Optional<Teacher> teacherOpt = teacherDAO.getById(teacherId);
            if (teacherOpt.isEmpty()) {
                throw new IllegalArgumentException("Teacher with ID " + teacherId + " not found");
            }

            Region region = regionOpt.get();
            Teacher teacher = teacherOpt.get();

            // Use the bidirectional relationship management
            region.removeTeacher(teacher);
            regionDAO.update(region);
        } catch (Exception e) {
            throw new Exception("Error removing teacher from region: " + e.getMessage(), e);
        }
    }

    /**
     * Validates region data before persistence.
     *
     * @param region the region to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateRegion(Region region) {
        if (region == null) {
            throw new IllegalArgumentException("Region cannot be null");
        }
        if (region.getTitle() == null || region.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Region title cannot be null or empty");
        }
    }
}
