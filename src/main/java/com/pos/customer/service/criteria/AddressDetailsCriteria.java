package com.pos.customer.service.criteria;

import com.pos.customer.domain.enumeration.AddressType;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link com.pos.customer.domain.AddressDetails} entity. This class is used
 * in {@link com.pos.customer.web.rest.AddressDetailsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /address-details?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AddressDetailsCriteria implements Serializable, Criteria {

    /**
     * Class for filtering AddressType
     */
    public static class AddressTypeFilter extends Filter<AddressType> {

        public AddressTypeFilter() {}

        public AddressTypeFilter(AddressTypeFilter filter) {
            super(filter);
        }

        @Override
        public AddressTypeFilter copy() {
            return new AddressTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private AddressTypeFilter addressDetailType;

    private StringFilter mobileNo;

    private StringFilter name;

    private StringFilter label;

    private StringFilter addressLine1;

    private StringFilter addressLine2;

    private StringFilter addressLine3;

    private StringFilter floorNo;

    private LongFilter pincode;

    private StringFilter area;

    private StringFilter city;

    private StringFilter country;

    private StringFilter landmark;

    private FloatFilter latitude;

    private FloatFilter longitude;

    private BooleanFilter isActive;

    private InstantFilter createdAt;

    private InstantFilter updatedAt;

    private StringFilter createdBy;

    private StringFilter updatedBy;

    private LongFilter customerId;

    private Boolean distinct;

    public AddressDetailsCriteria() {}

    public AddressDetailsCriteria(AddressDetailsCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.addressDetailType = other.optionalAddressDetailType().map(AddressTypeFilter::copy).orElse(null);
        this.mobileNo = other.optionalMobileNo().map(StringFilter::copy).orElse(null);
        this.name = other.optionalName().map(StringFilter::copy).orElse(null);
        this.label = other.optionalLabel().map(StringFilter::copy).orElse(null);
        this.addressLine1 = other.optionalAddressLine1().map(StringFilter::copy).orElse(null);
        this.addressLine2 = other.optionalAddressLine2().map(StringFilter::copy).orElse(null);
        this.addressLine3 = other.optionalAddressLine3().map(StringFilter::copy).orElse(null);
        this.floorNo = other.optionalFloorNo().map(StringFilter::copy).orElse(null);
        this.pincode = other.optionalPincode().map(LongFilter::copy).orElse(null);
        this.area = other.optionalArea().map(StringFilter::copy).orElse(null);
        this.city = other.optionalCity().map(StringFilter::copy).orElse(null);
        this.country = other.optionalCountry().map(StringFilter::copy).orElse(null);
        this.landmark = other.optionalLandmark().map(StringFilter::copy).orElse(null);
        this.latitude = other.optionalLatitude().map(FloatFilter::copy).orElse(null);
        this.longitude = other.optionalLongitude().map(FloatFilter::copy).orElse(null);
        this.isActive = other.optionalIsActive().map(BooleanFilter::copy).orElse(null);
        this.createdAt = other.optionalCreatedAt().map(InstantFilter::copy).orElse(null);
        this.updatedAt = other.optionalUpdatedAt().map(InstantFilter::copy).orElse(null);
        this.createdBy = other.optionalCreatedBy().map(StringFilter::copy).orElse(null);
        this.updatedBy = other.optionalUpdatedBy().map(StringFilter::copy).orElse(null);
        this.customerId = other.optionalCustomerId().map(LongFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public AddressDetailsCriteria copy() {
        return new AddressDetailsCriteria(this);
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

    public AddressTypeFilter getAddressDetailType() {
        return addressDetailType;
    }

    public Optional<AddressTypeFilter> optionalAddressDetailType() {
        return Optional.ofNullable(addressDetailType);
    }

    public AddressTypeFilter addressDetailType() {
        if (addressDetailType == null) {
            setAddressDetailType(new AddressTypeFilter());
        }
        return addressDetailType;
    }

    public void setAddressDetailType(AddressTypeFilter addressDetailType) {
        this.addressDetailType = addressDetailType;
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

    public StringFilter getName() {
        return name;
    }

    public Optional<StringFilter> optionalName() {
        return Optional.ofNullable(name);
    }

    public StringFilter name() {
        if (name == null) {
            setName(new StringFilter());
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getLabel() {
        return label;
    }

    public Optional<StringFilter> optionalLabel() {
        return Optional.ofNullable(label);
    }

    public StringFilter label() {
        if (label == null) {
            setLabel(new StringFilter());
        }
        return label;
    }

    public void setLabel(StringFilter label) {
        this.label = label;
    }

    public StringFilter getAddressLine1() {
        return addressLine1;
    }

    public Optional<StringFilter> optionalAddressLine1() {
        return Optional.ofNullable(addressLine1);
    }

    public StringFilter addressLine1() {
        if (addressLine1 == null) {
            setAddressLine1(new StringFilter());
        }
        return addressLine1;
    }

    public void setAddressLine1(StringFilter addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public StringFilter getAddressLine2() {
        return addressLine2;
    }

    public Optional<StringFilter> optionalAddressLine2() {
        return Optional.ofNullable(addressLine2);
    }

    public StringFilter addressLine2() {
        if (addressLine2 == null) {
            setAddressLine2(new StringFilter());
        }
        return addressLine2;
    }

    public void setAddressLine2(StringFilter addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public StringFilter getAddressLine3() {
        return addressLine3;
    }

    public Optional<StringFilter> optionalAddressLine3() {
        return Optional.ofNullable(addressLine3);
    }

    public StringFilter addressLine3() {
        if (addressLine3 == null) {
            setAddressLine3(new StringFilter());
        }
        return addressLine3;
    }

    public void setAddressLine3(StringFilter addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public StringFilter getFloorNo() {
        return floorNo;
    }

    public Optional<StringFilter> optionalFloorNo() {
        return Optional.ofNullable(floorNo);
    }

    public StringFilter floorNo() {
        if (floorNo == null) {
            setFloorNo(new StringFilter());
        }
        return floorNo;
    }

    public void setFloorNo(StringFilter floorNo) {
        this.floorNo = floorNo;
    }

    public LongFilter getPincode() {
        return pincode;
    }

    public Optional<LongFilter> optionalPincode() {
        return Optional.ofNullable(pincode);
    }

    public LongFilter pincode() {
        if (pincode == null) {
            setPincode(new LongFilter());
        }
        return pincode;
    }

    public void setPincode(LongFilter pincode) {
        this.pincode = pincode;
    }

    public StringFilter getArea() {
        return area;
    }

    public Optional<StringFilter> optionalArea() {
        return Optional.ofNullable(area);
    }

    public StringFilter area() {
        if (area == null) {
            setArea(new StringFilter());
        }
        return area;
    }

    public void setArea(StringFilter area) {
        this.area = area;
    }

    public StringFilter getCity() {
        return city;
    }

    public Optional<StringFilter> optionalCity() {
        return Optional.ofNullable(city);
    }

    public StringFilter city() {
        if (city == null) {
            setCity(new StringFilter());
        }
        return city;
    }

    public void setCity(StringFilter city) {
        this.city = city;
    }

    public StringFilter getCountry() {
        return country;
    }

    public Optional<StringFilter> optionalCountry() {
        return Optional.ofNullable(country);
    }

    public StringFilter country() {
        if (country == null) {
            setCountry(new StringFilter());
        }
        return country;
    }

    public void setCountry(StringFilter country) {
        this.country = country;
    }

    public StringFilter getLandmark() {
        return landmark;
    }

    public Optional<StringFilter> optionalLandmark() {
        return Optional.ofNullable(landmark);
    }

    public StringFilter landmark() {
        if (landmark == null) {
            setLandmark(new StringFilter());
        }
        return landmark;
    }

    public void setLandmark(StringFilter landmark) {
        this.landmark = landmark;
    }

    public FloatFilter getLatitude() {
        return latitude;
    }

    public Optional<FloatFilter> optionalLatitude() {
        return Optional.ofNullable(latitude);
    }

    public FloatFilter latitude() {
        if (latitude == null) {
            setLatitude(new FloatFilter());
        }
        return latitude;
    }

    public void setLatitude(FloatFilter latitude) {
        this.latitude = latitude;
    }

    public FloatFilter getLongitude() {
        return longitude;
    }

    public Optional<FloatFilter> optionalLongitude() {
        return Optional.ofNullable(longitude);
    }

    public FloatFilter longitude() {
        if (longitude == null) {
            setLongitude(new FloatFilter());
        }
        return longitude;
    }

    public void setLongitude(FloatFilter longitude) {
        this.longitude = longitude;
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
        final AddressDetailsCriteria that = (AddressDetailsCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(addressDetailType, that.addressDetailType) &&
            Objects.equals(mobileNo, that.mobileNo) &&
            Objects.equals(name, that.name) &&
            Objects.equals(label, that.label) &&
            Objects.equals(addressLine1, that.addressLine1) &&
            Objects.equals(addressLine2, that.addressLine2) &&
            Objects.equals(addressLine3, that.addressLine3) &&
            Objects.equals(floorNo, that.floorNo) &&
            Objects.equals(pincode, that.pincode) &&
            Objects.equals(area, that.area) &&
            Objects.equals(city, that.city) &&
            Objects.equals(country, that.country) &&
            Objects.equals(landmark, that.landmark) &&
            Objects.equals(latitude, that.latitude) &&
            Objects.equals(longitude, that.longitude) &&
            Objects.equals(isActive, that.isActive) &&
            Objects.equals(createdAt, that.createdAt) &&
            Objects.equals(updatedAt, that.updatedAt) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(updatedBy, that.updatedBy) &&
            Objects.equals(customerId, that.customerId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            addressDetailType,
            mobileNo,
            name,
            label,
            addressLine1,
            addressLine2,
            addressLine3,
            floorNo,
            pincode,
            area,
            city,
            country,
            landmark,
            latitude,
            longitude,
            isActive,
            createdAt,
            updatedAt,
            createdBy,
            updatedBy,
            customerId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddressDetailsCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalAddressDetailType().map(f -> "addressDetailType=" + f + ", ").orElse("") +
            optionalMobileNo().map(f -> "mobileNo=" + f + ", ").orElse("") +
            optionalName().map(f -> "name=" + f + ", ").orElse("") +
            optionalLabel().map(f -> "label=" + f + ", ").orElse("") +
            optionalAddressLine1().map(f -> "addressLine1=" + f + ", ").orElse("") +
            optionalAddressLine2().map(f -> "addressLine2=" + f + ", ").orElse("") +
            optionalAddressLine3().map(f -> "addressLine3=" + f + ", ").orElse("") +
            optionalFloorNo().map(f -> "floorNo=" + f + ", ").orElse("") +
            optionalPincode().map(f -> "pincode=" + f + ", ").orElse("") +
            optionalArea().map(f -> "area=" + f + ", ").orElse("") +
            optionalCity().map(f -> "city=" + f + ", ").orElse("") +
            optionalCountry().map(f -> "country=" + f + ", ").orElse("") +
            optionalLandmark().map(f -> "landmark=" + f + ", ").orElse("") +
            optionalLatitude().map(f -> "latitude=" + f + ", ").orElse("") +
            optionalLongitude().map(f -> "longitude=" + f + ", ").orElse("") +
            optionalIsActive().map(f -> "isActive=" + f + ", ").orElse("") +
            optionalCreatedAt().map(f -> "createdAt=" + f + ", ").orElse("") +
            optionalUpdatedAt().map(f -> "updatedAt=" + f + ", ").orElse("") +
            optionalCreatedBy().map(f -> "createdBy=" + f + ", ").orElse("") +
            optionalUpdatedBy().map(f -> "updatedBy=" + f + ", ").orElse("") +
            optionalCustomerId().map(f -> "customerId=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
