package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.domain.ProductQa;
import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.dto.ProductNewQuestionDto;
import com.nimaaj.ecommerce.dto.ProductQaDto;
import com.nimaaj.ecommerce.dto.ProductQaReplyDto;
import com.nimaaj.ecommerce.enumaration.ProductQaStatus;
import com.nimaaj.ecommerce.exception.InvalidOpForQaStatusException;
import com.nimaaj.ecommerce.exception.ProductNotFoundException;
import com.nimaaj.ecommerce.exception.ResourceNotFoundException;
import com.nimaaj.ecommerce.mapper.ProductQaMapper;
import com.nimaaj.ecommerce.repository.ProductQaRepository;
import com.nimaaj.ecommerce.repository.ProductRepository;
import com.nimaaj.ecommerce.security.SecurityUtils;
import com.nimaaj.ecommerce.service.ProductQaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductQaServiceImpl implements ProductQaService {

    private final ProductQaMapper productQaMapper;
    private final ProductQaRepository productQaRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductQaDto reply(Long productId, ProductQaReplyDto replyDto) {

        productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        ProductQa productQa = productQaRepository.findById(replyDto.getId())
                .orElseThrow(ResourceNotFoundException::new);

        Long currentUserId = SecurityUtils.getCurrentUserId().orElseThrow(IllegalStateException::new);
        User user = new User(currentUserId);

        productQa.setReplyUser(user);
        productQa.setReplyDateTime(new Date());
        productQa.setAnswer(replyDto.getReply());
        productQa.setStatus(ProductQaStatus.VERIFIED);

        return productQaMapper.toDto(productQaRepository.saveAndFlush(productQa));
    }

    @Override
    public ProductQaDto addQuestion(Long productId, ProductNewQuestionDto productNewQuestionDto) {

        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);

        Long currentUserId = SecurityUtils.getCurrentUserId().orElseThrow(IllegalStateException::new);
        User user = new User(currentUserId);

        ProductQa productQa = productQaMapper.toEntity(productNewQuestionDto);
        productQa.setStatus(ProductQaStatus.NEW);
        productQa.setAskUser(user);
        productQa.setProduct(product);

        return productQaMapper.toDto(productQaRepository.saveAndFlush(productQa));
    }

    @Override
    public Page<ProductQaDto> pageProductQa(Long productId, Pageable pageable) {

        List<ProductQaStatus> statuses =
                SecurityUtils.isCurrentUserAdmin() ?
                        Arrays.stream(ProductQaStatus.values()).collect(Collectors.toList()) :
                        List.of(ProductQaStatus.VERIFIED);

        return productQaRepository.findAllByStatusInAndProduct_Id(pageable, statuses, productId)
                .map(productQaMapper::toDto);
    }

    @Override
    public ProductQaDto verify(Long productId, Long qaId) {

        productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        ProductQa productQa = productQaRepository.findById(qaId)
                .orElseThrow(ResourceNotFoundException::new);

        if (productQa.getStatus() != ProductQaStatus.NEW) {
            throw new InvalidOpForQaStatusException();
        }

        productQa.setStatus(ProductQaStatus.VERIFIED);

        return productQaMapper.toDto(productQaRepository.save(productQa));
    }

    @Override
    public ProductQaDto reject(Long productId, Long qaId) {

        productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        ProductQa productQa = productQaRepository.findById(qaId)
                .orElseThrow(ResourceNotFoundException::new);

        if (productQa.getStatus() != ProductQaStatus.NEW) {
            throw new InvalidOpForQaStatusException();
        }

        productQa.setStatus(ProductQaStatus.REJECTED);

        return productQaMapper.toDto(productQaRepository.save(productQa));
    }
}
