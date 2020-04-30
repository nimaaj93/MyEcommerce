package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Sale;
import com.nimaaj.ecommerce.dto.SaleDto;
import com.nimaaj.ecommerce.enumaration.SaleStatus;
import com.nimaaj.ecommerce.exception.InvalidInputDataException;
import com.nimaaj.ecommerce.exception.InvalidSaleRefException;
import com.nimaaj.ecommerce.exception.ResourceNotFoundException;
import com.nimaaj.ecommerce.mapper.SaleMapper;
import com.nimaaj.ecommerce.model.input.SaleFilter;
import com.nimaaj.ecommerce.repository.SaleRepository;
import com.nimaaj.ecommerce.service.SaleService;
import com.nimaaj.ecommerce.service.specification.factory.SpecificationFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final SpecificationFactory<Sale, SaleFilter> specificationFactory;

    public SaleServiceImpl(SaleRepository saleRepository,
                           SaleMapper saleMapper,
                           @Qualifier("saleSearchSpecificationFactory")
                           SpecificationFactory<Sale, SaleFilter> specificationFactory) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
        this.specificationFactory = specificationFactory;
    }

    @Override
    public SaleDto add(SaleDto saleDto) {
        log.debug("add() called for {}", saleDto);
        validateSaleDates(saleDto);
        validateSaleRef(saleDto);
        Sale sale = saleMapper.toEntity(saleDto);
        return saleMapper.toDto(saleRepository.saveAndFlush(sale));
    }

    private void validateSaleRef(SaleDto saleDto) {
        if (saleDto.getSaleType().isAcceptRef()) {
            if (!StringUtils.hasText(saleDto.getSaleRefVal())) {
                throw new InvalidSaleRefException();
            }
            saleDto.getSaleType().validateRef(saleDto.getSaleRefVal());
        } else {
            if (StringUtils.hasText(saleDto.getSaleRefVal())) {
                throw new InvalidSaleRefException();
            }
        }
    }

    private void validateSaleDates(SaleDto saleDto) {
        if (saleDto.getStartDateTime() != null && saleDto.getEndDateTime() != null &&
            saleDto.getStartDateTime() >= saleDto.getEndDateTime()) {
            throw new InvalidInputDataException();
        }
    }

    @Override
    public SaleDto update(SaleDto saleDto) {
        log.debug("update() called for {}", saleDto);
        validateSaleDates(saleDto);
        validateSaleRef(saleDto);
        saleRepository.findById(saleDto.getId())
                .orElseThrow(ResourceNotFoundException::new);
        Sale sale = saleMapper.toEntity(saleDto);
        return saleMapper.toDto(saleRepository.saveAndFlush(sale));
    }

    @Override
    public SaleDto getById(SaleDto saleDto) {
        log.debug("getById() called for {}", saleDto);
        return saleRepository.findById(saleDto.getId())
                .map(saleMapper::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public SaleDto activate(Long id) {
        log.debug("activate() called for {}", id);
        Sale sale = saleRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        sale.setSaleStatus(SaleStatus.ACTIVE);
        return saleMapper.toDto(saleRepository.saveAndFlush(sale));
    }

    @Override
    public SaleDto deactivate(Long id) {
        log.debug("deactivate() called for {}", id);
        Sale sale = saleRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        sale.setSaleStatus(SaleStatus.INACTIVE);
        return saleMapper.toDto(saleRepository.saveAndFlush(sale));
    }

    @Override
    public Page<SaleDto> pageSales(Pageable pageable, SaleFilter saleFilter) {
        log.debug("pageSales() called for pageable: {} and SaleFilter: {} ", pageable, saleFilter);
        Specification<Sale> specification = specificationFactory.getSpecification(saleFilter);
        return saleRepository.findAll(specification, pageable)
                .map(saleMapper::toDto);
    }

    @Override
    public List<SaleDto> getAllActiveSalesForCurrentDateTime() {
        return saleRepository.findAllBySaleStatus(SaleStatus.ACTIVE)
                .stream()
                .filter(this::isSaleCurrentlyValid)
                .map(saleMapper::toDto)
                .collect(Collectors.toList());
    }

    private boolean isSaleCurrentlyValid(Sale sale) {

        Date currentTime = new Date();

        if (sale.getStartDateTime() == null) {
            if (sale.getEndDateTime() == null) {
                return true;
            }
            if (sale.getEndDateTime().after(currentTime)) {
                return true;
            }
        } else if (sale.getEndDateTime() == null &&
                   sale.getStartDateTime().before(currentTime)) {
            return true;
        } else if (sale.getStartDateTime().before(currentTime) &&
                   sale.getEndDateTime().after(currentTime)) {
            return true;
        }

        return false;
    }

}
