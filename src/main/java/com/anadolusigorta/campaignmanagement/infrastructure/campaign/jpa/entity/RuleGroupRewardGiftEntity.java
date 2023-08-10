/* odeon_sukruo created on 4.05.2021 inside the package - com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity */

package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignReward;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rule_group_reward_gift")
public class RuleGroupRewardGiftEntity extends AbstractEntity {

	@OneToOne
	@JoinColumn(name = "campaign_rule_group_reward_id")
	private RuleGroupRewardEntity ruleGroupRewardEntity;

	@OneToOne
	@JoinColumn(name = "gift_kind_type_id")
	private GiftKindEntity giftKind;

	@OneToOne
	@JoinColumn(name = "gift_type_id")
	private GiftTypeEntity giftType;

	@OneToOne
	@JoinColumn(name = "gift_delivery_type_id")
	private GiftDeliveryTypeEntity giftDeliveryType;

	@OneToOne
	@JoinColumn(name = "gift_delivery_start_type_id")
	private GiftDeliveryStartTypeEntity giftDeliveryStartType;

	@OneToOne
	@JoinColumn(name = "gift_payment_id")
	private GiftPaymentTypeEntity giftPaymentType;

	@OneToOne
	@JoinColumn(name = "reward_company_information_id")
	private RewardCompanyInformationEntity rewardCompanyInformation;

	@OneToOne
	@JoinColumn(name = "gift_product_id")
	private RewardGiftProductEntity rewardGiftProduct;

	@OneToOne
	@JoinColumn(name = "gift_code_information_id")
	private GiftCodeInformationEntity giftCodeInformation;

	@Column(name = "value")
	private String value;

	@Column(name = "total_product_count")
	private Long totalProductCount;

	@Column(name = "product_delivery_order")
	private Long productDeliveryOrder;

	@Column(name = "customer_product_count")
	private Long customerProductCount;

	@Column(name = "total_customer_count")
	private Long totalCustomerCount;

	@Column(name = "last_send_time")
	private LocalDateTime lastSendTime;

	@Column(name = "day_after_delivery_start")
	private Integer dayAfterDeliveryStart;

	@OneToOne
	@JoinColumn(name = "gift_send_method_type_id")
	private RewardGiftSendMethodTypeEntity rewardGiftSendMethodTypeEntity;

	@Column(name = "reward_gift_template_id")
	private String rewardGiftTemplateId;


	@ManyToOne
	@JoinColumn(name = "reward_role_id")
	private RewardRoleEntity rewardRole;

	@OneToMany(mappedBy = "ruleGroupRewardGift",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CampaignRuleEntity> sendRules;




	public CampaignReward.CampaignRewardGift toModel(){
		return CampaignReward.CampaignRewardGift.builder()
				.rewardGiftId(getId())
				.rewardGiftType(giftType!=null?giftType.toModel():null)
				.rewardGiftKind(giftKind!=null?giftKind.toModel():null)
				.rewardGiftDeliveryStartType(giftDeliveryStartType!=null?giftDeliveryStartType.toModel():null)
				.rewardGiftDeliveryType(giftDeliveryType!=null?giftDeliveryType.toModel():null)
				.rewardGiftPaymentType(giftPaymentType!=null?giftPaymentType.toModel():null)
				.customerCount(totalCustomerCount)
				.dayAfterDeliveryStart(dayAfterDeliveryStart)
				.lastSendTime(lastSendTime)
				.totalProductCount(totalProductCount)
				.customerProductCount(customerProductCount)
				.productDeliveryOrder(productDeliveryOrder)
				.rewardGiftSendMethodType(rewardGiftSendMethodTypeEntity != null ? rewardGiftSendMethodTypeEntity.toModel():null)
				.value(value)
				.giftCodeInformation(giftCodeInformation!=null?giftCodeInformation.toModel():null)
				.rewardGiftProduct(rewardGiftProduct!=null?rewardGiftProduct.toModel():null)
				.rewardProductId(rewardGiftProduct!= null ? rewardGiftProduct.getId() : null)
				.rewardGiftTemplateId(rewardGiftTemplateId)
				.rewardRole(rewardRole!=null?rewardRole.toModel():null)
				.sendRules(sendRules!=null?sendRules
						.stream()
						.sorted(Comparator.comparing(AbstractEntity::getId))
						.map(CampaignRuleEntity::toModel)
						.collect(Collectors.toList())
						: null)
				.build();
	}

}
