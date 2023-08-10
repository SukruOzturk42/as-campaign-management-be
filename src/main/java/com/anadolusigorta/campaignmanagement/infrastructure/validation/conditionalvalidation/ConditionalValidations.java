package com.anadolusigorta.campaignmanagement.infrastructure.validation.conditionalvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ConditionalValidations {
    ConditionalValidation[] value();
}
