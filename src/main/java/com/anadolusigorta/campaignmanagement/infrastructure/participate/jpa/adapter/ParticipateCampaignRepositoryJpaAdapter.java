/* dks20165 created on 4.08.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.adapter */

package com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.participate.model.ParticipateCampaign;
import com.anadolusigorta.campaignmanagement.domain.participate.model.ParticipatedCustomerCampaign;
import com.anadolusigorta.campaignmanagement.domain.participate.port.ParticipateCampaignRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.RuleGroupRewardDiscountEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.RuleGroupRewardEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.RuleGroupRewardGiftEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.*;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.DiscountCodeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.GiftCodeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.entity.ParticipateCampaignDiscountRewardEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.entity.ParticipateCampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.entity.ParticipateCampaignGiftRewardEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.entity.ParticipateCampaignRewardEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.repository.ParticipateCampaignDiscountRewardJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.repository.ParticipateCampaignGiftRewardJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.repository.ParticipateCampaignJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.participate.jpa.repository.ParticipateCampaignRewardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipateCampaignRepositoryJpaAdapter implements ParticipateCampaignRepository {

	private final ParticipateCampaignJpaRepository participateCampaignJpaRepository;
    private final CustomerCampaignJpaRepository customerCampaignJpaRepository;
	private final ParticipateCampaignRewardJpaRepository participateCampaignRewardJpaRepository;
	private final ParticipateCampaignDiscountRewardJpaRepository participateCampaignDiscountRewardJpaRepository;
	private final ParticipateCampaignGiftRewardJpaRepository participateCampaignGiftRewardJpaRepository;
	private final DiscountCodeJpaRepository discountCodeJpaRepository;
	private final GiftCodeJpaRepository giftCodeJpaRepository;

	@Override
	public ParticipatedCustomerCampaign save(ParticipateCampaign participateCampaign) {

		var campaign=customerCampaignJpaRepository.findByCampaignId(participateCampaign.getCampaignId())
				.orElseThrow(()->new CampaignManagementException("campaign.not.found"));
		var campaignVersion=campaign.getCampaignVersion();
       var campaignInformation=campaignVersion.getCampaignInformation();
		var campaignRuleGroup=campaignVersion.getCampaignRuleGroups().stream()
				.filter(item->item.getId().equals(participateCampaign.getRuleGroupId()))
				.findFirst()
				.orElseThrow(()->new CampaignManagementException("campaign.rule.group.not.found"));
		var  participateEntity = participateCampaignJpaRepository.save(toEntity(participateCampaign));
		participateEntity.setCampaignInformation(campaignInformation);
		participateEntity.setCampaignVersion(campaignVersion);
		participateEntity.setRuleGroup(campaignRuleGroup);
		participateEntity
				.setParticipateCampaignReward(saveCampaignReward(participateCampaign,participateEntity,campaignRuleGroup.getReward()));

		return 	participateCampaignJpaRepository.save(participateEntity).toModel();
	}

	private ParticipateCampaignRewardEntity saveCampaignReward(ParticipateCampaign participateCampaign,
			ParticipateCampaignEntity participateCampaignEntity,
			RuleGroupRewardEntity reward){
		var participateRewardEntity=participateCampaignRewardJpaRepository.save(new ParticipateCampaignRewardEntity());
		participateRewardEntity.setParticipateCampaign(participateCampaignEntity);
		if(reward.getDiscount()!=null){
			participateRewardEntity
					.setDiscountReward(saveParticipateDiscountReward(participateCampaign,participateRewardEntity,reward.getDiscount()));
		}
		if(reward.getGift()!=null){
			participateRewardEntity
					.setGiftReward(saveParticipateGiftReward
							(participateCampaign,participateRewardEntity,reward.getGift()));
		}
		return participateCampaignRewardJpaRepository.save(participateRewardEntity);

	}

	private ParticipateCampaignDiscountRewardEntity saveParticipateDiscountReward(ParticipateCampaign participateCampaign,ParticipateCampaignRewardEntity participateCampaignRewardEntity,
			RuleGroupRewardDiscountEntity discount){
		var participateDiscountRewardEntity=new ParticipateCampaignDiscountRewardEntity();

		var codeInfo=discount.getDiscountCodeInformation();
		if(codeInfo!=null){
			var disCountCode=discountCodeJpaRepository.findTopByDiscountCodeInformationIdAndIsActiveTrueAndContactNumberIsNull(codeInfo.getId())
					.orElseThrow(()->new CampaignManagementException("discount.code.not.found"));
			disCountCode.setContactNumber(participateCampaign.getContactNumber());
			discountCodeJpaRepository.save(disCountCode);
			participateDiscountRewardEntity.setCampaignCode(disCountCode);
		}
		participateDiscountRewardEntity.setParticipateCampaignReward(participateCampaignRewardEntity);
		participateDiscountRewardEntity.setDiscount(discount);
		return participateCampaignDiscountRewardJpaRepository.save(participateDiscountRewardEntity);

	}

	private ParticipateCampaignGiftRewardEntity saveParticipateGiftReward(ParticipateCampaign participateCampaign,ParticipateCampaignRewardEntity participateCampaignRewardEntity,
			RuleGroupRewardGiftEntity ruleGroupRewardGiftEntity){
		var participateGiftRewardEntity=new ParticipateCampaignGiftRewardEntity();
		var giftTicket=ruleGroupRewardGiftEntity.getGiftCodeInformation();
		if(giftTicket!=null){
			var giftCode=giftCodeJpaRepository.findTopByGiftCodeInformationIdAndContactNumberIsNull(giftTicket.getId())
					.orElseThrow(()->new CampaignManagementException("gift.code.not.found"));
			giftCode.setContactNumber(participateCampaign.getContactNumber());
			giftCodeJpaRepository.save(giftCode);
			participateGiftRewardEntity.setGiftCode(giftCode);
		}
		participateGiftRewardEntity.setParticipateCampaignReward(participateCampaignRewardEntity);
		participateGiftRewardEntity.setGift(ruleGroupRewardGiftEntity);
		return participateCampaignGiftRewardJpaRepository.save(participateGiftRewardEntity);


	}

	private ParticipateCampaignEntity toEntity(ParticipateCampaign participateCampaign) {
		var participateEntity=new ParticipateCampaignEntity();
		participateEntity.setCustomerNumber(participateCampaign.getContactNumber());
		participateEntity.setOrderOfParticipation(getOrderOfParticipation(participateCampaign));
		participateEntity.setNumberOfParticipation(gerNumberOfParticipation(participateCampaign));
		participateCampaign.setTransactionId(participateCampaign.getTransactionId());
		return participateEntity;
	}

	public Integer gerNumberOfParticipation(ParticipateCampaign participateCampaign){
		var participant=participateCampaignJpaRepository
				.findTopByCampaignInformationCampaignIdAndCustomerNumberOrderByNumberOfParticipationDesc(participateCampaign.getCampaignId(),participateCampaign.getContactNumber());
		if(participant.isEmpty()){
			return Constants.DEFAULT_PARTICIPATION_NUMBER;
		}else{
			var value=participant.get().getNumberOfParticipation();
			value++;
			return value;
		}
	}

	public Integer getOrderOfParticipation(ParticipateCampaign participateCampaign){
		var participant=participateCampaignJpaRepository
				.findTopByCampaignInformationCampaignIdOrderByNumberOfParticipationDesc(participateCampaign.getCampaignId());
		if(participant.isEmpty()){
			return Constants.DEFAULT_PARTICIPATION_ORDER;
		}else{
			var value=participant.get().getOrderOfParticipation();
			value++;
			return value;
		}
	}
}
