package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Manufacturer;
import com.nimaaj.ecommerce.dto.ManufacturerDto;
import com.nimaaj.ecommerce.enumaration.ManufacturerStatus;
import com.nimaaj.ecommerce.exception.ManufacturerNotFoundException;
import com.nimaaj.ecommerce.mapper.ManufacturerMapper;
import com.nimaaj.ecommerce.repository.ManufacturerRepository;
import com.nimaaj.ecommerce.service.ManufacturerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ManufacturerServiceImpl.class);

    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerMapper manufacturerMapper;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository,
                                   ManufacturerMapper manufacturerMapper) {
        this.manufacturerRepository = manufacturerRepository;
        this.manufacturerMapper = manufacturerMapper;
    }

    @Override
    public ManufacturerDto create(ManufacturerDto manufacturerDTO) {
        LOGGER.debug("create() run for {}", manufacturerDTO);
        Manufacturer manufacturer = manufacturerMapper.toEntity(manufacturerDTO);
        manufacturer = manufacturerRepository.save(manufacturer);
        return manufacturerMapper.toDto(manufacturer);
    }

    @Override
    public ManufacturerDto update(ManufacturerDto manufacturerDTO) {
        LOGGER.debug("update() run for {}", manufacturerDTO);
        manufacturerRepository.findById(manufacturerDTO.getId())
                .filter(manufacturer1 -> manufacturer1.getManufacturerStatus() != ManufacturerStatus.DELETED)
                .orElseThrow(ManufacturerNotFoundException::new);
        Manufacturer manufacturer = manufacturerMapper.toEntity(manufacturerDTO);
        manufacturer = manufacturerRepository.save(manufacturer);
        return manufacturerMapper.toDto(manufacturer);
    }

    @Override
    public ManufacturerDto updateStatus(Long id, ManufacturerStatus status) {
        LOGGER.debug("updateStatus() run for id {} and status {}", id, status);
        Manufacturer manufacturer = manufacturerRepository.findById(id)
                .filter(manufacturer1 -> manufacturer1.getManufacturerStatus() != ManufacturerStatus.DELETED)
                .orElseThrow(ManufacturerNotFoundException::new);
        manufacturer.setManufacturerStatus(status);
        manufacturer = manufacturerRepository.save(manufacturer);
        return manufacturerMapper.toDto(manufacturer);
    }

    @Override
    public ManufacturerDto getById(Long id) {
        LOGGER.debug("getById() run for {}", id);
        return manufacturerRepository.findById(id)
                .filter(manufacturer1 -> manufacturer1.getManufacturerStatus() != ManufacturerStatus.DELETED)
                .map(manufacturerMapper::toDto)
                .orElseThrow(ManufacturerNotFoundException::new);
    }
}