package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity;


import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.GiftCode;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardNotificationStatus;
import com.anadolusigorta.campaignmanagement.domain.sale.model.SaleRewardGift;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.GiftCodeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.entity.AbstractEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.common.util.GlobalGson;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.RewardNotificationStatusEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales_gift_reward")
public class SaleRewardGiftEntity extends AbstractEntity {

	@OneToOne
	@JoinColumn(name = "sale_campaign_id")
	private SaleCampaignEntity saleCampaign;



	@Column(name = "send_date")
	private LocalDateTime rewardSendDate;

	@Column(name = "description")
	private String description;

	@OneToOne
	@JoinColumn(name = "gift_code_id")
	private GiftCodeEntity giftCode;

	@OneToMany(mappedBy = "saleRewardGift", fetch = FetchType.LAZY)
	private List<GiftCodeEntity> giftCodes;

	@Column(name = "noc_task_id")
	private String nocTaskId;


	@Column(name = "last_send_date")
	private LocalDateTime rewardLastSendDate;

	@Column(name = "send_try_count")
	private Long sendTryCount;

	@Column(name = "reward_owner_contact_no")
	private String rewardOwnerContactNo;


	@ManyToOne
	@JoinColumn(name = "reward_notification_status_id")
	private RewardNotificationStatusEntity rewardNotificationStatus;

	@Column(name = "remove_date")
	private LocalDateTime removeDate;

	@Column(name = "policy_endorsement_number")
	private String policyEndorsementNumber;



	public SaleRewardGift toModel() {
		return SaleRewardGift.builder().id(getId())
				.campaignRewardGift(
						saleCampaign.getRuleGroup() != null && saleCampaign.getRuleGroup().getReward() != null
								? saleCampaign.getRuleGroup().getReward().toModel()
								: null)
				.rewardSendDate(rewardSendDate)
				.saleInformation(saleCampaign.toModel())
				.rewardNotificationStatus(rewardNotificationStatus!=null?rewardNotificationStatus.toModel():
						RewardNotificationStatus.builder()
								.code(Constants.PENDING_NOTIFY_STATUS)
								.description(Constants.PENDING_NOTIFY_STATUS_DESC)
								.build())
				.nocTaskId(nocTaskId)
				.sendTryCount(sendTryCount)
				.rewardOwnerContactNo(rewardOwnerContactNo)
				.removeDate(removeDate)
				.policyEndorsementNumber(policyEndorsementNumber)
				.giftCodes(giftCodes!=null? giftCodes.stream().map(GiftCodeEntity::toModel).toList() :null)
				.build();
	}

	public SaleRewardGift toRewardDetailModel() {
		return SaleRewardGift.builder().id(getId())
				.campaignRewardGift(saleCampaign.getRuleGroup() != null &&
						saleCampaign.getRuleGroup().getReward() != null
								? saleCampaign.getRuleGroup().getReward().toModel()
								: null)
				.rewardSendDate(rewardSendDate)
				.giftCode(giftCode != null ? giftCode.toModel() : null)
				.rewardNotificationStatus(rewardNotificationStatus!=null?rewardNotificationStatus.toModel():
				RewardNotificationStatus.builder()
						.code(Constants.PENDING_NOTIFY_STATUS)
						.description(Constants.PENDING_NOTIFY_STATUS_DESC)
						.build())
				.notificationDeliveryDescription(description)
				.nocTaskId(nocTaskId)
				.sendTryCount(sendTryCount)
				.rewardOwnerContactNo(rewardOwnerContactNo)
				.removeDate(removeDate)
				.policyEndorsementNumber(policyEndorsementNumber)
				.giftCodes(giftCodes!=null? giftCodes.stream().map(GiftCodeEntity::toModel).toList() :null)
				.build();
	}



}
