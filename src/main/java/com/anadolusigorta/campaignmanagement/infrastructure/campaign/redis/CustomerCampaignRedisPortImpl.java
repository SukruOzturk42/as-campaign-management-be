package com.anadolusigorta.campaignmanagement.infrastructure.campaign.redis;

import java.util.ArrayList;
import java.util.List;

import com.anadolusigorta.campaignmanagement.domain.campaign.port.CustomerCampaignRedisPort;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.Campaign;

@Component
@Slf4j
public class CustomerCampaignRedisPortImpl implements CustomerCampaignRedisPort {
	private static final String HASH_KEY = "Campaigns";

	private final HashOperations<String, String, Campaign> hashOperations;

	private final RedisTemplate<String, Campaign> redisTemplate;


	public CustomerCampaignRedisPortImpl(RedisTemplate<String, Campaign> redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
		this.redisTemplate=redisTemplate;
	}
	@Override
	public void deleteCampaignById(String campaignId) {
		hashOperations.delete(HASH_KEY, campaignId);
	}

	@Override
	public void putCampaign(Campaign campaign) {
		try {


			log.info(String.format("Redis put campaign %s",new Gson().toJson(campaign)));

			String campaignId = campaign.getId().toString();
			hashOperations.put(HASH_KEY, campaignId, campaign);

		}catch (Exception e){
			log.error(String.format("Redis put error %s",e.getMessage()));
		}

	}


	@Override
	public void putCampaignIfAbsent(Campaign campaign) {
		hashOperations.putIfAbsent(HASH_KEY, campaign.getId().toString(), campaign);
	}


	@Override
	public List<Campaign> getAllCampaigns() {
		try {
			return hashOperations.values(HASH_KEY);

		}catch (Exception e){
			log.info(String.format("Redis error %s",e.getMessage()));
			return new ArrayList<>();
		}
	}

	@Override
	public Boolean cleanAll() {
		redisTemplate.delete(HASH_KEY);
		return true;
	}

	@Override
	public Campaign getCampaignById(String campaignId) {
		return hashOperations.get(HASH_KEY, campaignId);
	}



}
