package com.pos.customer.repository;

import com.pos.customer.domain.AddressDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AddressDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AddressDetailsRepository extends JpaRepository<AddressDetails, Long>, JpaSpecificationExecutor<AddressDetails> {}
