package gr.aueb.cf.service;

import gr.aueb.cf.model.Region;
import gr.aueb.cf.model.Teacher;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for Region business logic.
 * Provides high-level operations for managing regions.
 */
public interface IRegionService {
    /**
     * Creates a new region.
     *
     * @param region the region to create
     * @return the created region with generated ID
     * @throws Exception if region creation fails
     */
    Region createRegion(Region region) throws Exception;

    /**
     * Updates an existing region.
     *
     * @param region the region to update
     * @return the updated region
     * @throws Exception if region update fails
     */
    Region updateRegion(Region region) throws Exception;

    /**
     * Deletes a region by ID.
     *
     * @param id the ID of the region to delete
     * @throws Exception if region deletion fails
     */
    void deleteRegion(Long id) throws Exception;

    /**
     * Retrieves a region by ID.
     *
     * @param id the ID of the region
     * @return Optional containing the region if found, empty otherwise
     */
    Optional<Region> getRegionById(Long id);

    /**
     * Retrieves all regions.
     *
     * @return a list of all regions
     */
    List<Region> getAllRegions();

    /**
     * Finds a region by title.
     *
     * @param title the title to search for
     * @return Optional containing the region if found, empty otherwise
     */
    Optional<Region> getRegionByTitle(String title);

    /**
     * Adds a teacher to a region.
     *
     * @param regionId the ID of the region
     * @param teacherId the ID of the teacher
     * @throws Exception if the operation fails
     */
    void addTeacherToRegion(Long regionId, Long teacherId) throws Exception;

    /**
     * Removes a teacher from a region.
     *
     * @param regionId the ID of the region
     * @param teacherId the ID of the teacher
     * @throws Exception if the operation fails
     */
    void removeTeacherFromRegion(Long regionId, Long teacherId) throws Exception;
}
