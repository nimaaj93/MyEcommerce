package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.NotificationTemplate;
import com.nimaaj.ecommerce.dto.NotificationTemplateDto;
import com.nimaaj.ecommerce.exception.NotificationTemplateNotFoundException;
import com.nimaaj.ecommerce.mapper.NotificationTemplateMapper;
import com.nimaaj.ecommerce.repository.NotificationTemplateRepository;
import com.nimaaj.ecommerce.service.NotificationTemplateService;
import com.nimaaj.ecommerce.service.specification.factory.SpecificationFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class NotificationTemplateServiceImpl implements NotificationTemplateService {

    private final NotificationTemplateRepository notificationTemplateRepository;
    private final NotificationTemplateMapper notificationTemplateMapper;
    private final SpecificationFactory<NotificationTemplate, String> specificationFactory;

    public NotificationTemplateServiceImpl(NotificationTemplateRepository notificationTemplateRepository,
                                           NotificationTemplateMapper notificationTemplateMapper,
                                           @Qualifier("notificationTemplateSearchSpecificationFactory")
                                           SpecificationFactory<NotificationTemplate, String> specificationFactory) {
        this.notificationTemplateRepository = notificationTemplateRepository;
        this.notificationTemplateMapper = notificationTemplateMapper;
        this.specificationFactory = specificationFactory;
    }

    @Override
    public Page<NotificationTemplateDto> getTemplates(Pageable pageable, String query) {
        Specification<NotificationTemplate> specification = specificationFactory.getSpecification(query);
        return notificationTemplateRepository.findAll(specification, pageable)
                .map(notificationTemplateMapper::toDto);
    }

    @Override
    public NotificationTemplateDto create(NotificationTemplateDto notificationTemplateDto) {
        notificationTemplateRepository.findByTemplateCode(notificationTemplateDto.getTemplateCode())
                .orElseThrow(NotificationTemplateNotFoundException::new);
        NotificationTemplate notificationTemplate = notificationTemplateMapper.toEntity(notificationTemplateDto);
        return notificationTemplateMapper.toDto(notificationTemplateRepository.save(notificationTemplate));
    }

    @Override
    public NotificationTemplateDto update(NotificationTemplateDto notificationTemplateDto) {
        NotificationTemplate notificationTemplate = notificationTemplateRepository.findById(notificationTemplateDto.getId())
                .orElseThrow(NotificationTemplateNotFoundException::new);
        notificationTemplate.setTemplateText(notificationTemplateDto.getTemplateText());
        return notificationTemplateMapper.toDto(notificationTemplateRepository.save(notificationTemplate));
    }

    @Override
    public void delete(Long id) {
        notificationTemplateRepository.findById(id)
                .ifPresent(notificationTemplateRepository::delete);
    }

    @Override
    public NotificationTemplateDto getNotificationTemplateByCode(String code) {
        return notificationTemplateRepository.findByTemplateCode(code)
                .map(notificationTemplateMapper::toDto)
                .orElseThrow(NotificationTemplateNotFoundException::new);
    }

    @Override
    public String buildNotificationWithParams(String templateCode, Object ... params) {
        return notificationTemplateRepository.findByTemplateCode(templateCode)
                .map(notificationTemplate -> String.format(notificationTemplate.getTemplateText(), params))
                .orElseThrow(NotificationTemplateNotFoundException::new);
    }

}
