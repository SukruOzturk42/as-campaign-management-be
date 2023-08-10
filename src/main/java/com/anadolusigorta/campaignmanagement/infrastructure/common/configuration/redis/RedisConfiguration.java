package com.anadolusigorta.campaignmanagement.infrastructure.common.configuration.redis;

import java.util.Objects;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.Campaign;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RedisConfiguration extends CachingConfigurerSupport {

	private final RedisConfigurationProperties properties;

	@Bean
	public RedisTemplate<String, ?> redisTemplate() {
		RedisTemplate<String, ?> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(arcJedisConnectionFactory());
		Jackson2JsonRedisSerializer<Campaign> j2jrs = new Jackson2JsonRedisSerializer<>(Campaign.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		// Solve the problem that jackson2 cannot deserialize LocalDateTime
		om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		om.registerModule(new JavaTimeModule());
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
		j2jrs.setObjectMapper(om);
		// serialize value Use this serialization method when
		redisTemplate.setValueSerializer(j2jrs);
		redisTemplate.setHashValueSerializer(j2jrs);
		// serialize key Time
		StringRedisSerializer srs = new StringRedisSerializer();
		redisTemplate.setKeySerializer(srs);
		redisTemplate.setHashKeySerializer(srs);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Bean
	public JedisConnectionFactory arcJedisConnectionFactory() {
		var redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(properties.getHost());
		redisStandaloneConfiguration.setPort(properties.getPort());

		if (Objects.nonNull(properties.getPassword())) {
			redisStandaloneConfiguration.setPassword(properties.getPassword());
		}

		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

}