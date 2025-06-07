package com.pos.customer.domain;

import com.pos.customer.domain.enumeration.AddressType;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AddressDetails.
 */
@Entity
@Table(name = "address_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AddressDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_detail_type")
    private AddressType addressDetailType;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "name")
    private String name;

    @Column(name = "label")
    private String label;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "address_line_3")
    private String addressLine3;

    @Column(name = "floor_no")
    private String floorNo;

    @Column(name = "pincode")
    private Long pincode;

    @Column(name = "area")
    private String area;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "landmark")
    private String landmark;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AddressDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressType getAddressDetailType() {
        return this.addressDetailType;
    }

    public AddressDetails addressDetailType(AddressType addressDetailType) {
        this.setAddressDetailType(addressDetailType);
        return this;
    }

    public void setAddressDetailType(AddressType addressDetailType) {
        this.addressDetailType = addressDetailType;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public AddressDetails mobileNo(String mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getName() {
        return this.name;
    }

    public AddressDetails name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return this.label;
    }

    public AddressDetails label(String label) {
        this.setLabel(label);
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public AddressDetails addressLine1(String addressLine1) {
        this.setAddressLine1(addressLine1);
        return this;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public AddressDetails addressLine2(String addressLine2) {
        this.setAddressLine2(addressLine2);
        return this;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return this.addressLine3;
    }

    public AddressDetails addressLine3(String addressLine3) {
        this.setAddressLine3(addressLine3);
        return this;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getFloorNo() {
        return this.floorNo;
    }

    public AddressDetails floorNo(String floorNo) {
        this.setFloorNo(floorNo);
        return this;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public Long getPincode() {
        return this.pincode;
    }

    public AddressDetails pincode(Long pincode) {
        this.setPincode(pincode);
        return this;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getArea() {
        return this.area;
    }

    public AddressDetails area(String area) {
        this.setArea(area);
        return this;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return this.city;
    }

    public AddressDetails city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public AddressDetails country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLandmark() {
        return this.landmark;
    }

    public AddressDetails landmark(String landmark) {
        this.setLandmark(landmark);
        return this;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public Float getLatitude() {
        return this.latitude;
    }

    public AddressDetails latitude(Float latitude) {
        this.setLatitude(latitude);
        return this;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return this.longitude;
    }

    public AddressDetails longitude(Float longitude) {
        this.setLongitude(longitude);
        return this;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public AddressDetails isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public AddressDetails createdAt(Instant createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public AddressDetails updatedAt(Instant updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public AddressDetails createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public AddressDetails updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressDetails)) {
            return false;
        }
        return getId() != null && getId().equals(((AddressDetails) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AddressDetails{" +
            "id=" + getId() +
            ", addressDetailType='" + getAddressDetailType() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", name='" + getName() + "'" +
            ", label='" + getLabel() + "'" +
            ", addressLine1='" + getAddressLine1() + "'" +
            ", addressLine2='" + getAddressLine2() + "'" +
            ", addressLine3='" + getAddressLine3() + "'" +
            ", floorNo='" + getFloorNo() + "'" +
            ", pincode=" + getPincode() +
            ", area='" + getArea() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", landmark='" + getLandmark() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", isActive='" + getIsActive() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
