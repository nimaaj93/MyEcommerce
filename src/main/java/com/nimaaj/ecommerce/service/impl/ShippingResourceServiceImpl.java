package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.ShippingResource;
import com.nimaaj.ecommerce.dto.ShippingResourceDTO;
import com.nimaaj.ecommerce.exception.InvalidInputDataException;
import com.nimaaj.ecommerce.exception.ShippingResourceNotFoundException;
import com.nimaaj.ecommerce.mapper.ShippingResourceMapper;
import com.nimaaj.ecommerce.repository.ShippingResourceRepository;
import com.nimaaj.ecommerce.service.ShippingResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Validated
public class ShippingResourceServiceImpl implements ShippingResourceService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ShippingResourceServiceImpl.class);

    private final ShippingResourceRepository shippingResourceRepository;
    private final ShippingResourceMapper shippingResourceMapper;

    public ShippingResourceServiceImpl(ShippingResourceRepository shippingResourceRepository,
                                       ShippingResourceMapper shippingResourceMapper) {
        this.shippingResourceRepository = shippingResourceRepository;
        this.shippingResourceMapper = shippingResourceMapper;
    }

    @Override
    public ShippingResourceDTO getById(Long id) {
        LOGGER.debug("getById() called for {}", id);
        return shippingResourceRepository.findById(id)
                .map(shippingResourceMapper::toDto)
                .orElseThrow(ShippingResourceNotFoundException::new);
    }

    @Override
    public ShippingResourceDTO update(ShippingResourceDTO shippingResourceDTO) {
        LOGGER.debug("update() called for {}", shippingResourceDTO);
        if (shippingResourceDTO.getAvailable() > shippingResourceDTO.getTotal()) {
            throw new InvalidInputDataException();
        }
        shippingResourceRepository.findById(shippingResourceDTO.getId())
                .orElseThrow(ShippingResourceNotFoundException::new);
        ShippingResource shippingResource = shippingResourceMapper.toEntity(shippingResourceDTO);
        shippingResource = shippingResourceRepository.save(shippingResource);
        return shippingResourceMapper.toDto(shippingResource);
    }

    @Override
    public ShippingResourceDTO updateAvailable(Long id, @PositiveOrZero Integer available) {
        LOGGER.debug("updateAvailable() called for id {} and available {}", id, available);
        ShippingResource shippingResource = shippingResourceRepository.findById(id)
                .orElseThrow(ShippingResourceNotFoundException::new);
        if (available > shippingResource.getTotal()) {
            throw new InvalidInputDataException();
        }
        shippingResource.setAvailable(available);
        shippingResource = shippingResourceRepository.save(shippingResource);
        return shippingResourceMapper.toDto(shippingResource);
    }

    @Override
    public ShippingResourceDTO updateTotal(Long id, @PositiveOrZero Integer total) {
        LOGGER.debug("updateTotal() called for id {} and total {}", id, total);
        ShippingResource shippingResource = shippingResourceRepository.findById(id)
                .orElseThrow(ShippingResourceNotFoundException::new);
        if (total < shippingResource.getAvailable()) {
            throw new InvalidInputDataException();
        }
        shippingResource.setTotal(total);
        shippingResource = shippingResourceRepository.save(shippingResource);
        return shippingResourceMapper.toDto(shippingResource);
    }

    @Override
    public List<ShippingResourceDTO> get(Long fromDate, Long toDate) {
        LOGGER.debug("get() called for fromDate {} and toDate {}", fromDate, toDate);
        if (fromDate >= toDate) {
            throw new InvalidInputDataException();
        }
        Date fromDateObj = new Date(fromDate);
        Date toDateObj = new Date(toDate);
        List<ShippingResource> resourceList =
                shippingResourceRepository.findAllByStartDateTimeBeforeAndEndDateTimeAfter(fromDateObj, toDateObj);
        return resourceList.stream().map(shippingResourceMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ShippingResourceDTO add(ShippingResourceDTO shippingResourceDTO) {
        LOGGER.debug("add() called for {}", shippingResourceDTO);
        if (shippingResourceDTO.getAvailable() > shippingResourceDTO.getTotal()) {
            throw new InvalidInputDataException();
        }
        ShippingResource shippingResource = shippingResourceMapper.toEntity(shippingResourceDTO);
        shippingResource = shippingResourceRepository.save(shippingResource);
        return shippingResourceMapper.toDto(shippingResource);
    }
}