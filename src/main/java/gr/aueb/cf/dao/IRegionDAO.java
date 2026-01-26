package gr.aueb.cf.dao;

import gr.aueb.cf.model.Region;

import java.util.List;
import java.util.Optional;

/**
 * Data Access Object interface for Region entity.
 * Defines CRUD operations and custom queries for Region.
 */
public interface IRegionDAO {
    /**
     * Persists a new region to the database.
     *
     * @param region the region to persist
     * @return the persisted region with generated ID
     */
    Region insert(Region region);

    /**
     * Updates an existing region in the database.
     *
     * @param region the region to update
     * @return the updated region
     */
    Region update(Region region);

    /**
     * Deletes a region from the database.
     *
     * @param id the ID of the region to delete
     */
    void delete(Long id);

    /**
     * Finds a region by ID.
     *
     * @param id the ID of the region
     * @return Optional containing the region if found, empty otherwise
     */
    Optional<Region> getById(Long id);

    /**
     * Retrieves all regions from the database.
     *
     * @return a list of all regions
     */
    List<Region> getAll();

    /**
     * Finds a region by title.
     *
     * @param title the title to search for
     * @return Optional containing the region if found, empty otherwise
     */
    Optional<Region> getByTitle(String title);
}
