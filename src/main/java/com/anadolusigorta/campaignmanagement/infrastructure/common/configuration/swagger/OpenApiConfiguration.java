package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("external service")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public GroupedOpenApi apiExternal() {
        return GroupedOpenApi.builder()
                .group("admin service")
                .pathsToMatch("/api-admin/**")
                .build();
    }

}