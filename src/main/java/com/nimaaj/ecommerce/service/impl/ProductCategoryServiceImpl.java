package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.ProductCategory;
import com.nimaaj.ecommerce.dto.ProductCategoryDto;
import com.nimaaj.ecommerce.dto.ProductCategoryTreeDto;
import com.nimaaj.ecommerce.exception.ParentNotFoundException;
import com.nimaaj.ecommerce.exception.ProductCategoryNotFoundException;
import com.nimaaj.ecommerce.mapper.ProductCategoryMapper;
import com.nimaaj.ecommerce.model.input.AddProductCategoryModel;
import com.nimaaj.ecommerce.model.input.UpdateProductCategoryModel;
import com.nimaaj.ecommerce.repository.ProductCategoryRepository;
import com.nimaaj.ecommerce.service.ProductCategoryService;
import com.nimaaj.ecommerce.util.Empty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

/**
 * Created by K550 VX on 3/5/2019.
 */
@Service
@Transactional
@Validated
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductCategoryServiceImpl.class);
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository,
                                      ProductCategoryMapper productCategoryMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryMapper = productCategoryMapper;
    }

    @Override
    public List<Long> getInclusiveChildrenCategoryIdsForProductCategory(@NotNull Long productCategoryId) {
        LOGGER.debug("getInclusiveChildrenCategoryIdsForProductCategory() called for {}", productCategoryId);
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(ProductCategoryNotFoundException::new);

        List<Long> categoryIds = loadNextChildrenLevel(productCategory)
                .stream()
                .map(ProductCategory::getId)
                .collect(Collectors.toList());
        categoryIds.add(productCategory.getId());

        return categoryIds;
    }

    private List<ProductCategory> loadNextChildrenLevel(ProductCategory productCategory) {

        List<ProductCategory> result = new ArrayList<>();
        List<ProductCategory> children =
                productCategoryRepository.findAllByParent_Id(productCategory.getId());

        if (Empty.isNotEmpty(children)) {
            result.addAll(children);
            children.forEach(child -> {
                List<ProductCategory> grandChildren = loadNextChildrenLevel(child);
                if (Empty.isNotEmpty(grandChildren)) {
                    result.addAll(grandChildren);
                }
            });
        }

        return result;
    }

    @Override
    public List<ProductCategoryDto> getAllProductCategories() {
        LOGGER.debug("getAllProductCategories() called");
        return productCategoryRepository.findAll()
                .stream()
                .map(productCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductCategoryTreeDto> getAllProductCategoriesTree() {
        LOGGER.debug("getAllProductCategoriesTree() called");
        List<ProductCategoryTreeDto> rootCategories = productCategoryRepository.findAllByParentIsNull()
                .stream()
                .map(productCategoryMapper::toTreeDto)
                .map(root -> {
                    loadChildrenForTreeDto(root);
                    return root;
                })
                .collect(Collectors.toList());

        return rootCategories;
    }

    private void loadChildrenForTreeDto(ProductCategoryTreeDto treeDTO) {
        List<ProductCategory> children = productCategoryRepository.findAllByParent_Id(treeDTO.getId());
        if (Empty.isNotEmpty(children)) {
            List<ProductCategoryTreeDto> childrenTreeDtos = children.stream()
                    .map(productCategoryMapper::toTreeDto)
                    .map(child -> {
                        loadChildrenForTreeDto(child);
                        return child;
                    })
                    .collect(Collectors.toList());
            treeDTO.setChildren(childrenTreeDtos);
        }
    }

    @Override
    public ProductCategoryDto create(@Valid AddProductCategoryModel model) {
        LOGGER.debug("create() called for {}", model);
        ProductCategory parent = null;
        if (Empty.isNotEmpty(model.getParentId())) {
            parent = productCategoryRepository.findById(model.getParentId())
                    .orElseThrow(ParentNotFoundException::new);
        }
        int currentMaxOrderForParent =
                productCategoryRepository.findTopByParent_IdOrderByOrderValDesc(model.getParentId())
                .orElse(0);

        ProductCategory entity = productCategoryMapper.toEntity(model);
        entity.setOrderVal(++currentMaxOrderForParent);
        return productCategoryMapper.toDto(productCategoryRepository.save(entity));
    }

    @Override
    public ProductCategoryDto update(@Valid UpdateProductCategoryModel model) {
        LOGGER.debug("update() run for {}", model);
        ProductCategory productCategory = productCategoryRepository.findById(model.getId())
                .orElseThrow(ProductCategoryNotFoundException::new);

        productCategoryMapper.mapForUpdate(productCategory, model);
        return productCategoryMapper.toDto(productCategoryRepository.save(productCategory));
    }

    @Override
    public ProductCategoryDto getById(@NotNull Long id) {
        LOGGER.debug("getById() run for {}", id);
        return productCategoryRepository.findById(id)
                .map(productCategoryMapper::toDto)
                .orElseThrow(ProductCategoryNotFoundException::new);
    }

    @Override
    public ProductCategoryDto reorder(Long id, Integer orderVal) {
        LOGGER.debug("reorder() run for id {} and orderVal {}", id, orderVal);
        ProductCategory productCategory = productCategoryRepository.findById(id)
                .orElseThrow(ProductCategoryNotFoundException::new);
        Long parentId = productCategory.getParent() != null ? productCategory.getParent().getId() : null;
        List<ProductCategory> siblings = productCategoryRepository.findAllByParent_Id(parentId);
        OptionalInt maxOrder = siblings.stream().mapToInt(ProductCategory::getOrderVal).max();
        if (maxOrder.isPresent()) {
            int maxOrderVal = maxOrder.getAsInt();
            if (orderVal > maxOrderVal) {
                productCategory.setOrderVal(++maxOrderVal);
                productCategory = productCategoryRepository.save(productCategory);
            } else {
                siblings.stream()
                        .filter(sibling -> !sibling.getId().equals(id))
                        .filter(sibling -> sibling.getOrderVal() >= orderVal)
                        .forEach(sibling -> {
                            int currentOrderVal = sibling.getOrderVal();
                            sibling.setOrderVal(++currentOrderVal);
                            productCategoryRepository.save(sibling);
                        });
                productCategory.setOrderVal(orderVal);
                productCategory = productCategoryRepository.save(productCategory);
            }
        } else {
            productCategory.setOrderVal(1);
            productCategory = productCategoryRepository.save(productCategory);
        }
        return productCategoryMapper.toDto(productCategory);
    }
}