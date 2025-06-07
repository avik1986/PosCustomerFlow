package com.pos.customer.service;

import com.pos.customer.service.dto.AddressDetailsDTO;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.pos.customer.domain.AddressDetails}.
 */
public interface AddressDetailsService {
    /**
     * Save a addressDetails.
     *
     * @param addressDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    AddressDetailsDTO save(AddressDetailsDTO addressDetailsDTO);

    /**
     * Updates a addressDetails.
     *
     * @param addressDetailsDTO the entity to update.
     * @return the persisted entity.
     */
    AddressDetailsDTO update(AddressDetailsDTO addressDetailsDTO);

    /**
     * Partially updates a addressDetails.
     *
     * @param addressDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AddressDetailsDTO> partialUpdate(AddressDetailsDTO addressDetailsDTO);

    /**
     * Get the "id" addressDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AddressDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" addressDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
