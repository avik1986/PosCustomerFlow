package com.pos.customer.service.criteria;

import com.pos.customer.domain.enumeration.SourceType;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.pos.customer.domain.Order} entity. This class is used
 * in {@link com.pos.customer.web.rest.OrderResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /orders?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderCriteria implements Serializable, Criteria {

    /**
     * Class for filtering SourceType
     */
    public static class SourceTypeFilter extends Filter<SourceType> {

        public SourceTypeFilter() {}

        public SourceTypeFilter(SourceTypeFilter filter) {
            super(filter);
        }

        @Override
        public SourceTypeFilter copy() {
            return new SourceTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private SourceTypeFilter sourceType;

    private BooleanFilter isActive;

    private InstantFilter createdAt;

    private InstantFilter updatedAt;

    private StringFilter createdBy;

    private StringFilter updatedBy;

    private LongFilter selectedAddressId;

    private LongFilter customerId;

    private Boolean distinct;

    public OrderCriteria() {}

    public OrderCriteria(OrderCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.sourceType = other.optionalSourceType().map(SourceTypeFilter::copy).orElse(null);
        this.isActive = other.optionalIsActive().map(BooleanFilter::copy).orElse(null);
        this.createdAt = other.optionalCreatedAt().map(InstantFilter::copy).orElse(null);
        this.updatedAt = other.optionalUpdatedAt().map(InstantFilter::copy).orElse(null);
        this.createdBy = other.optionalCreatedBy().map(StringFilter::copy).orElse(null);
        this.updatedBy = other.optionalUpdatedBy().map(StringFilter::copy).orElse(null);
        this.selectedAddressId = other.optionalSelectedAddressId().map(LongFilter::copy).orElse(null);
        this.customerId = other.optionalCustomerId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public OrderCriteria copy() {
        return new OrderCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public SourceTypeFilter getSourceType() {
        return sourceType;
    }

    public Optional<SourceTypeFilter> optionalSourceType() {
        return Optional.ofNullable(sourceType);
    }

    public SourceTypeFilter sourceType() {
        if (sourceType == null) {
            setSourceType(new SourceTypeFilter());
        }
        return sourceType;
    }

    public void setSourceType(SourceTypeFilter sourceType) {
        this.sourceType = sourceType;
    }

    public BooleanFilter getIsActive() {
        return isActive;
    }

    public Optional<BooleanFilter> optionalIsActive() {
        return Optional.ofNullable(isActive);
    }

    public BooleanFilter isActive() {
        if (isActive == null) {
            setIsActive(new BooleanFilter());
        }
        return isActive;
    }

    public void setIsActive(BooleanFilter isActive) {
        this.isActive = isActive;
    }

    public InstantFilter getCreatedAt() {
        return createdAt;
    }

    public Optional<InstantFilter> optionalCreatedAt() {
        return Optional.ofNullable(createdAt);
    }

    public InstantFilter createdAt() {
        if (createdAt == null) {
            setCreatedAt(new InstantFilter());
        }
        return createdAt;
    }

    public void setCreatedAt(InstantFilter createdAt) {
        this.createdAt = createdAt;
    }

    public InstantFilter getUpdatedAt() {
        return updatedAt;
    }

    public Optional<InstantFilter> optionalUpdatedAt() {
        return Optional.ofNullable(updatedAt);
    }

    public InstantFilter updatedAt() {
        if (updatedAt == null) {
            setUpdatedAt(new InstantFilter());
        }
        return updatedAt;
    }

    public void setUpdatedAt(InstantFilter updatedAt) {
        this.updatedAt = updatedAt;
    }

    public StringFilter getCreatedBy() {
        return createdBy;
    }

    public Optional<StringFilter> optionalCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    public StringFilter createdBy() {
        if (createdBy == null) {
            setCreatedBy(new StringFilter());
        }
        return createdBy;
    }

    public void setCreatedBy(StringFilter createdBy) {
        this.createdBy = createdBy;
    }

    public StringFilter getUpdatedBy() {
        return updatedBy;
    }

    public Optional<StringFilter> optionalUpdatedBy() {
        return Optional.ofNullable(updatedBy);
    }

    public StringFilter updatedBy() {
        if (updatedBy == null) {
            setUpdatedBy(new StringFilter());
        }
        return updatedBy;
    }

    public void setUpdatedBy(StringFilter updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LongFilter getSelectedAddressId() {
        return selectedAddressId;
    }

    public Optional<LongFilter> optionalSelectedAddressId() {
        return Optional.ofNullable(selectedAddressId);
    }

    public LongFilter selectedAddressId() {
        if (selectedAddressId == null) {
            setSelectedAddressId(new LongFilter());
        }
        return selectedAddressId;
    }

    public void setSelectedAddressId(LongFilter selectedAddressId) {
        this.selectedAddressId = selectedAddressId;
    }

    public LongFilter getCustomerId() {
        return customerId;
    }

    public Optional<LongFilter> optionalCustomerId() {
        return Optional.ofNullable(customerId);
    }

    public LongFilter customerId() {
        if (customerId == null) {
            setCustomerId(new LongFilter());
        }
        return customerId;
    }

    public void setCustomerId(LongFilter customerId) {
        this.customerId = customerId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OrderCriteria that = (OrderCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(sourceType, that.sourceType) &&
            Objects.equals(isActive, that.isActive) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(updatedAt, that.updatedAt) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(updatedBy, that.updatedBy) &&
            Objects.equals(selectedAddressId, that.selectedAddressId) &&
            Objects.equals(customerId, that.customerId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceType, isActive, createdAt, updatedAt, createdBy, updatedBy, selectedAddressId, customerId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalSourceType().map(f -> "sourceType=" + f + ", ").orElse("") +
            optionalIsActive().map(f -> "isActive=" + f + ", ").orElse("") +
            optionalCreatedAt().map(f -> "createdAt=" + f + ", ").orElse("") +
            optionalUpdatedAt().map(f -> "updatedAt=" + f + ", ").orElse("") +
            optionalCreatedBy().map(f -> "createdBy=" + f + ", ").orElse("") +
            optionalUpdatedBy().map(f -> "updatedBy=" + f + ", ").orElse("") +
            optionalSelectedAddressId().map(f -> "selectedAddressId=" + f + ", ").orElse("") +
            optionalCustomerId().map(f -> "customerId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
