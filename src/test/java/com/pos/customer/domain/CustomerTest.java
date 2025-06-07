package com.pos.customer.domain;

import static com.pos.customer.domain.AddressDetailsTestSamples.*;
import static com.pos.customer.domain.CustomerTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.pos.customer.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CustomerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Customer.class);
        Customer customer1 = getCustomerSample1();
        Customer customer2 = new Customer();
        assertThat(customer1).isNotEqualTo(customer2);

        customer2.setId(customer1.getId());
        assertThat(customer1).isEqualTo(customer2);

        customer2 = getCustomerSample2();
        assertThat(customer1).isNotEqualTo(customer2);
    }

    @Test
    void addressDetailsTest() {
        Customer customer = getCustomerRandomSampleGenerator();
        AddressDetails addressDetailsBack = getAddressDetailsRandomSampleGenerator();

        customer.addAddressDetails(addressDetailsBack);
        assertThat(customer.getAddressDetails()).containsOnly(addressDetailsBack);
        assertThat(addressDetailsBack.getCustomer()).isEqualTo(customer);

        customer.removeAddressDetails(addressDetailsBack);
        assertThat(customer.getAddressDetails()).doesNotContain(addressDetailsBack);
        assertThat(addressDetailsBack.getCustomer()).isNull();

        customer.addressDetails(new HashSet<>(Set.of(addressDetailsBack)));
        assertThat(customer.getAddressDetails()).containsOnly(addressDetailsBack);
        assertThat(addressDetailsBack.getCustomer()).isEqualTo(customer);

        customer.setAddressDetails(new HashSet<>());
        assertThat(customer.getAddressDetails()).doesNotContain(addressDetailsBack);
        assertThat(addressDetailsBack.getCustomer()).isNull();
    }
}
