package com.pos.customer.service.impl;

import com.pos.customer.domain.AddressDetails;
import com.pos.customer.repository.AddressDetailsRepository;
import com.pos.customer.service.AddressDetailsService;
import com.pos.customer.service.dto.AddressDetailsDTO;
import com.pos.customer.service.mapper.AddressDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.pos.customer.domain.AddressDetails}.
 */
@Service
@Transactional
public class AddressDetailsServiceImpl implements AddressDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(AddressDetailsServiceImpl.class);

    private final AddressDetailsRepository addressDetailsRepository;

    private final AddressDetailsMapper addressDetailsMapper;

    public AddressDetailsServiceImpl(AddressDetailsRepository addressDetailsRepository, AddressDetailsMapper addressDetailsMapper) {
        this.addressDetailsRepository = addressDetailsRepository;
        this.addressDetailsMapper = addressDetailsMapper;
    }

    @Override
    public AddressDetailsDTO save(AddressDetailsDTO addressDetailsDTO) {
        LOG.debug("Request to save AddressDetails : {}", addressDetailsDTO);
        AddressDetails addressDetails = addressDetailsMapper.toEntity(addressDetailsDTO);
        addressDetails = addressDetailsRepository.save(addressDetails);
        return addressDetailsMapper.toDto(addressDetails);
    }

    @Override
    public AddressDetailsDTO update(AddressDetailsDTO addressDetailsDTO) {
        LOG.debug("Request to update AddressDetails : {}", addressDetailsDTO);
        AddressDetails addressDetails = addressDetailsMapper.toEntity(addressDetailsDTO);
        addressDetails = addressDetailsRepository.save(addressDetails);
        return addressDetailsMapper.toDto(addressDetails);
    }

    @Override
    public Optional<AddressDetailsDTO> partialUpdate(AddressDetailsDTO addressDetailsDTO) {
        LOG.debug("Request to partially update AddressDetails : {}", addressDetailsDTO);

        return addressDetailsRepository
            .findById(addressDetailsDTO.getId())
            .map(existingAddressDetails -> {
                addressDetailsMapper.partialUpdate(existingAddressDetails, addressDetailsDTO);

                return existingAddressDetails;
            })
            .map(addressDetailsRepository::save)
            .map(addressDetailsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AddressDetailsDTO> findOne(Long id) {
        LOG.debug("Request to get AddressDetails : {}", id);
        return addressDetailsRepository.findById(id).map(addressDetailsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete AddressDetails : {}", id);
        addressDetailsRepository.deleteById(id);
    }
}
