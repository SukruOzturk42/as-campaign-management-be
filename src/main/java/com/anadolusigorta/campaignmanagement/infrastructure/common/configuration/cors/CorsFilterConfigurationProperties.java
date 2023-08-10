package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.cors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "cors")
public class CorsFilterConfigurationProperties {
	protected String agent;
}
