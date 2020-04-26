package com.nimaaj.ecommerce.service.specification.factory.impl;

import com.nimaaj.ecommerce.domain.NotificationTemplate;
import com.nimaaj.ecommerce.service.specification.factory.SpecificationFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Qualifier("notificationTemplateSearchSpecificationFactory")
public class NotificationTemplateSearchSpecificationFactory
        implements SpecificationFactory<NotificationTemplate, String> {

    @Override
    public Specification<NotificationTemplate> getSpecification(String filter) {
        if (!StringUtils.hasText(filter)) {
            return Specification.where(null);
        }
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.or(
                        criteriaBuilder.like(root.get("templateText"), "%" + filter + "%"),
                        criteriaBuilder.like(root.get("templateCode"), "%" + filter + "%")
                );
    }
}
