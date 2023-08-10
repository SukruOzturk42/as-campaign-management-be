/* dks20165 created on 3.12.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.scheduler.participate */

package com.anadolusigorta.campaignmanagement.infrastructure.scheduler.participate;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.entity.ParticipateCampaignDiscountRewardEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.repository.ParticipateCampaignDiscountRewardJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Component
@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ParticipateRewardScheduler {

	private final ParticipateCampaignDiscountRewardJpaRepository participateCampaignDiscountRewardJpaRepository;
	//@Scheduled(cron = "0 */5 * ? * *")
	public void processRewardDiscount() {

		var discountRecords=participateCampaignDiscountRewardJpaRepository
				.findAllByIsSentFalseAndRewardSendDateIsBefore(LocalDateTime.now());

		var campaignGroups=discountRecords.stream()
				.collect(groupingBy(item->item.getParticipateCampaignReward().getParticipateCampaign()));

		for(var cm:campaignGroups.keySet()){

			var campaignInfo=cm.getCampaignInformation();
			var campaignRecords=campaignGroups.get(cm);

			handleDiscountRewardsSendOperation(campaignInfo,campaignRecords);


		}

	}

	private  void handleDiscountRewardsSendOperation(CampaignInformationEntity campaignInformation,
			List<ParticipateCampaignDiscountRewardEntity> campaignDiscountRewards){

             campaignDiscountRewards.parallelStream().forEach(item->{
				 handleDiscountRewardSendOperation(campaignInformation,item);
			 });

	}
	private void handleDiscountRewardSendOperation(CampaignInformationEntity campaignInformation,
			ParticipateCampaignDiscountRewardEntity campaignDiscountReward){
		var ruleGroup=campaignDiscountReward
				.getParticipateCampaignReward()
					.getParticipateCampaign()
						.getRuleGroup();
		log.info(ruleGroup.getId().toString());
	}
}
