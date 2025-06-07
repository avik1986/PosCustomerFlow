package com.pos.customer.service.mapper;

import com.pos.customer.domain.AddressDetails;
import com.pos.customer.domain.Customer;
import com.pos.customer.service.dto.AddressDetailsDTO;
import com.pos.customer.service.dto.CustomerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AddressDetails} and its DTO {@link AddressDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface AddressDetailsMapper extends EntityMapper<AddressDetailsDTO, AddressDetails> {
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerId")
    AddressDetailsDTO toDto(AddressDetails s);

    @Named("customerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomerDTO toDtoCustomerId(Customer customer);
}
