/* odeon_sukruo created on 16.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CreateCampaign;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.*;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.*;
import com.anadolusigorta.campaignmanagement.infrastructure.referencetype.jpa.repository.ReferenceTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RuleGroupRewardRepositoryJpaAdapter {
	private final RuleGroupRewardJpaRepository ruleGroupRewardJpaRepository;
	private final DiscountTypeJpaRepository discountTypeJpaRepository;
	private final DiscountKindJpaRepository discountKindJpaRepository;
	private final DiscountDetailTypeJpaRepository discountDetailTypeJpaRepository;
	private final GiftPaymentTypeJpaRepository giftPaymentTypeJpaRepository;
	private final GiftDeliveryTypeJpaRepository giftDeliveryTypeJpaRepository;
	private final GiftDeliveryStartTypeJpaRepository giftDeliveryStartTypeJpaRepository;
	private final GiftTypeJpaRepository giftTypeJpaRepository;
	private final RewardCompanyInformationJpaRepository rewardCompanyInformationJpaRepository;
	private final CampaignRewardDiscountJpaRepository campaignRewardDiscountJpaRepository;
	private final CampaignRewardGiftJpaRepository campaignRewardGiftJpaRepository;
	private final GiftProductJpaRepository giftProductJpaRepository;
	private final RewardRoleJpaRepository rewardRoleJpaRepository;
	private final GiftCodeInformationJpaRepository giftCodeInformationJpaRepository;
	private final DiscountCodeInformationJpaRepository discountCodeInformationJpaRepository;
	private final RewardGiftSendMethodTypeJpaRepository rewardGiftSendMethodTypeJpaRepository;
	private final ReferenceTypeJpaRepository referenceTypeJpaRepository;
	private final CampaignRuleRepositoryJpaAdapter campaignRuleRepositoryJpaAdapter;


	RuleGroupRewardEntity createRuleGroupReward(
			CreateCampaign createCampaign,
			CampaignEntity campaignEntity,
			CampaignRuleGroupEntity campaignRuleGroupEntity
			,CreateCampaign.CampaignRuleGroup campaignRuleGroup){
		if(campaignRuleGroup.getRuleGroupReward().getDiscount()!=null || campaignRuleGroup.getRuleGroupReward().getGift()!=null) {
			var reward=new RuleGroupRewardEntity();
			reward.setDiscount(campaignRuleGroup.getRuleGroupReward().getDiscount()!=null?
					saveRuleGroupRewardDiscountEntity(createCampaign,campaignRuleGroup)
					:null);
			reward.setGift(campaignRuleGroup.getRuleGroupReward().getGift()!=null?
					saveRuleGroupRewardGiftEntity(campaignRuleGroup.getRuleGroupReward().getGift())
					:null);
			reward.setCampaign(campaignEntity);
			reward.setRuleGroup(campaignRuleGroupEntity);
			return ruleGroupRewardJpaRepository.save(reward);
		}
			return null;

	}

	private RuleGroupRewardGiftEntity saveRuleGroupRewardGiftEntity(CreateCampaign.RuleGroupRewardGift gift){
		var entity=new RuleGroupRewardGiftEntity();
		var giftType=giftTypeJpaRepository.findById(gift.getRewardGiftTypeId())
				.orElseThrow(()->new CampaignManagementException("gift.kind.not.found"));
            entity.setRewardCompanyInformation(gift.getCompanyInformationId()!=null?
					rewardCompanyInformationJpaRepository.findById(gift.getCompanyInformationId())
							.orElseThrow(()->new CampaignManagementException("gift.company.type.not.found"))
					:null);
		entity.setRewardGiftProduct(gift.getRewardProductId()!=null?
				giftProductJpaRepository.findById(gift.getRewardProductId())
						.orElseThrow(()->new CampaignManagementException("gift.company.type.not.found"))
				:null);
			entity.setGiftType(giftTypeJpaRepository
					.findById(gift.getRewardGiftTypeId())
					.orElseThrow(()->new CampaignManagementException("gift.type.not.found")));
			entity.setGiftPaymentType(gift.getRewardGiftPaymentTypeId()!=null?
					giftPaymentTypeJpaRepository
					.findById(gift.getRewardGiftPaymentTypeId())
					.orElseThrow(()->new CampaignManagementException("gift.payment.type.not.found"))
					:null);

			entity.setGiftDeliveryType(gift.getRewardGiftDeliveryTypeId()!=null?
					giftDeliveryTypeJpaRepository
					.findById(gift.getRewardGiftDeliveryTypeId())
					.orElseThrow(()->new CampaignManagementException("gift.delivery.type.not.found"))
					:null);
			entity.setGiftDeliveryStartType(gift.getRewardGiftDeliveryStartTypeId()!=null?
					giftDeliveryStartTypeJpaRepository
					.findById(gift.getRewardGiftDeliveryStartTypeId())
					.orElseThrow(()->new CampaignManagementException("gift.delivery.start.type.not.found"))
					:null);
			entity.setDayAfterDeliveryStart(gift.getDayAfterDeliveryStart());
			entity.setLastSendTime(gift.getLastSendTime());
			entity.setRewardGiftTemplateId(gift.getRewardGiftTemplateId());
			entity.setRewardGiftSendMethodTypeEntity(gift.getSendMethodTypeId() != null?
					rewardGiftSendMethodTypeJpaRepository
							.findById(gift.getSendMethodTypeId()).orElseThrow(() -> new CampaignManagementException("gift.send.method.type.not.fount"))
					:null);
				entity.setGiftCodeInformation(gift.getRewardGiftCodeInformationId()!=null?
						giftCodeInformationJpaRepository
								.findById(gift.getRewardGiftCodeInformationId())
								.orElseThrow(()->new CampaignManagementException("gift.ticket.code.not.found"))
						:null);


			entity.setTotalCustomerCount(gift.getTotalCustomerCount());
			entity.setCustomerProductCount(gift.getCustomerProductCount());
			entity.setTotalProductCount(gift.getTotalProductCount());
			entity.setProductDeliveryOrder(gift.getProductDeliveryOrder());
			entity.setValue(gift.getValue());
		    entity.setRewardRole(gift.getRewardRoleId()!=null?
				rewardRoleJpaRepository.findById(gift.getRewardRoleId())
						.orElseThrow(()->new CampaignManagementException("reward.role.not.found"))
				:null);
		entity.setGiftType(giftType);
        entity.setSendRules(gift.getSendRules()!=null?
						gift.getSendRules().stream()
										.map(campaignRuleAttribute ->
												campaignRuleRepositoryJpaAdapter
														.createCampaignRuleAttribute(entity,campaignRuleAttribute))
								.collect(Collectors.toSet())
				:null);
		return campaignRewardGiftJpaRepository.save(entity);
	}

	private RuleGroupRewardDiscountEntity saveRuleGroupRewardDiscountEntity(CreateCampaign createCampaign,
																			CreateCampaign.CampaignRuleGroup campaignRuleGroup){
		var ruleGroupReward=campaignRuleGroup.getRuleGroupReward();
		var discount=ruleGroupReward.getDiscount();
		var entity=new RuleGroupRewardDiscountEntity();
		var discountKind= discount.getDiscountKindId() != null ?
				discountKindJpaRepository.findById(ruleGroupReward.getDiscount().getDiscountKindId())
				.orElseThrow(()->new CampaignManagementException("discount.kind.not.found")) : null;
			entity.setValue(ruleGroupReward.getDiscount().getValue());
			entity.setDiscountDetailType(discountDetailTypeJpaRepository.findById(discount.getDiscountTypeId())
			.orElseThrow(()->new CampaignManagementException("discount.detail.type.not.found")));
			entity.setDiscountType(discountTypeJpaRepository.findById(discount.getDiscountTypeId())
					.orElseThrow(()->new CampaignManagementException("discount.type.not.found")));
			entity.setCoverCodeDiscountValue(ruleGroupReward.getDiscount().getCoverCodeDiscountValue());

			var referenceType=discount.getCoverCodeTypeId()!=null?
					referenceTypeJpaRepository.findById(ruleGroupReward.getDiscount().getCoverCodeTypeId())
					.orElseThrow(()->new CampaignManagementException("discount.kind.not.found")) : null;

			entity.setCoverCodeType(referenceType);

           entity.setDiscountKind(discountKind);

		   if(campaignRuleGroup.getOwnerProduct()==null &&  campaignRuleGroup.getRelatedCooperation()==null){
			   var discountCodeInfo = discount.getDiscountCodeInformationId()!=null?
					   discountCodeInformationJpaRepository.findById(discount.getDiscountCodeInformationId())
							   .orElseThrow(()->new CampaignManagementException("discount.code.not.found"))
					   :null;



			   if (discountCodeInfo != null && !discountCodeInfo.getIsUsed()
					   && !createCampaign.getCampaignInformation().getCampaignTypeId().equals(3L)) {
				   discountCodeInfo.setIsUsed(true);
			   }
			   entity.setDiscountCodeInformation(discountCodeInfo);
		   }

		return campaignRewardDiscountJpaRepository.save(entity);
	}
}
