package com.pos.customer.domain;

import static com.pos.customer.domain.AddressDetailsTestSamples.*;
import static com.pos.customer.domain.CustomerTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.pos.customer.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AddressDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AddressDetails.class);
        AddressDetails addressDetails1 = getAddressDetailsSample1();
        AddressDetails addressDetails2 = new AddressDetails();
        assertThat(addressDetails1).isNotEqualTo(addressDetails2);

        addressDetails2.setId(addressDetails1.getId());
        assertThat(addressDetails1).isEqualTo(addressDetails2);

        addressDetails2 = getAddressDetailsSample2();
        assertThat(addressDetails1).isNotEqualTo(addressDetails2);
    }

    @Test
    void customerTest() {
        AddressDetails addressDetails = getAddressDetailsRandomSampleGenerator();
        Customer customerBack = getCustomerRandomSampleGenerator();

        addressDetails.setCustomer(customerBack);
        assertThat(addressDetails.getCustomer()).isEqualTo(customerBack);

        addressDetails.customer(null);
        assertThat(addressDetails.getCustomer()).isNull();
    }
}
