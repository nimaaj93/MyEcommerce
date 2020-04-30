package com.nimaaj.ecommerce.util.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class StaticContextAccessor {

    private static StaticContextAccessor instance;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void registerInstance() {
        synchronized (StaticContextAccessor.class) {
            instance = this;
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return instance.applicationContext.getBean(clazz);
    }
}
