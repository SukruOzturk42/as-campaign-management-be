package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "redis")
public class RedisConfigurationProperties {
	protected String host;
	protected int port;
	protected int timeToLive;
	protected  String password;
}
