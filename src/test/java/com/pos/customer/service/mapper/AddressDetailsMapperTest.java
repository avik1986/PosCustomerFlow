package com.pos.customer.service.mapper;

import static com.pos.customer.domain.AddressDetailsAsserts.*;
import static com.pos.customer.domain.AddressDetailsTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddressDetailsMapperTest {

    private AddressDetailsMapper addressDetailsMapper;

    @BeforeEach
    void setUp() {
        addressDetailsMapper = new AddressDetailsMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getAddressDetailsSample1();
        var actual = addressDetailsMapper.toEntity(addressDetailsMapper.toDto(expected));
        assertAddressDetailsAllPropertiesEquals(expected, actual);
    }
}
