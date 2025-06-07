package com.pos.customer.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AddressDetailsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AddressDetails getAddressDetailsSample1() {
        return new AddressDetails()
            .id(1L)
            .mobileNo("mobileNo1")
            .name("name1")
            .label("label1")
            .addressLine1("addressLine11")
            .addressLine2("addressLine21")
            .addressLine3("addressLine31")
            .floorNo("floorNo1")
            .pincode(1L)
            .area("area1")
            .city("city1")
            .country("country1")
            .landmark("landmark1")
            .createdBy("createdBy1")
            .updatedBy("updatedBy1");
    }

    public static AddressDetails getAddressDetailsSample2() {
        return new AddressDetails()
            .id(2L)
            .mobileNo("mobileNo2")
            .name("name2")
            .label("label2")
            .addressLine1("addressLine12")
            .addressLine2("addressLine22")
            .addressLine3("addressLine32")
            .floorNo("floorNo2")
            .pincode(2L)
            .area("area2")
            .city("city2")
            .country("country2")
            .landmark("landmark2")
            .createdBy("createdBy2")
            .updatedBy("updatedBy2");
    }

    public static AddressDetails getAddressDetailsRandomSampleGenerator() {
        return new AddressDetails()
            .id(longCount.incrementAndGet())
            .mobileNo(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .label(UUID.randomUUID().toString())
            .addressLine1(UUID.randomUUID().toString())
            .addressLine2(UUID.randomUUID().toString())
            .addressLine3(UUID.randomUUID().toString())
            .floorNo(UUID.randomUUID().toString())
            .pincode(longCount.incrementAndGet())
            .area(UUID.randomUUID().toString())
            .city(UUID.randomUUID().toString())
            .country(UUID.randomUUID().toString())
            .landmark(UUID.randomUUID().toString())
            .createdBy(UUID.randomUUID().toString())
            .updatedBy(UUID.randomUUID().toString());
    }
}
