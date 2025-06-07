package com.pos.customer.web.rest;

import static com.pos.customer.domain.AddressDetailsAsserts.*;
import static com.pos.customer.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.customer.IntegrationTest;
import com.pos.customer.domain.AddressDetails;
import com.pos.customer.domain.Customer;
import com.pos.customer.domain.enumeration.AddressType;
import com.pos.customer.repository.AddressDetailsRepository;
import com.pos.customer.service.dto.AddressDetailsDTO;
import com.pos.customer.service.mapper.AddressDetailsMapper;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AddressDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AddressDetailsResourceIT {

    private static final AddressType DEFAULT_ADDRESS_DETAIL_TYPE = AddressType.Billing;
    private static final AddressType UPDATED_ADDRESS_DETAIL_TYPE = AddressType.Shipping;

    private static final String DEFAULT_MOBILE_NO = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_LINE_3 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_LINE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_FLOOR_NO = "AAAAAAAAAA";
    private static final String UPDATED_FLOOR_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_PINCODE = 1L;
    private static final Long UPDATED_PINCODE = 2L;
    private static final Long SMALLER_PINCODE = 1L - 1L;

    private static final String DEFAULT_AREA = "AAAAAAAAAA";
    private static final String UPDATED_AREA = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_LANDMARK = "AAAAAAAAAA";
    private static final String UPDATED_LANDMARK = "BBBBBBBBBB";

    private static final Float DEFAULT_LATITUDE = 1F;
    private static final Float UPDATED_LATITUDE = 2F;
    private static final Float SMALLER_LATITUDE = 1F - 1F;

    private static final Float DEFAULT_LONGITUDE = 1F;
    private static final Float UPDATED_LONGITUDE = 2F;
    private static final Float SMALLER_LONGITUDE = 1F - 1F;

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final Instant DEFAULT_CREATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATED_AT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATED_AT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/address-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AddressDetailsRepository addressDetailsRepository;

    @Autowired
    private AddressDetailsMapper addressDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAddressDetailsMockMvc;

    private AddressDetails addressDetails;

    private AddressDetails insertedAddressDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AddressDetails createEntity() {
        return new AddressDetails()
            .addressDetailType(DEFAULT_ADDRESS_DETAIL_TYPE)
            .mobileNo(DEFAULT_MOBILE_NO)
            .name(DEFAULT_NAME)
            .label(DEFAULT_LABEL)
            .addressLine1(DEFAULT_ADDRESS_LINE_1)
            .addressLine2(DEFAULT_ADDRESS_LINE_2)
            .addressLine3(DEFAULT_ADDRESS_LINE_3)
            .floorNo(DEFAULT_FLOOR_NO)
            .pincode(DEFAULT_PINCODE)
            .area(DEFAULT_AREA)
            .city(DEFAULT_CITY)
            .country(DEFAULT_COUNTRY)
            .landmark(DEFAULT_LANDMARK)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedBy(DEFAULT_UPDATED_BY);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AddressDetails createUpdatedEntity() {
        return new AddressDetails()
            .addressDetailType(UPDATED_ADDRESS_DETAIL_TYPE)
            .mobileNo(UPDATED_MOBILE_NO)
            .name(UPDATED_NAME)
            .label(UPDATED_LABEL)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .addressLine3(UPDATED_ADDRESS_LINE_3)
            .floorNo(UPDATED_FLOOR_NO)
            .pincode(UPDATED_PINCODE)
            .area(UPDATED_AREA)
            .city(UPDATED_CITY)
            .country(UPDATED_COUNTRY)
            .landmark(UPDATED_LANDMARK)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
    }

    @BeforeEach
    void initTest() {
        addressDetails = createEntity();
    }

    @AfterEach
    void cleanup() {
        if (insertedAddressDetails != null) {
            addressDetailsRepository.delete(insertedAddressDetails);
            insertedAddressDetails = null;
        }
    }

    @Test
    @Transactional
    void createAddressDetails() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the AddressDetails
        AddressDetailsDTO addressDetailsDTO = addressDetailsMapper.toDto(addressDetails);
        var returnedAddressDetailsDTO = om.readValue(
            restAddressDetailsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(addressDetailsDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            AddressDetailsDTO.class
        );

        // Validate the AddressDetails in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedAddressDetails = addressDetailsMapper.toEntity(returnedAddressDetailsDTO);
        assertAddressDetailsUpdatableFieldsEquals(returnedAddressDetails, getPersistedAddressDetails(returnedAddressDetails));

        insertedAddressDetails = returnedAddressDetails;
    }

    @Test
    @Transactional
    void createAddressDetailsWithExistingId() throws Exception {
        // Create the AddressDetails with an existing ID
        addressDetails.setId(1L);
        AddressDetailsDTO addressDetailsDTO = addressDetailsMapper.toDto(addressDetails);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddressDetailsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(addressDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AddressDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAddressDetails() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList
        restAddressDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(addressDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].addressDetailType").value(hasItem(DEFAULT_ADDRESS_DETAIL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL)))
            .andExpect(jsonPath("$.[*].addressLine1").value(hasItem(DEFAULT_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].addressLine2").value(hasItem(DEFAULT_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].addressLine3").value(hasItem(DEFAULT_ADDRESS_LINE_3)))
            .andExpect(jsonPath("$.[*].floorNo").value(hasItem(DEFAULT_FLOOR_NO)))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE.intValue())))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].landmark").value(hasItem(DEFAULT_LANDMARK)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)));
    }

    @Test
    @Transactional
    void getAddressDetails() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get the addressDetails
        restAddressDetailsMockMvc
            .perform(get(ENTITY_API_URL_ID, addressDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(addressDetails.getId().intValue()))
            .andExpect(jsonPath("$.addressDetailType").value(DEFAULT_ADDRESS_DETAIL_TYPE.toString()))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.label").value(DEFAULT_LABEL))
            .andExpect(jsonPath("$.addressLine1").value(DEFAULT_ADDRESS_LINE_1))
            .andExpect(jsonPath("$.addressLine2").value(DEFAULT_ADDRESS_LINE_2))
            .andExpect(jsonPath("$.addressLine3").value(DEFAULT_ADDRESS_LINE_3))
            .andExpect(jsonPath("$.floorNo").value(DEFAULT_FLOOR_NO))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE.intValue()))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.landmark").value(DEFAULT_LANDMARK))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY));
    }

    @Test
    @Transactional
    void getAddressDetailsByIdFiltering() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        Long id = addressDetails.getId();

        defaultAddressDetailsFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultAddressDetailsFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultAddressDetailsFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressDetailTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressDetailType equals to
        defaultAddressDetailsFiltering(
            "addressDetailType.equals=" + DEFAULT_ADDRESS_DETAIL_TYPE,
            "addressDetailType.equals=" + UPDATED_ADDRESS_DETAIL_TYPE
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressDetailTypeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressDetailType in
        defaultAddressDetailsFiltering(
            "addressDetailType.in=" + DEFAULT_ADDRESS_DETAIL_TYPE + "," + UPDATED_ADDRESS_DETAIL_TYPE,
            "addressDetailType.in=" + UPDATED_ADDRESS_DETAIL_TYPE
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressDetailTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressDetailType is not null
        defaultAddressDetailsFiltering("addressDetailType.specified=true", "addressDetailType.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByMobileNoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where mobileNo equals to
        defaultAddressDetailsFiltering("mobileNo.equals=" + DEFAULT_MOBILE_NO, "mobileNo.equals=" + UPDATED_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByMobileNoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where mobileNo in
        defaultAddressDetailsFiltering("mobileNo.in=" + DEFAULT_MOBILE_NO + "," + UPDATED_MOBILE_NO, "mobileNo.in=" + UPDATED_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByMobileNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where mobileNo is not null
        defaultAddressDetailsFiltering("mobileNo.specified=true", "mobileNo.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByMobileNoContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where mobileNo contains
        defaultAddressDetailsFiltering("mobileNo.contains=" + DEFAULT_MOBILE_NO, "mobileNo.contains=" + UPDATED_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByMobileNoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where mobileNo does not contain
        defaultAddressDetailsFiltering("mobileNo.doesNotContain=" + UPDATED_MOBILE_NO, "mobileNo.doesNotContain=" + DEFAULT_MOBILE_NO);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where name equals to
        defaultAddressDetailsFiltering("name.equals=" + DEFAULT_NAME, "name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where name in
        defaultAddressDetailsFiltering("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME, "name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where name is not null
        defaultAddressDetailsFiltering("name.specified=true", "name.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByNameContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where name contains
        defaultAddressDetailsFiltering("name.contains=" + DEFAULT_NAME, "name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where name does not contain
        defaultAddressDetailsFiltering("name.doesNotContain=" + UPDATED_NAME, "name.doesNotContain=" + DEFAULT_NAME);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLabelIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where label equals to
        defaultAddressDetailsFiltering("label.equals=" + DEFAULT_LABEL, "label.equals=" + UPDATED_LABEL);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLabelIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where label in
        defaultAddressDetailsFiltering("label.in=" + DEFAULT_LABEL + "," + UPDATED_LABEL, "label.in=" + UPDATED_LABEL);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLabelIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where label is not null
        defaultAddressDetailsFiltering("label.specified=true", "label.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLabelContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where label contains
        defaultAddressDetailsFiltering("label.contains=" + DEFAULT_LABEL, "label.contains=" + UPDATED_LABEL);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLabelNotContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where label does not contain
        defaultAddressDetailsFiltering("label.doesNotContain=" + UPDATED_LABEL, "label.doesNotContain=" + DEFAULT_LABEL);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine1IsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine1 equals to
        defaultAddressDetailsFiltering("addressLine1.equals=" + DEFAULT_ADDRESS_LINE_1, "addressLine1.equals=" + UPDATED_ADDRESS_LINE_1);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine1IsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine1 in
        defaultAddressDetailsFiltering(
            "addressLine1.in=" + DEFAULT_ADDRESS_LINE_1 + "," + UPDATED_ADDRESS_LINE_1,
            "addressLine1.in=" + UPDATED_ADDRESS_LINE_1
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine1IsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine1 is not null
        defaultAddressDetailsFiltering("addressLine1.specified=true", "addressLine1.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine1ContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine1 contains
        defaultAddressDetailsFiltering(
            "addressLine1.contains=" + DEFAULT_ADDRESS_LINE_1,
            "addressLine1.contains=" + UPDATED_ADDRESS_LINE_1
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine1NotContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine1 does not contain
        defaultAddressDetailsFiltering(
            "addressLine1.doesNotContain=" + UPDATED_ADDRESS_LINE_1,
            "addressLine1.doesNotContain=" + DEFAULT_ADDRESS_LINE_1
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine2IsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine2 equals to
        defaultAddressDetailsFiltering("addressLine2.equals=" + DEFAULT_ADDRESS_LINE_2, "addressLine2.equals=" + UPDATED_ADDRESS_LINE_2);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine2IsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine2 in
        defaultAddressDetailsFiltering(
            "addressLine2.in=" + DEFAULT_ADDRESS_LINE_2 + "," + UPDATED_ADDRESS_LINE_2,
            "addressLine2.in=" + UPDATED_ADDRESS_LINE_2
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine2IsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine2 is not null
        defaultAddressDetailsFiltering("addressLine2.specified=true", "addressLine2.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine2ContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine2 contains
        defaultAddressDetailsFiltering(
            "addressLine2.contains=" + DEFAULT_ADDRESS_LINE_2,
            "addressLine2.contains=" + UPDATED_ADDRESS_LINE_2
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine2NotContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine2 does not contain
        defaultAddressDetailsFiltering(
            "addressLine2.doesNotContain=" + UPDATED_ADDRESS_LINE_2,
            "addressLine2.doesNotContain=" + DEFAULT_ADDRESS_LINE_2
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine3IsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine3 equals to
        defaultAddressDetailsFiltering("addressLine3.equals=" + DEFAULT_ADDRESS_LINE_3, "addressLine3.equals=" + UPDATED_ADDRESS_LINE_3);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine3IsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine3 in
        defaultAddressDetailsFiltering(
            "addressLine3.in=" + DEFAULT_ADDRESS_LINE_3 + "," + UPDATED_ADDRESS_LINE_3,
            "addressLine3.in=" + UPDATED_ADDRESS_LINE_3
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine3IsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine3 is not null
        defaultAddressDetailsFiltering("addressLine3.specified=true", "addressLine3.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine3ContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine3 contains
        defaultAddressDetailsFiltering(
            "addressLine3.contains=" + DEFAULT_ADDRESS_LINE_3,
            "addressLine3.contains=" + UPDATED_ADDRESS_LINE_3
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAddressLine3NotContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where addressLine3 does not contain
        defaultAddressDetailsFiltering(
            "addressLine3.doesNotContain=" + UPDATED_ADDRESS_LINE_3,
            "addressLine3.doesNotContain=" + DEFAULT_ADDRESS_LINE_3
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByFloorNoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where floorNo equals to
        defaultAddressDetailsFiltering("floorNo.equals=" + DEFAULT_FLOOR_NO, "floorNo.equals=" + UPDATED_FLOOR_NO);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByFloorNoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where floorNo in
        defaultAddressDetailsFiltering("floorNo.in=" + DEFAULT_FLOOR_NO + "," + UPDATED_FLOOR_NO, "floorNo.in=" + UPDATED_FLOOR_NO);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByFloorNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where floorNo is not null
        defaultAddressDetailsFiltering("floorNo.specified=true", "floorNo.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByFloorNoContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where floorNo contains
        defaultAddressDetailsFiltering("floorNo.contains=" + DEFAULT_FLOOR_NO, "floorNo.contains=" + UPDATED_FLOOR_NO);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByFloorNoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where floorNo does not contain
        defaultAddressDetailsFiltering("floorNo.doesNotContain=" + UPDATED_FLOOR_NO, "floorNo.doesNotContain=" + DEFAULT_FLOOR_NO);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByPincodeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where pincode equals to
        defaultAddressDetailsFiltering("pincode.equals=" + DEFAULT_PINCODE, "pincode.equals=" + UPDATED_PINCODE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByPincodeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where pincode in
        defaultAddressDetailsFiltering("pincode.in=" + DEFAULT_PINCODE + "," + UPDATED_PINCODE, "pincode.in=" + UPDATED_PINCODE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByPincodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where pincode is not null
        defaultAddressDetailsFiltering("pincode.specified=true", "pincode.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByPincodeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where pincode is greater than or equal to
        defaultAddressDetailsFiltering("pincode.greaterThanOrEqual=" + DEFAULT_PINCODE, "pincode.greaterThanOrEqual=" + UPDATED_PINCODE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByPincodeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where pincode is less than or equal to
        defaultAddressDetailsFiltering("pincode.lessThanOrEqual=" + DEFAULT_PINCODE, "pincode.lessThanOrEqual=" + SMALLER_PINCODE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByPincodeIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where pincode is less than
        defaultAddressDetailsFiltering("pincode.lessThan=" + UPDATED_PINCODE, "pincode.lessThan=" + DEFAULT_PINCODE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByPincodeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where pincode is greater than
        defaultAddressDetailsFiltering("pincode.greaterThan=" + SMALLER_PINCODE, "pincode.greaterThan=" + DEFAULT_PINCODE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where area equals to
        defaultAddressDetailsFiltering("area.equals=" + DEFAULT_AREA, "area.equals=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAreaIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where area in
        defaultAddressDetailsFiltering("area.in=" + DEFAULT_AREA + "," + UPDATED_AREA, "area.in=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAreaIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where area is not null
        defaultAddressDetailsFiltering("area.specified=true", "area.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAreaContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where area contains
        defaultAddressDetailsFiltering("area.contains=" + DEFAULT_AREA, "area.contains=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByAreaNotContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where area does not contain
        defaultAddressDetailsFiltering("area.doesNotContain=" + UPDATED_AREA, "area.doesNotContain=" + DEFAULT_AREA);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCityIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where city equals to
        defaultAddressDetailsFiltering("city.equals=" + DEFAULT_CITY, "city.equals=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCityIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where city in
        defaultAddressDetailsFiltering("city.in=" + DEFAULT_CITY + "," + UPDATED_CITY, "city.in=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCityIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where city is not null
        defaultAddressDetailsFiltering("city.specified=true", "city.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCityContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where city contains
        defaultAddressDetailsFiltering("city.contains=" + DEFAULT_CITY, "city.contains=" + UPDATED_CITY);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCityNotContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where city does not contain
        defaultAddressDetailsFiltering("city.doesNotContain=" + UPDATED_CITY, "city.doesNotContain=" + DEFAULT_CITY);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCountryIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where country equals to
        defaultAddressDetailsFiltering("country.equals=" + DEFAULT_COUNTRY, "country.equals=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCountryIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where country in
        defaultAddressDetailsFiltering("country.in=" + DEFAULT_COUNTRY + "," + UPDATED_COUNTRY, "country.in=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCountryIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where country is not null
        defaultAddressDetailsFiltering("country.specified=true", "country.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCountryContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where country contains
        defaultAddressDetailsFiltering("country.contains=" + DEFAULT_COUNTRY, "country.contains=" + UPDATED_COUNTRY);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCountryNotContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where country does not contain
        defaultAddressDetailsFiltering("country.doesNotContain=" + UPDATED_COUNTRY, "country.doesNotContain=" + DEFAULT_COUNTRY);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLandmarkIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where landmark equals to
        defaultAddressDetailsFiltering("landmark.equals=" + DEFAULT_LANDMARK, "landmark.equals=" + UPDATED_LANDMARK);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLandmarkIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where landmark in
        defaultAddressDetailsFiltering("landmark.in=" + DEFAULT_LANDMARK + "," + UPDATED_LANDMARK, "landmark.in=" + UPDATED_LANDMARK);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLandmarkIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where landmark is not null
        defaultAddressDetailsFiltering("landmark.specified=true", "landmark.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLandmarkContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where landmark contains
        defaultAddressDetailsFiltering("landmark.contains=" + DEFAULT_LANDMARK, "landmark.contains=" + UPDATED_LANDMARK);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLandmarkNotContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where landmark does not contain
        defaultAddressDetailsFiltering("landmark.doesNotContain=" + UPDATED_LANDMARK, "landmark.doesNotContain=" + DEFAULT_LANDMARK);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLatitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where latitude equals to
        defaultAddressDetailsFiltering("latitude.equals=" + DEFAULT_LATITUDE, "latitude.equals=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLatitudeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where latitude in
        defaultAddressDetailsFiltering("latitude.in=" + DEFAULT_LATITUDE + "," + UPDATED_LATITUDE, "latitude.in=" + UPDATED_LATITUDE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLatitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where latitude is not null
        defaultAddressDetailsFiltering("latitude.specified=true", "latitude.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLatitudeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where latitude is greater than or equal to
        defaultAddressDetailsFiltering(
            "latitude.greaterThanOrEqual=" + DEFAULT_LATITUDE,
            "latitude.greaterThanOrEqual=" + UPDATED_LATITUDE
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLatitudeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where latitude is less than or equal to
        defaultAddressDetailsFiltering("latitude.lessThanOrEqual=" + DEFAULT_LATITUDE, "latitude.lessThanOrEqual=" + SMALLER_LATITUDE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLatitudeIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where latitude is less than
        defaultAddressDetailsFiltering("latitude.lessThan=" + UPDATED_LATITUDE, "latitude.lessThan=" + DEFAULT_LATITUDE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLatitudeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where latitude is greater than
        defaultAddressDetailsFiltering("latitude.greaterThan=" + SMALLER_LATITUDE, "latitude.greaterThan=" + DEFAULT_LATITUDE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLongitudeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where longitude equals to
        defaultAddressDetailsFiltering("longitude.equals=" + DEFAULT_LONGITUDE, "longitude.equals=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLongitudeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where longitude in
        defaultAddressDetailsFiltering("longitude.in=" + DEFAULT_LONGITUDE + "," + UPDATED_LONGITUDE, "longitude.in=" + UPDATED_LONGITUDE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLongitudeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where longitude is not null
        defaultAddressDetailsFiltering("longitude.specified=true", "longitude.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLongitudeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where longitude is greater than or equal to
        defaultAddressDetailsFiltering(
            "longitude.greaterThanOrEqual=" + DEFAULT_LONGITUDE,
            "longitude.greaterThanOrEqual=" + UPDATED_LONGITUDE
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLongitudeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where longitude is less than or equal to
        defaultAddressDetailsFiltering("longitude.lessThanOrEqual=" + DEFAULT_LONGITUDE, "longitude.lessThanOrEqual=" + SMALLER_LONGITUDE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLongitudeIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where longitude is less than
        defaultAddressDetailsFiltering("longitude.lessThan=" + UPDATED_LONGITUDE, "longitude.lessThan=" + DEFAULT_LONGITUDE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByLongitudeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where longitude is greater than
        defaultAddressDetailsFiltering("longitude.greaterThan=" + SMALLER_LONGITUDE, "longitude.greaterThan=" + DEFAULT_LONGITUDE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByIsActiveIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where isActive equals to
        defaultAddressDetailsFiltering("isActive.equals=" + DEFAULT_IS_ACTIVE, "isActive.equals=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByIsActiveIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where isActive in
        defaultAddressDetailsFiltering("isActive.in=" + DEFAULT_IS_ACTIVE + "," + UPDATED_IS_ACTIVE, "isActive.in=" + UPDATED_IS_ACTIVE);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByIsActiveIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where isActive is not null
        defaultAddressDetailsFiltering("isActive.specified=true", "isActive.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCreatedAtIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where createdAt equals to
        defaultAddressDetailsFiltering("createdAt.equals=" + DEFAULT_CREATED_AT, "createdAt.equals=" + UPDATED_CREATED_AT);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCreatedAtIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where createdAt in
        defaultAddressDetailsFiltering(
            "createdAt.in=" + DEFAULT_CREATED_AT + "," + UPDATED_CREATED_AT,
            "createdAt.in=" + UPDATED_CREATED_AT
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCreatedAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where createdAt is not null
        defaultAddressDetailsFiltering("createdAt.specified=true", "createdAt.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByUpdatedAtIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where updatedAt equals to
        defaultAddressDetailsFiltering("updatedAt.equals=" + DEFAULT_UPDATED_AT, "updatedAt.equals=" + UPDATED_UPDATED_AT);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByUpdatedAtIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where updatedAt in
        defaultAddressDetailsFiltering(
            "updatedAt.in=" + DEFAULT_UPDATED_AT + "," + UPDATED_UPDATED_AT,
            "updatedAt.in=" + UPDATED_UPDATED_AT
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByUpdatedAtIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where updatedAt is not null
        defaultAddressDetailsFiltering("updatedAt.specified=true", "updatedAt.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where createdBy equals to
        defaultAddressDetailsFiltering("createdBy.equals=" + DEFAULT_CREATED_BY, "createdBy.equals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCreatedByIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where createdBy in
        defaultAddressDetailsFiltering(
            "createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY,
            "createdBy.in=" + UPDATED_CREATED_BY
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCreatedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where createdBy is not null
        defaultAddressDetailsFiltering("createdBy.specified=true", "createdBy.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCreatedByContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where createdBy contains
        defaultAddressDetailsFiltering("createdBy.contains=" + DEFAULT_CREATED_BY, "createdBy.contains=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCreatedByNotContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where createdBy does not contain
        defaultAddressDetailsFiltering("createdBy.doesNotContain=" + UPDATED_CREATED_BY, "createdBy.doesNotContain=" + DEFAULT_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByUpdatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where updatedBy equals to
        defaultAddressDetailsFiltering("updatedBy.equals=" + DEFAULT_UPDATED_BY, "updatedBy.equals=" + UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByUpdatedByIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where updatedBy in
        defaultAddressDetailsFiltering(
            "updatedBy.in=" + DEFAULT_UPDATED_BY + "," + UPDATED_UPDATED_BY,
            "updatedBy.in=" + UPDATED_UPDATED_BY
        );
    }

    @Test
    @Transactional
    void getAllAddressDetailsByUpdatedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where updatedBy is not null
        defaultAddressDetailsFiltering("updatedBy.specified=true", "updatedBy.specified=false");
    }

    @Test
    @Transactional
    void getAllAddressDetailsByUpdatedByContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where updatedBy contains
        defaultAddressDetailsFiltering("updatedBy.contains=" + DEFAULT_UPDATED_BY, "updatedBy.contains=" + UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByUpdatedByNotContainsSomething() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        // Get all the addressDetailsList where updatedBy does not contain
        defaultAddressDetailsFiltering("updatedBy.doesNotContain=" + UPDATED_UPDATED_BY, "updatedBy.doesNotContain=" + DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    void getAllAddressDetailsByCustomerIsEqualToSomething() throws Exception {
        Customer customer;
        if (TestUtil.findAll(em, Customer.class).isEmpty()) {
            addressDetailsRepository.saveAndFlush(addressDetails);
            customer = CustomerResourceIT.createEntity();
        } else {
            customer = TestUtil.findAll(em, Customer.class).get(0);
        }
        em.persist(customer);
        em.flush();
        addressDetails.setCustomer(customer);
        addressDetailsRepository.saveAndFlush(addressDetails);
        Long customerId = customer.getId();
        // Get all the addressDetailsList where customer equals to customerId
        defaultAddressDetailsShouldBeFound("customerId.equals=" + customerId);

        // Get all the addressDetailsList where customer equals to (customerId + 1)
        defaultAddressDetailsShouldNotBeFound("customerId.equals=" + (customerId + 1));
    }

    private void defaultAddressDetailsFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultAddressDetailsShouldBeFound(shouldBeFound);
        defaultAddressDetailsShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAddressDetailsShouldBeFound(String filter) throws Exception {
        restAddressDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(addressDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].addressDetailType").value(hasItem(DEFAULT_ADDRESS_DETAIL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].label").value(hasItem(DEFAULT_LABEL)))
            .andExpect(jsonPath("$.[*].addressLine1").value(hasItem(DEFAULT_ADDRESS_LINE_1)))
            .andExpect(jsonPath("$.[*].addressLine2").value(hasItem(DEFAULT_ADDRESS_LINE_2)))
            .andExpect(jsonPath("$.[*].addressLine3").value(hasItem(DEFAULT_ADDRESS_LINE_3)))
            .andExpect(jsonPath("$.[*].floorNo").value(hasItem(DEFAULT_FLOOR_NO)))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE.intValue())))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].landmark").value(hasItem(DEFAULT_LANDMARK)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE)))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)));

        // Check, that the count call also returns 1
        restAddressDetailsMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAddressDetailsShouldNotBeFound(String filter) throws Exception {
        restAddressDetailsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAddressDetailsMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingAddressDetails() throws Exception {
        // Get the addressDetails
        restAddressDetailsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAddressDetails() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the addressDetails
        AddressDetails updatedAddressDetails = addressDetailsRepository.findById(addressDetails.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAddressDetails are not directly saved in db
        em.detach(updatedAddressDetails);
        updatedAddressDetails
            .addressDetailType(UPDATED_ADDRESS_DETAIL_TYPE)
            .mobileNo(UPDATED_MOBILE_NO)
            .name(UPDATED_NAME)
            .label(UPDATED_LABEL)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .addressLine3(UPDATED_ADDRESS_LINE_3)
            .floorNo(UPDATED_FLOOR_NO)
            .pincode(UPDATED_PINCODE)
            .area(UPDATED_AREA)
            .city(UPDATED_CITY)
            .country(UPDATED_COUNTRY)
            .landmark(UPDATED_LANDMARK)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        AddressDetailsDTO addressDetailsDTO = addressDetailsMapper.toDto(updatedAddressDetails);

        restAddressDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, addressDetailsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(addressDetailsDTO))
            )
            .andExpect(status().isOk());

        // Validate the AddressDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAddressDetailsToMatchAllProperties(updatedAddressDetails);
    }

    @Test
    @Transactional
    void putNonExistingAddressDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        addressDetails.setId(longCount.incrementAndGet());

        // Create the AddressDetails
        AddressDetailsDTO addressDetailsDTO = addressDetailsMapper.toDto(addressDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddressDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, addressDetailsDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(addressDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddressDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAddressDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        addressDetails.setId(longCount.incrementAndGet());

        // Create the AddressDetails
        AddressDetailsDTO addressDetailsDTO = addressDetailsMapper.toDto(addressDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAddressDetailsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(addressDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddressDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAddressDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        addressDetails.setId(longCount.incrementAndGet());

        // Create the AddressDetails
        AddressDetailsDTO addressDetailsDTO = addressDetailsMapper.toDto(addressDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAddressDetailsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(addressDetailsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AddressDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAddressDetailsWithPatch() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the addressDetails using partial update
        AddressDetails partialUpdatedAddressDetails = new AddressDetails();
        partialUpdatedAddressDetails.setId(addressDetails.getId());

        partialUpdatedAddressDetails
            .name(UPDATED_NAME)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .pincode(UPDATED_PINCODE)
            .city(UPDATED_CITY)
            .landmark(UPDATED_LANDMARK)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        restAddressDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAddressDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAddressDetails))
            )
            .andExpect(status().isOk());

        // Validate the AddressDetails in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAddressDetailsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAddressDetails, addressDetails),
            getPersistedAddressDetails(addressDetails)
        );
    }

    @Test
    @Transactional
    void fullUpdateAddressDetailsWithPatch() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the addressDetails using partial update
        AddressDetails partialUpdatedAddressDetails = new AddressDetails();
        partialUpdatedAddressDetails.setId(addressDetails.getId());

        partialUpdatedAddressDetails
            .addressDetailType(UPDATED_ADDRESS_DETAIL_TYPE)
            .mobileNo(UPDATED_MOBILE_NO)
            .name(UPDATED_NAME)
            .label(UPDATED_LABEL)
            .addressLine1(UPDATED_ADDRESS_LINE_1)
            .addressLine2(UPDATED_ADDRESS_LINE_2)
            .addressLine3(UPDATED_ADDRESS_LINE_3)
            .floorNo(UPDATED_FLOOR_NO)
            .pincode(UPDATED_PINCODE)
            .area(UPDATED_AREA)
            .city(UPDATED_CITY)
            .country(UPDATED_COUNTRY)
            .landmark(UPDATED_LANDMARK)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);

        restAddressDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAddressDetails.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAddressDetails))
            )
            .andExpect(status().isOk());

        // Validate the AddressDetails in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAddressDetailsUpdatableFieldsEquals(partialUpdatedAddressDetails, getPersistedAddressDetails(partialUpdatedAddressDetails));
    }

    @Test
    @Transactional
    void patchNonExistingAddressDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        addressDetails.setId(longCount.incrementAndGet());

        // Create the AddressDetails
        AddressDetailsDTO addressDetailsDTO = addressDetailsMapper.toDto(addressDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddressDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, addressDetailsDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(addressDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddressDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAddressDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        addressDetails.setId(longCount.incrementAndGet());

        // Create the AddressDetails
        AddressDetailsDTO addressDetailsDTO = addressDetailsMapper.toDto(addressDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAddressDetailsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(addressDetailsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AddressDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAddressDetails() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        addressDetails.setId(longCount.incrementAndGet());

        // Create the AddressDetails
        AddressDetailsDTO addressDetailsDTO = addressDetailsMapper.toDto(addressDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAddressDetailsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(addressDetailsDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AddressDetails in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAddressDetails() throws Exception {
        // Initialize the database
        insertedAddressDetails = addressDetailsRepository.saveAndFlush(addressDetails);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the addressDetails
        restAddressDetailsMockMvc
            .perform(delete(ENTITY_API_URL_ID, addressDetails.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return addressDetailsRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected AddressDetails getPersistedAddressDetails(AddressDetails addressDetails) {
        return addressDetailsRepository.findById(addressDetails.getId()).orElseThrow();
    }

    protected void assertPersistedAddressDetailsToMatchAllProperties(AddressDetails expectedAddressDetails) {
        assertAddressDetailsAllPropertiesEquals(expectedAddressDetails, getPersistedAddressDetails(expectedAddressDetails));
    }

    protected void assertPersistedAddressDetailsToMatchUpdatableProperties(AddressDetails expectedAddressDetails) {
        assertAddressDetailsAllUpdatablePropertiesEquals(expectedAddressDetails, getPersistedAddressDetails(expectedAddressDetails));
    }
}
