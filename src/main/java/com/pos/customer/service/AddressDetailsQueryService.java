package com.pos.customer.service;

import com.pos.customer.domain.*; // for static metamodels
import com.pos.customer.domain.AddressDetails;
import com.pos.customer.repository.AddressDetailsRepository;
import com.pos.customer.service.criteria.AddressDetailsCriteria;
import com.pos.customer.service.dto.AddressDetailsDTO;
import com.pos.customer.service.mapper.AddressDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link AddressDetails} entities in the database.
 * The main input is a {@link AddressDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link AddressDetailsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AddressDetailsQueryService extends QueryService<AddressDetails> {

    private static final Logger LOG = LoggerFactory.getLogger(AddressDetailsQueryService.class);

    private final AddressDetailsRepository addressDetailsRepository;

    private final AddressDetailsMapper addressDetailsMapper;

    public AddressDetailsQueryService(AddressDetailsRepository addressDetailsRepository, AddressDetailsMapper addressDetailsMapper) {
        this.addressDetailsRepository = addressDetailsRepository;
        this.addressDetailsMapper = addressDetailsMapper;
    }

    /**
     * Return a {@link Page} of {@link AddressDetailsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AddressDetailsDTO> findByCriteria(AddressDetailsCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AddressDetails> specification = createSpecification(criteria);
        return addressDetailsRepository.findAll(specification, page).map(addressDetailsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AddressDetailsCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<AddressDetails> specification = createSpecification(criteria);
        return addressDetailsRepository.count(specification);
    }

    /**
     * Function to convert {@link AddressDetailsCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AddressDetails> createSpecification(AddressDetailsCriteria criteria) {
        Specification<AddressDetails> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            specification = Specification.allOf(
                Boolean.TRUE.equals(criteria.getDistinct()) ? distinct(criteria.getDistinct()) : null,
                buildRangeSpecification(criteria.getId(), AddressDetails_.id),
                buildSpecification(criteria.getAddressDetailType(), AddressDetails_.addressDetailType),
                buildStringSpecification(criteria.getMobileNo(), AddressDetails_.mobileNo),
                buildStringSpecification(criteria.getName(), AddressDetails_.name),
                buildStringSpecification(criteria.getLabel(), AddressDetails_.label),
                buildStringSpecification(criteria.getAddressLine1(), AddressDetails_.addressLine1),
                buildStringSpecification(criteria.getAddressLine2(), AddressDetails_.addressLine2),
                buildStringSpecification(criteria.getAddressLine3(), AddressDetails_.addressLine3),
                buildStringSpecification(criteria.getFloorNo(), AddressDetails_.floorNo),
                buildRangeSpecification(criteria.getPincode(), AddressDetails_.pincode),
                buildStringSpecification(criteria.getArea(), AddressDetails_.area),
                buildStringSpecification(criteria.getCity(), AddressDetails_.city),
                buildStringSpecification(criteria.getCountry(), AddressDetails_.country),
                buildStringSpecification(criteria.getLandmark(), AddressDetails_.landmark),
                buildRangeSpecification(criteria.getLatitude(), AddressDetails_.latitude),
                buildRangeSpecification(criteria.getLongitude(), AddressDetails_.longitude),
                buildSpecification(criteria.getIsActive(), AddressDetails_.isActive),
                buildRangeSpecification(criteria.getCreatedAt(), AddressDetails_.createdAt),
                buildRangeSpecification(criteria.getUpdatedAt(), AddressDetails_.updatedAt),
                buildStringSpecification(criteria.getCreatedBy(), AddressDetails_.createdBy),
                buildStringSpecification(criteria.getUpdatedBy(), AddressDetails_.updatedBy)
            );
        }
        return specification;
    }
}
