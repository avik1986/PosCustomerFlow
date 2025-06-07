package com.pos.customer.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class AddressDetailsCriteriaTest {

    @Test
    void newAddressDetailsCriteriaHasAllFiltersNullTest() {
        var addressDetailsCriteria = new AddressDetailsCriteria();
        assertThat(addressDetailsCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void addressDetailsCriteriaFluentMethodsCreatesFiltersTest() {
        var addressDetailsCriteria = new AddressDetailsCriteria();

        setAllFilters(addressDetailsCriteria);

        assertThat(addressDetailsCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void addressDetailsCriteriaCopyCreatesNullFilterTest() {
        var addressDetailsCriteria = new AddressDetailsCriteria();
        var copy = addressDetailsCriteria.copy();

        assertThat(addressDetailsCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(addressDetailsCriteria)
        );
    }

    @Test
    void addressDetailsCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var addressDetailsCriteria = new AddressDetailsCriteria();
        setAllFilters(addressDetailsCriteria);

        var copy = addressDetailsCriteria.copy();

        assertThat(addressDetailsCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(addressDetailsCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var addressDetailsCriteria = new AddressDetailsCriteria();

        assertThat(addressDetailsCriteria).hasToString("AddressDetailsCriteria{}");
    }

    private static void setAllFilters(AddressDetailsCriteria addressDetailsCriteria) {
        addressDetailsCriteria.id();
        addressDetailsCriteria.addressDetailType();
        addressDetailsCriteria.mobileNo();
        addressDetailsCriteria.name();
        addressDetailsCriteria.label();
        addressDetailsCriteria.addressLine1();
        addressDetailsCriteria.addressLine2();
        addressDetailsCriteria.addressLine3();
        addressDetailsCriteria.floorNo();
        addressDetailsCriteria.pincode();
        addressDetailsCriteria.area();
        addressDetailsCriteria.city();
        addressDetailsCriteria.country();
        addressDetailsCriteria.landmark();
        addressDetailsCriteria.latitude();
        addressDetailsCriteria.longitude();
        addressDetailsCriteria.isActive();
        addressDetailsCriteria.createdAt();
        addressDetailsCriteria.updatedAt();
        addressDetailsCriteria.createdBy();
        addressDetailsCriteria.updatedBy();
        addressDetailsCriteria.customerId();
        addressDetailsCriteria.distinct();
    }

    private static Condition<AddressDetailsCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getAddressDetailType()) &&
                condition.apply(criteria.getMobileNo()) &&
                condition.apply(criteria.getName()) &&
                condition.apply(criteria.getLabel()) &&
                condition.apply(criteria.getAddressLine1()) &&
                condition.apply(criteria.getAddressLine2()) &&
                condition.apply(criteria.getAddressLine3()) &&
                condition.apply(criteria.getFloorNo()) &&
                condition.apply(criteria.getPincode()) &&
                condition.apply(criteria.getArea()) &&
                condition.apply(criteria.getCity()) &&
                condition.apply(criteria.getCountry()) &&
                condition.apply(criteria.getLandmark()) &&
                condition.apply(criteria.getLatitude()) &&
                condition.apply(criteria.getLongitude()) &&
                condition.apply(criteria.getIsActive()) &&
                condition.apply(criteria.getCreatedAt()) &&
                condition.apply(criteria.getUpdatedAt()) &&
                condition.apply(criteria.getCreatedBy()) &&
                condition.apply(criteria.getUpdatedBy()) &&
                condition.apply(criteria.getCustomerId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<AddressDetailsCriteria> copyFiltersAre(
        AddressDetailsCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getAddressDetailType(), copy.getAddressDetailType()) &&
                condition.apply(criteria.getMobileNo(), copy.getMobileNo()) &&
                condition.apply(criteria.getName(), copy.getName()) &&
                condition.apply(criteria.getLabel(), copy.getLabel()) &&
                condition.apply(criteria.getAddressLine1(), copy.getAddressLine1()) &&
                condition.apply(criteria.getAddressLine2(), copy.getAddressLine2()) &&
                condition.apply(criteria.getAddressLine3(), copy.getAddressLine3()) &&
                condition.apply(criteria.getFloorNo(), copy.getFloorNo()) &&
                condition.apply(criteria.getPincode(), copy.getPincode()) &&
                condition.apply(criteria.getArea(), copy.getArea()) &&
                condition.apply(criteria.getCity(), copy.getCity()) &&
                condition.apply(criteria.getCountry(), copy.getCountry()) &&
                condition.apply(criteria.getLandmark(), copy.getLandmark()) &&
                condition.apply(criteria.getLatitude(), copy.getLatitude()) &&
                condition.apply(criteria.getLongitude(), copy.getLongitude()) &&
                condition.apply(criteria.getIsActive(), copy.getIsActive()) &&
                condition.apply(criteria.getCreatedAt(), copy.getCreatedAt()) &&
                condition.apply(criteria.getUpdatedAt(), copy.getUpdatedAt()) &&
                condition.apply(criteria.getCreatedBy(), copy.getCreatedBy()) &&
                condition.apply(criteria.getUpdatedBy(), copy.getUpdatedBy()) &&
                condition.apply(criteria.getCustomerId(), copy.getCustomerId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
