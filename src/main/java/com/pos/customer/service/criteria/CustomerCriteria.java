package com.pos.customer.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.pos.customer.domain.Customer} entity. This class is used
 * in {@link com.pos.customer.web.rest.CustomerResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /customers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomerCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter fname;

    private StringFilter lname;

    private StringFilter mobileNo;

    private StringFilter emailId;

    private StringFilter rposCustomerId;

    private BooleanFilter isActive;

    private InstantFilter createdAt;

    private InstantFilter updatedAt;

    private StringFilter createdBy;

    private StringFilter updatedBy;

    private Boolean distinct;

    public CustomerCriteria() {}

    public CustomerCriteria(CustomerCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.fname = other.optionalFname().map(StringFilter::copy).orElse(null);
        this.lname = other.optionalLname().map(StringFilter::copy).orElse(null);
        this.mobileNo = other.optionalMobileNo().map(StringFilter::copy).orElse(null);
        this.emailId = other.optionalEmailId().map(StringFilter::copy).orElse(null);
        this.rposCustomerId = other.optionalRposCustomerId().map(StringFilter::copy).orElse(null);
        this.isActive = other.optionalIsActive().map(BooleanFilter::copy).orElse(null);
        this.createdAt = other.optionalCreatedAt().map(InstantFilter::copy).orElse(null);
        this.updatedAt = other.optionalUpdatedAt().map(InstantFilter::copy).orElse(null);
        this.createdBy = other.optionalCreatedBy().map(StringFilter::copy).orElse(null);
        this.updatedBy = other.optionalUpdatedBy().map(StringFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public CustomerCriteria copy() {
        return new CustomerCriteria(this);
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

    public StringFilter getFname() {
        return fname;
    }

    public Optional<StringFilter> optionalFname() {
        return Optional.ofNullable(fname);
    }

    public StringFilter fname() {
        if (fname == null) {
            setFname(new StringFilter());
        }
        return fname;
    }

    public void setFname(StringFilter fname) {
        this.fname = fname;
    }

    public StringFilter getLname() {
        return lname;
    }

    public Optional<StringFilter> optionalLname() {
        return Optional.ofNullable(lname);
    }

    public StringFilter lname() {
        if (lname == null) {
            setLname(new StringFilter());
        }
        return lname;
    }

    public void setLname(StringFilter lname) {
        this.lname = lname;
    }

    public StringFilter getMobileNo() {
        return mobileNo;
    }

    public Optional<StringFilter> optionalMobileNo() {
        return Optional.ofNullable(mobileNo);
    }

    public StringFilter mobileNo() {
        if (mobileNo == null) {
            setMobileNo(new StringFilter());
        }
        return mobileNo;
    }

    public void setMobileNo(StringFilter mobileNo) {
        this.mobileNo = mobileNo;
    }

    public StringFilter getEmailId() {
        return emailId;
    }

    public Optional<StringFilter> optionalEmailId() {
        return Optional.ofNullable(emailId);
    }

    public StringFilter emailId() {
        if (emailId == null) {
            setEmailId(new StringFilter());
        }
        return emailId;
    }

    public void setEmailId(StringFilter emailId) {
        this.emailId = emailId;
    }

    public StringFilter getRposCustomerId() {
        return rposCustomerId;
    }

    public Optional<StringFilter> optionalRposCustomerId() {
        return Optional.ofNullable(rposCustomerId);
    }

    public StringFilter rposCustomerId() {
        if (rposCustomerId == null) {
            setRposCustomerId(new StringFilter());
        }
        return rposCustomerId;
    }

    public void setRposCustomerId(StringFilter rposCustomerId) {
        this.rposCustomerId = rposCustomerId;
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
        final CustomerCriteria that = (CustomerCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(fname, that.fname) &&
            Objects.equals(lname, that.lname) &&
            Objects.equals(mobileNo, that.mobileNo) &&
            Objects.equals(emailId, that.emailId) &&
            Objects.equals(rposCustomerId, that.rposCustomerId) &&
            Objects.equals(isActive, that.isActive) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(updatedAt, that.updatedAt) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(updatedBy, that.updatedBy) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            fname,
            lname,
            mobileNo,
            emailId,
            rposCustomerId,
            isActive,
            createdAt,
            updatedAt,
            createdBy,
            updatedBy,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalFname().map(f -> "fname=" + f + ", ").orElse("") +
            optionalLname().map(f -> "lname=" + f + ", ").orElse("") +
            optionalMobileNo().map(f -> "mobileNo=" + f + ", ").orElse("") +
            optionalEmailId().map(f -> "emailId=" + f + ", ").orElse("") +
            optionalRposCustomerId().map(f -> "rposCustomerId=" + f + ", ").orElse("") +
            optionalIsActive().map(f -> "isActive=" + f + ", ").orElse("") +
            optionalCreatedAt().map(f -> "createdAt=" + f + ", ").orElse("") +
            optionalUpdatedAt().map(f -> "updatedAt=" + f + ", ").orElse("") +
            optionalCreatedBy().map(f -> "createdBy=" + f + ", ").orElse("") +
            optionalUpdatedBy().map(f -> "updatedBy=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
