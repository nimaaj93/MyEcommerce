package com.nimaaj.ecommerce.service.impl;

import com.nimaaj.ecommerce.domain.Application;
import com.nimaaj.ecommerce.dto.ApplicationDTO;
import com.nimaaj.ecommerce.mapper.ApplicationMapper;
import com.nimaaj.ecommerce.repository.ApplicationRepository;
import com.nimaaj.ecommerce.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    @Override
    public ApplicationDTO getApplication() {
        LOGGER.debug("getApplication() called");
        Application application = applicationRepository.findAll()
                .stream()
                .findFirst()
                .orElse(new Application());
        return applicationMapper.toDto(application);
    }
}