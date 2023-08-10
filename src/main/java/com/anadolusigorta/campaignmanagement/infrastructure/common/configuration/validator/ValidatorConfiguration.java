package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ValidatorConfiguration implements WebMvcConfigurer {

    private final Validator validator;

    @Override
    public Validator getValidator() {
        return validator;
    }

}
