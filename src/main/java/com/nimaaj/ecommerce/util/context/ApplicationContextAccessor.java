package com.nimaaj.ecommerce.util.context;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by K550 VX on 18.10.2019.
 */
@Component
public class ApplicationContextAccessor {

    public static ApplicationContext applicationContext;

    public ApplicationContextAccessor(ApplicationContext applicationContext) {
        ApplicationContextAccessor.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}