package com.example.hibernate.reactive.config;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springdoc.webflux.core.converters.WebFluxSupportConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Open API
 */
@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    private final ObjectMapperProvider objectMapperProvider;

    private static final String WILD_CARD = "/**";
    private static final String API_NAME = "Hibernate-reactive";
    private static final String API_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "Hibernate-reactive API 명세서";

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group(API_NAME)
                .pathsToMatch(WILD_CARD)
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        ModelConverters.getInstance().addConverter(new WebFluxSupportConverter(objectMapperProvider));

        return new OpenAPI()
                .specVersion(SpecVersion.V31)
                .info(new Info().title(API_NAME)
                        .description(API_DESCRIPTION)
                        .version(API_VERSION));
    }

}
