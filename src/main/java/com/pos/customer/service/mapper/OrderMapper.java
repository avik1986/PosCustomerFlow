package com.pos.customer.service.mapper;

import com.pos.customer.domain.AddressDetails;
import com.pos.customer.domain.Customer;
import com.pos.customer.domain.Order;
import com.pos.customer.service.dto.AddressDetailsDTO;
import com.pos.customer.service.dto.CustomerDTO;
import com.pos.customer.service.dto.OrderDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Order} and its DTO {@link OrderDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderMapper extends EntityMapper<OrderDTO, Order> {
    @Mapping(target = "selectedAddress", source = "selectedAddress", qualifiedByName = "addressDetailsId")
    @Mapping(target = "customer", source = "customer", qualifiedByName = "customerId")
    OrderDTO toDto(Order s);

    @Named("addressDetailsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AddressDetailsDTO toDtoAddressDetailsId(AddressDetails addressDetails);

    @Named("customerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomerDTO toDtoCustomerId(Customer customer);
}
