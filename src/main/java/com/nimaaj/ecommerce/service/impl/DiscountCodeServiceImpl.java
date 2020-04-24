package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.DiscountCode;
import com.nimaaj.ecommerce.dto.DiscountCodeDto;
import com.nimaaj.ecommerce.enumaration.DiscountCodeState;
import com.nimaaj.ecommerce.exception.DiscountCodeNotFoundException;
import com.nimaaj.ecommerce.exception.DuplicateDiscountCodeException;
import com.nimaaj.ecommerce.mapper.DiscountCodeMapper;
import com.nimaaj.ecommerce.repository.DiscountCodeRepository;
import com.nimaaj.ecommerce.service.DiscountCodeService;
import com.nimaaj.ecommerce.service.specification.factory.SpecificationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
@Transactional
public class DiscountCodeServiceImpl implements DiscountCodeService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DiscountCodeServiceImpl.class);

    private final DiscountCodeRepository discountCodeRepository;
    private final DiscountCodeMapper discountCodeMapper;
    private final SpecificationFactory<DiscountCode, String> specificationFactory;

    public DiscountCodeServiceImpl(DiscountCodeRepository discountCodeRepository,
                                   DiscountCodeMapper discountCodeMapper,
                                   @Qualifier("discountCodeSearchSpecificationFactory")
                                               SpecificationFactory<DiscountCode, String> specificationFactory) {
        this.discountCodeRepository = discountCodeRepository;
        this.discountCodeMapper = discountCodeMapper;
        this.specificationFactory = specificationFactory;
    }

    @Override
    public DiscountCodeDto create(DiscountCodeDto discountCodeDTO) {
        LOGGER.debug("create() called for {}", discountCodeDTO);
        Optional<DiscountCode> optionalDiscountCode =
                discountCodeRepository.findByCodeVal(discountCodeDTO.getCodeVal());
        if (optionalDiscountCode.isPresent()) {
            throw new DuplicateDiscountCodeException();
        }
        DiscountCode discountCode = discountCodeMapper.toEntity(discountCodeDTO);
        discountCode = discountCodeRepository.save(discountCode);
        return discountCodeMapper.toDto(discountCode);
    }

    @Override
    public DiscountCodeDto update(DiscountCodeDto discountCodeDTO) {
        LOGGER.debug("update() called for {}", discountCodeDTO);
        DiscountCode discountCode = discountCodeRepository.findById(discountCodeDTO.getId())
                .orElseThrow(DiscountCodeNotFoundException::new);
        discountCodeMapper.toEntity(discountCode, discountCodeDTO);
        discountCode = discountCodeRepository.save(discountCode);
        return discountCodeMapper.toDto(discountCode);
    }

    @Override
    public DiscountCodeDto updateState(Long id, DiscountCodeState state) {
        DiscountCode discountCode = discountCodeRepository.findById(id)
                .orElseThrow(DiscountCodeNotFoundException::new);
        discountCode.setState(state);
        discountCode = discountCodeRepository.save(discountCode);
        return discountCodeMapper.toDto(discountCode);
    }

    @Override
    public Page<DiscountCodeDto> search(Pageable pageable, String query) {
        Specification<DiscountCode> specification = specificationFactory.getSpecification(query);
        return discountCodeRepository.findAll(specification, pageable).map(discountCodeMapper::toDto);
    }
}