package com.pos.customer.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class CustomerCriteriaTest {

    @Test
    void newCustomerCriteriaHasAllFiltersNullTest() {
        var customerCriteria = new CustomerCriteria();
        assertThat(customerCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void customerCriteriaFluentMethodsCreatesFiltersTest() {
        var customerCriteria = new CustomerCriteria();

        setAllFilters(customerCriteria);

        assertThat(customerCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void customerCriteriaCopyCreatesNullFilterTest() {
        var customerCriteria = new CustomerCriteria();
        var copy = customerCriteria.copy();

        assertThat(customerCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(customerCriteria)
        );
    }

    @Test
    void customerCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var customerCriteria = new CustomerCriteria();
        setAllFilters(customerCriteria);

        var copy = customerCriteria.copy();

        assertThat(customerCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(customerCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var customerCriteria = new CustomerCriteria();

        assertThat(customerCriteria).hasToString("CustomerCriteria{}");
    }

    private static void setAllFilters(CustomerCriteria customerCriteria) {
        customerCriteria.id();
        customerCriteria.fname();
        customerCriteria.lname();
        customerCriteria.mobileNo();
        customerCriteria.emailId();
        customerCriteria.rposCustomerId();
        customerCriteria.isActive();
        customerCriteria.createdAt();
        customerCriteria.updatedAt();
        customerCriteria.createdBy();
        customerCriteria.updatedBy();
        customerCriteria.distinct();
    }

    private static Condition<CustomerCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getFname()) &&
                condition.apply(criteria.getLname()) &&
                condition.apply(criteria.getMobileNo()) &&
                condition.apply(criteria.getEmailId()) &&
                condition.apply(criteria.getRposCustomerId()) &&
                condition.apply(criteria.getIsActive()) &&
                condition.apply(criteria.getCreatedAt()) &&
                condition.apply(criteria.getUpdatedAt()) &&
                condition.apply(criteria.getCreatedBy()) &&
                condition.apply(criteria.getUpdatedBy()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<CustomerCriteria> copyFiltersAre(CustomerCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getFname(), copy.getFname()) &&
                condition.apply(criteria.getLname(), copy.getLname()) &&
                condition.apply(criteria.getMobileNo(), copy.getMobileNo()) &&
                condition.apply(criteria.getEmailId(), copy.getEmailId()) &&
                condition.apply(criteria.getRposCustomerId(), copy.getRposCustomerId()) &&
                condition.apply(criteria.getIsActive(), copy.getIsActive()) &&
                condition.apply(criteria.getCreatedAt(), copy.getCreatedAt()) &&
                condition.apply(criteria.getUpdatedAt(), copy.getUpdatedAt()) &&
                condition.apply(criteria.getCreatedBy(), copy.getCreatedBy()) &&
                condition.apply(criteria.getUpdatedBy(), copy.getUpdatedBy()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
