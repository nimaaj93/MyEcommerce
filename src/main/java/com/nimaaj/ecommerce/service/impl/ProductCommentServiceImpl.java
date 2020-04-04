package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Product;
import com.nimaaj.ecommerce.domain.ProductComment;
import com.nimaaj.ecommerce.domain.User;
import com.nimaaj.ecommerce.dto.ProductCommentDto;
import com.nimaaj.ecommerce.enumaration.CommentStatus;
import com.nimaaj.ecommerce.exception.AdminRootCommentRestrictionException;
import com.nimaaj.ecommerce.exception.ProductNotFoundException;
import com.nimaaj.ecommerce.exception.ResourceNotFoundException;
import com.nimaaj.ecommerce.exception.UnsuppertedUpdateForCommentStateException;
import com.nimaaj.ecommerce.mapper.ProductCommentMapper;
import com.nimaaj.ecommerce.model.input.AdminProductCommentFilter;
import com.nimaaj.ecommerce.repository.ProductCommentRepository;
import com.nimaaj.ecommerce.repository.ProductRepository;
import com.nimaaj.ecommerce.security.SecurityUtils;
import com.nimaaj.ecommerce.service.ProductCommentService;
import com.nimaaj.ecommerce.service.specification.factory.SpecificationFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class ProductCommentServiceImpl implements ProductCommentService {

    private final ProductRepository productRepository;
    private final ProductCommentRepository productCommentRepository;
    private final ProductCommentMapper productCommentMapper;
    private final SpecificationFactory<ProductComment, AdminProductCommentFilter> specificationFactory;

    public ProductCommentServiceImpl(ProductRepository productRepository,
                                     ProductCommentRepository productCommentRepository,
                                     ProductCommentMapper productCommentMapper,
                                     @Qualifier("productCommentSearchSpecificationFactory")
                                     SpecificationFactory<ProductComment, AdminProductCommentFilter>
                                             specificationFactory) {
        this.productRepository = productRepository;
        this.productCommentRepository = productCommentRepository;
        this.productCommentMapper = productCommentMapper;
        this.specificationFactory = specificationFactory;
    }

    @Override
    public ProductCommentDto addComment(ProductCommentDto productCommentDto) {
        validateCreateComment(productCommentDto);
        ProductComment productComment = mapForCreate(productCommentDto);
        return productCommentMapper.toDto(productCommentRepository.saveAndFlush(productComment));
    }

    private void validateCreateComment(ProductCommentDto productCommentDto) {
        if (productCommentDto.getParentId() == null && SecurityUtils.isCurrentUserAdmin()) {
            throw new AdminRootCommentRestrictionException();
        }
    }

    private ProductComment mapForCreate(ProductCommentDto productCommentDto) {

        Long userId = SecurityUtils.getCurrentUserId().orElseThrow(IllegalStateException::new);

        Product product = productRepository.findById(productCommentDto.getProductId())
                                            .orElseThrow(ProductNotFoundException::new);

        ProductComment productComment = new ProductComment();
        productComment.setComment(productCommentDto.getComment());
        productComment.setUser(new User(userId));
        productComment.setProduct(product);

        if (productCommentDto.getParentId() != null) {
            ProductComment parent = productCommentRepository.findById(productCommentDto.getParentId())
                    .orElseThrow(ResourceNotFoundException::new);
            productComment.setParent(parent);
        }

        productComment.setEdited(false);
        productComment.setAdminComment(SecurityUtils.isCurrentUserAdmin());
        productComment.setStatus(CommentStatus.NEW);

        return productComment;
    }

    @Override
    public ProductCommentDto updateComment(ProductCommentDto productCommentDto) {

        Long userId = SecurityUtils.getCurrentUserId().orElseThrow(IllegalStateException::new);

        ProductComment comment = productCommentRepository.findById(productCommentDto.getId())
                .filter(productComment -> productComment.getUser().getId().equals(userId))
                .filter(productComment -> productComment.getStatus() != CommentStatus.DELETED)
                .orElseThrow(ResourceNotFoundException::new);

        if (comment.getStatus() != CommentStatus.NEW) {
            throw new UnsuppertedUpdateForCommentStateException();
        }

        setChangesForUpdate(comment, productCommentDto);

        return productCommentMapper.toDto(productCommentRepository.saveAndFlush(comment));
    }

    private void setChangesForUpdate(ProductComment comment, ProductCommentDto productCommentDto) {
        comment.setEdited(true);
        comment.setComment(productCommentDto.getComment());
    }

    @Override
    public void deleteComment(Long productId, Long commentId) {
        Long userId = SecurityUtils.getCurrentUserId().orElseThrow(IllegalStateException::new);

        ProductComment comment = productCommentRepository.findById(commentId)
                .filter(productComment -> productComment.getUser().getId().equals(userId))
                .orElseThrow(ResourceNotFoundException::new);

        comment.setStatus(CommentStatus.DELETED);

        productCommentRepository.save(comment);
    }

    @Override
    public ProductCommentDto publishComment(Long productId, Long commentId) {

        ProductComment comment = productCommentRepository.findById(commentId)
                .filter(productComment -> productComment.getStatus() != CommentStatus.DELETED)
                .orElseThrow(ResourceNotFoundException::new);

        if (comment.getStatus() != CommentStatus.NEW) {
            throw new UnsuppertedUpdateForCommentStateException();
        }

        comment.setStatus(CommentStatus.VERIFIED);

        return productCommentMapper.toDto(productCommentRepository.saveAndFlush(comment));
    }

    @Override
    public ProductCommentDto getComment(Long productId, Long commentId) {
        Long userId = SecurityUtils.getCurrentUserId().orElseThrow(IllegalStateException::new);

        ProductComment comment = productCommentRepository.findById(commentId)
                .filter(productComment -> productComment.getUser().getId().equals(userId) || SecurityUtils.isCurrentUserAdmin())
                .filter(productComment -> productComment.getStatus() != CommentStatus.DELETED)
                .orElseThrow(ResourceNotFoundException::new);

        return productCommentMapper.toDto(comment);
    }

    @Override
    public Page<ProductCommentDto> getProductComments(Pageable pageable, Long productId) {
        return productCommentRepository.findAllByProduct_IdAndStatusIn(
                productId, Arrays.asList(CommentStatus.VERIFIED), pageable)
                .map(productCommentMapper::toDto);
    }

    @Override
    public Page<ProductCommentDto> getAllComments(Pageable pageable) {
        return productCommentRepository.findAll(pageable).map(productCommentMapper::toDto);
    }

    @Override
    public Page<ProductCommentDto> getAdminProductComments(Pageable pageable,
                                                           AdminProductCommentFilter filter) {
        Specification<ProductComment> specification = specificationFactory.getSpecification(filter);
        return productCommentRepository.findAll(specification, pageable)
                .map(productCommentMapper::toDto);
    }
}
