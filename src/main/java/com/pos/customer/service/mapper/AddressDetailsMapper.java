package com.pos.customer.service.mapper;

import com.pos.customer.domain.AddressDetails;
import com.pos.customer.service.dto.AddressDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AddressDetails} and its DTO {@link AddressDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface AddressDetailsMapper extends EntityMapper<AddressDetailsDTO, AddressDetails> {}
