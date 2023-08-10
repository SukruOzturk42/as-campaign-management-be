package com.anadolusigorta.campaignmanagement.infrastructure.scheduler.cacheloader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.anadolusigorta.campaignmanagement.domain.cache.facade.CustomerCampaignRedisCacheFacade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class CacheLoaderScheduler {

	private final CustomerCampaignRedisCacheFacade customerCampaignRedisCacheFacade;
	@Value("${cron.cacheLoaderSchedulerEnabled}")
	private boolean jobEnabled;

	@Scheduled(cron = "${cron.cacheLoaderScheduler}")
	private void cacheLoad() {
		if (jobEnabled) {
			log.info ("Cache Loader Scheduler is started:" + LocalDateTime.now());
			customerCampaignRedisCacheFacade.reloadCustomerCampaignCache();
			log.info ("Cache Loader Scheduler is finished:" + LocalDateTime.now());
		}
	}
}
