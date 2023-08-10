/* dks20165 created on 5.01.2022 inside the package - com.anadolusigorta.campaignmanagement.domain.campaign.aspect */

package com.anadolusigorta.campaignmanagement.domain.saletransaction.aspect;

import com.anadolusigorta.campaignmanagement.domain.cache.facade.CustomerCampaignRedisCacheFacade;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.Campaign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class SaleTransactionAspect {

	private final CustomerCampaignRedisCacheFacade customerCampaignRedisCacheFacade;

	@AfterReturning(
			value = "@annotation(com.anadolusigorta.campaignmanagement.domain.saletransaction.aspect.SaleTransaction)",
			returning = "campaign")
	public void saveInitialTransaction(Campaign campaign){

		try{
			customerCampaignRedisCacheFacade.update(campaign);


		}catch (Exception e){
			log.error(e.getMessage());
			log.info(String.format("Error occurred at campaign event creation phase, campaignId: %s",campaign.getId().toString()));
		}

	}
}
