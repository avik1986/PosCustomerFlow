package com.pos.customer.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CustomerTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Customer getCustomerSample1() {
        return new Customer()
            .id(1L)
            .fname("fname1")
            .lname("lname1")
            .mobileNo("mobileNo1")
            .emailId("emailId1")
            .rposCustomerId("rposCustomerId1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static Customer getCustomerSample2() {
        return new Customer()
            .id(2L)
            .fname("fname2")
            .lname("lname2")
            .mobileNo("mobileNo2")
            .emailId("emailId2")
            .rposCustomerId("rposCustomerId2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static Customer getCustomerRandomSampleGenerator() {
        return new Customer()
            .id(longCount.incrementAndGet())
            .fname(UUID.randomUUID().toString())
            .lname(UUID.randomUUID().toString())
            .mobileNo(UUID.randomUUID().toString())
            .emailId(UUID.randomUUID().toString())
            .rposCustomerId(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
