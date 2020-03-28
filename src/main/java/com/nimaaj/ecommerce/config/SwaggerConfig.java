package com.nimaaj.ecommerce.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nimaaj.ecommerce.web"))
                .paths(paths())
                .build().securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiKey apiKey() {
        return new ApiKey("apikey", "Authorization", "header");
    }

    private Predicate<String> paths() {
        return or(
                regex("/*.*"));
    }

}