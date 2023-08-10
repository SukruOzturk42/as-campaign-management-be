package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.localization;

import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Configuration
public class LocalizationConfiguration extends AcceptHeaderLocaleContextResolver implements WebMvcConfigurer {


    private static final List<String> SUPPORTED_LANGUAGE = List.of(Constants.DEFAULT_LANGUAGE_NAME);

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("i18n/BusinessMessages","i18n/ValidationMessages","i18n/ConstantsMessages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Override
    public LocaleContext resolveLocaleContext(ServerWebExchange exchange) {
        List<String> languages = Optional
                .ofNullable(exchange.getRequest().getHeaders().get(Constants.LANGUAGE_HEADER_NAME))
                .orElse(List.of(Constants.DEFAULT_LANGUAGE_NAME));

        String language = languages.stream()
                .filter(SUPPORTED_LANGUAGE::contains)
                .findFirst()
                .orElse(Constants.DEFAULT_LANGUAGE_NAME);

        return new SimpleLocaleContext(new Locale(language));
    }

    @Override
    public void setLocaleContext(ServerWebExchange exchange, LocaleContext localeContext) {
        LocaleContextHolder.setLocale(localeContext.getLocale());
    }

}