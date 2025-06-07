package com.pos.customer.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class OrderCriteriaTest {

    @Test
    void newOrderCriteriaHasAllFiltersNullTest() {
        var orderCriteria = new OrderCriteria();
        assertThat(orderCriteria).is(criteriaFiltersAre(Objects::isNull));
    }

    @Test
    void orderCriteriaFluentMethodsCreatesFiltersTest() {
        var orderCriteria = new OrderCriteria();

        setAllFilters(orderCriteria);

        assertThat(orderCriteria).is(criteriaFiltersAre(Objects::nonNull));
    }

    @Test
    void orderCriteriaCopyCreatesNullFilterTest() {
        var orderCriteria = new OrderCriteria();
        var copy = orderCriteria.copy();

        assertThat(orderCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::isNull)),
            criteria -> assertThat(criteria).isEqualTo(orderCriteria)
        );
    }

    @Test
    void orderCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var orderCriteria = new OrderCriteria();
        setAllFilters(orderCriteria);

        var copy = orderCriteria.copy();

        assertThat(orderCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(Objects::nonNull)),
            criteria -> assertThat(criteria).isEqualTo(orderCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var orderCriteria = new OrderCriteria();

        assertThat(orderCriteria).hasToString("OrderCriteria{}");
    }

    private static void setAllFilters(OrderCriteria orderCriteria) {
        orderCriteria.id();
        orderCriteria.sourceType();
        orderCriteria.isActive();
        orderCriteria.createdAt();
        orderCriteria.updatedAt();
        orderCriteria.createdBy();
        orderCriteria.updatedBy();
        orderCriteria.selectedAddressId();
        orderCriteria.customerId();
        orderCriteria.distinct();
    }

    private static Condition<OrderCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getSourceType()) &&
                condition.apply(criteria.getIsActive()) &&
                condition.apply(criteria.getCreatedAt()) &&
                condition.apply(criteria.getUpdatedAt()) &&
                condition.apply(criteria.getCreatedBy()) &&
                condition.apply(criteria.getUpdatedBy()) &&
                condition.apply(criteria.getSelectedAddressId()) &&
                condition.apply(criteria.getCustomerId()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<OrderCriteria> copyFiltersAre(OrderCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getSourceType(), copy.getSourceType()) &&
                condition.apply(criteria.getIsActive(), copy.getIsActive()) &&
                condition.apply(criteria.getCreatedAt(), copy.getCreatedAt()) &&
                condition.apply(criteria.getUpdatedAt(), copy.getUpdatedAt()) &&
                condition.apply(criteria.getCreatedBy(), copy.getCreatedBy()) &&
                condition.apply(criteria.getUpdatedBy(), copy.getUpdatedBy()) &&
                condition.apply(criteria.getSelectedAddressId(), copy.getSelectedAddressId()) &&
                condition.apply(criteria.getCustomerId(), copy.getCustomerId()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
