package com.pos.customer.domain;

import static com.pos.customer.domain.AddressDetailsTestSamples.*;
import static com.pos.customer.domain.CustomerTestSamples.*;
import static com.pos.customer.domain.OrderTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.pos.customer.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Order.class);
        Order order1 = getOrderSample1();
        Order order2 = new Order();
        assertThat(order1).isNotEqualTo(order2);

        order2.setId(order1.getId());
        assertThat(order1).isEqualTo(order2);

        order2 = getOrderSample2();
        assertThat(order1).isNotEqualTo(order2);
    }

    @Test
    void selectedAddressTest() {
        Order order = getOrderRandomSampleGenerator();
        AddressDetails addressDetailsBack = getAddressDetailsRandomSampleGenerator();

        order.setSelectedAddress(addressDetailsBack);
        assertThat(order.getSelectedAddress()).isEqualTo(addressDetailsBack);

        order.selectedAddress(null);
        assertThat(order.getSelectedAddress()).isNull();
    }

    @Test
    void customerTest() {
        Order order = getOrderRandomSampleGenerator();
        Customer customerBack = getCustomerRandomSampleGenerator();

        order.setCustomer(customerBack);
        assertThat(order.getCustomer()).isEqualTo(customerBack);

        order.customer(null);
        assertThat(order.getCustomer()).isNull();
    }
}
