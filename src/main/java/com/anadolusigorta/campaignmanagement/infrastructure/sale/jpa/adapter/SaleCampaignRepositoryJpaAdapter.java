package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignRuleGroup;
import com.anadolusigorta.campaignmanagement.domain.campaign.model.RewardContactRoleTypeEnum;
import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.*;
import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.anadolusigorta.campaignmanagement.domain.common.MessageHandler;
import com.anadolusigorta.campaignmanagement.domain.common.model.NotificationDeliveryStatus;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardSendType;
import com.anadolusigorta.campaignmanagement.domain.sale.model.*;
import com.anadolusigorta.campaignmanagement.domain.sale.port.SaleCampaignRepository;
import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter.CampaignInformationRepositoryJpaAdapter;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter.CampaignVersionRepositoryJpaAdapter;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.adapter.CustomerCampaignRepositoryJpaAdapter;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.*;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.*;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.DiscountCodeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.GiftCodeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.DiscountCodeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.GiftCodeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.common.rest.ExceptionConstants;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.RewardNotificationStatusEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.RewardNotificationStatusJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleCampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleRewardGiftEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SoldPolicyDetailEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository.SaleCampaignJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository.SaleRewardGiftJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository.SoldPolicyDetailJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SaleCampaignRepositoryJpaAdapter implements SaleCampaignRepository {

	private final SaleCampaignJpaRepository saleCampaignJpaRepository;
	private final SoldPolicyDetailJpaRepository soldPolicyDetailJpaRepository;
	private final DiscountCodeJpaRepository discountCodeJpaRepository;
	private final GiftCodeJpaRepository giftCodeJpaRepository;
	private final CustomerCampaignJpaRepository customerCampaignJpaRepository;
	private final CustomerCampaignRepositoryJpaAdapter customerCampaignRepositoryJpaAdapter;

	private final SaleRewardGiftJpaRepository saleRewardGiftJpaRepository;
	private final CampaignRuleGroupJpaRepository campaignRuleGroupJpaRepository;
	private final CampaignInformationRepositoryJpaAdapter campaignInformationRepositoryJpaAdapter;

	private final CampaignInformationJpaRepository campaignInformationJpaRepository;
	private final CampaignVersionRepositoryJpaAdapter campaignVersionRepositoryJpaAdapter;
	private final MessageHandler messageHandler;
	private final RewardNotificationStatusJpaRepository rewardNotificationStatusJpaRepository;
	private  final CampaignTypeJpaRepository campaignTypeJpaRepository;


	@Value("${noc.report.status.delivered}")
	private String nocReportDelivered;

	@Value("${reward.send.lastSendDayCount}")
	private long lastSendDayCount;

	@Value("${reward.send.tryCount}")
	private long tryCount;

	@Override
	@Async
	public CompletableFuture<SaleInformation> saveCampaignSaleInformation(CreateNotifySaleCampaign createNotifySaleCampaign) {

		CampaignInformationEntity campaignInformation;
		CampaignRuleGroupEntity campaignRuleGroup;
		if (createNotifySaleCampaign.hasProposal().equals(Boolean.TRUE)) {

			var proposalRecord = soldPolicyDetailJpaRepository
					.findByProposalNumberAndRevisionNumberAndSaleCampaignContactNumber(
							createNotifySaleCampaign.getOldProposalNumber(),
							createNotifySaleCampaign.getOldRevisionNumber(),
							createNotifySaleCampaign.getContactNumber());
			if (proposalRecord.isEmpty()) {
				log.info(String.format("Notify Handle No Proposal With Old Proposal: %s and Old Revision: %s",
						createNotifySaleCampaign.getOldProposalNumber(),
						createNotifySaleCampaign.getOldRevisionNumber()));
				throw new CampaignManagementException(ExceptionConstants.NO_PROPOSAL_WITH_OLD_PROPOSAL_AND_OLD_REVISION,
						createNotifySaleCampaign.getContactNumber());
			} else {
				var version = campaignVersionRepositoryJpaAdapter.findLatestVersionRecordByCampaignIdAndVersion(
						createNotifySaleCampaign.getCampaignId(), createNotifySaleCampaign.getCampaignVersion());

				campaignInformation = campaignInformationRepositoryJpaAdapter
						.findByCampaignIdAndVersionId(createNotifySaleCampaign.getCampaignId(), version.getId());

				campaignRuleGroup = campaignRuleGroupJpaRepository.findById(createNotifySaleCampaign.getRuleGroupId())
						.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.RULE_GROUP_NOT_FOUND));

			}
		} else {
			var campaign = customerCampaignJpaRepository.findByCampaignId(createNotifySaleCampaign.getCampaignId())
					.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.CAMPAIGN_NOT_FOUND));
			var campaignVersion = campaign.getCampaignVersion();

			campaignInformation = campaignVersion.getCampaignInformation();
			campaignRuleGroup = campaignVersion.getCampaignRuleGroups().stream()
					.filter(item -> item.getId().equals(createNotifySaleCampaign.getRuleGroupId())).findFirst()
					.orElseThrow(() -> new CampaignManagementException("rule.group.not.found"));
		}
		checkCampaignCodeRequirement(campaignRuleGroup, createNotifySaleCampaign);
		var saleCampaignEntity = toSaleEntity(createNotifySaleCampaign, campaignInformation.getCampaign(), campaignInformation, campaignRuleGroup);
		var soldPolicyDetailEntity = SoldPolicyDetailEntity.fromModel(createNotifySaleCampaign.getSoldPolicyDetail());
		saleCampaignEntity.setSoldPolicyDetail(soldPolicyDetailEntity);
		soldPolicyDetailEntity.setSaleCampaign(saleCampaignEntity);

		notifyCustomerCampaignCode(createNotifySaleCampaign, campaignRuleGroup.toModel());

		saleCampaignEntity = saleCampaignJpaRepository.save(saleCampaignEntity);
		soldPolicyDetailJpaRepository.save(soldPolicyDetailEntity);

		saveSaleRewardGift(saleCampaignEntity, campaignRuleGroup.getReward(), createNotifySaleCampaign);

		return CompletableFuture.completedFuture(saleCampaignJpaRepository.save(saleCampaignEntity).toModel());

	}

	@Override
	public boolean checkCampaignSaleInformationCodes(CreateNotifySaleCampaign createNotifySaleCampaign) {

		var result = new AtomicBoolean(true);
		if (createNotifySaleCampaign.getInsured() != null) {
			createNotifySaleCampaign.getInsured().forEach(item -> {
				var ruleGroup = campaignRuleGroupJpaRepository.findById(item.getRuleGroupId())
						.orElseThrow(() -> new CampaignManagementException("rule.group.not.found"));

				if (item.getCodeType() != null && item.getCodeType().equals(SaleCodeTypeEnum.CODE)) {
					var discountInformationId = ruleGroup.getReward().getDiscount().getDiscountCodeInformation()
							.getId();
					var code = discountCodeJpaRepository
							.findByDiscountCodeInformationIdAndCode(discountInformationId, item.getCampaignCode())
							.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.CODE_NOT_FOUND,
									item.getContactNumber()));
					var codeType = code.getDiscountCodeInformation().getCodeType().getName();
					if (codeType.equals(CodeTypeEnum.SINGLE_USE_CODE.getValue())) {
						var count = createNotifySaleCampaign.getInsured().stream()
								.filter(t -> t.getCampaignCode().equals(item.getCampaignCode())).count();
						if (count > 1) {
							result.set(false);
						}
					}
				}
			});
		}
		return result.get();
	}

	private void checkCampaignCodeRequirement(CampaignRuleGroupEntity ruleGroup,
			CreateNotifySaleCampaign createNotifySaleCampaign) {
		if (createNotifySaleCampaign.getCampaignCode() != null && !getCampaignType(ruleGroup)) {
			throw new CampaignManagementException(ExceptionConstants.CAMPAIGN_CODE_SHOULD_NOT_BE_PROVIDED,
					createNotifySaleCampaign.getContactNumber());
		}
	}

	private Boolean getCampaignType(CampaignRuleGroupEntity ruleGroup) {
		return ruleGroup.getRelatedCooperation() != null || (ruleGroup.getReward().getDiscount() != null
				&& ruleGroup.getReward().getDiscount().getDiscountCodeInformation() != null);
	}

	@Override
	public List<SaleRewardGift> findAvailableSaleRewardGiftsForSendOperation() {

		ArrayList<String> rewardNotificationStatusNames = getDeliveredAndSendNotificationStatusCodes();

		var saleRewardGiftEntityList = saleRewardGiftJpaRepository
				.findAllBySaleCampaignCampaignInformationIsStartedRewardSendTrueAndRewardNotificationStatusCodeNotInAndRewardSendDateIsBeforeAndSendTryCountLessThan(
						rewardNotificationStatusNames,
						LocalDateTime.now(),10L);
		return saleRewardGiftEntityList.stream().map(SaleRewardGiftEntity::toModel).collect(Collectors.toList());

	}

	@Override
	public List<SaleRewardGift> findAvailableSaleRewardGiftsForReSendOperationByCampaignInformationId(Long campaignId) {

		ArrayList<String> rewardNotificationStatusNames = getDeliveredAndSendNotificationStatusCodes();


		var saleRewardGiftEntityList = saleRewardGiftJpaRepository
				.findAllBySaleCampaignCampaignInformationIdAndRewardNotificationStatusCodeNotInAndRewardSendDateIsBeforeAndSendTryCountGreaterThanEqual(
						campaignId,
						rewardNotificationStatusNames,
						LocalDateTime.now(),10L);
		return saleRewardGiftEntityList.stream().map(SaleRewardGiftEntity::toModel).collect(Collectors.toList());

	}

	@Override
	public void terminateExpiredRewardSendOperation() {

		ArrayList<String> rewardNotificationStatusNames = getDeliveredAndSendNotificationStatusCodes();

		var saleRewardGiftEntityList = saleRewardGiftJpaRepository
				.findAllByRewardNotificationStatusCodeNotInAndRewardLastSendDateIsBefore(rewardNotificationStatusNames,LocalDateTime.now());
		var expiredRewardStatus = rewardNotificationStatusJpaRepository.findByCode(Constants.EXPIRED_NOTIFY_STATUS)
				.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.REWARD_NOTIFICATION_STATUS_NOT_FOUND));

		if (saleRewardGiftEntityList != null) {
			saleRewardGiftEntityList.forEach(item -> {
				try {
					if(item.getGiftCode()!=null){
						reusableCode(expiredRewardStatus, item,item.getGiftCode());

					}
					else if(item.getGiftCodes()!=null){
                      item.getGiftCodes().forEach(t->reusableCode(expiredRewardStatus,item,t));
					}

				} catch (Exception e) {
					log.info(e.getMessage());
					log.info(String.format("error when reward termination  code:%s  contact no:%s",
							item.getSaleCampaign().getCampaignCode(), item.getSaleCampaign().getContactNumber()));
				}

			});
		}
	}

	private void reusableCode(RewardNotificationStatusEntity expiredRewardStatus, SaleRewardGiftEntity item, GiftCodeEntity codeEntity) {

		log.info(String.format("reward termination  code:%s  contact no:%s",
				item.getSaleCampaign().getCampaignCode(), item.getSaleCampaign().getContactNumber()));

		item.setGiftCode(null);
		item.setGiftCodes(null);
		item.setDescription(messageHandler.retrieveMessage("expired.reward.send"));
		item.setRewardNotificationStatus(expiredRewardStatus);

		codeEntity.setContactNumber(null);
		codeEntity.setCodeStatusEnum(CodeStatusEnum.UNUSED);
		giftCodeJpaRepository.save(codeEntity);
		saleRewardGiftJpaRepository.save(item);
	}


	@Override
	public void checkRewardSendOperation() {
		var saleRewardGiftEntityList = saleRewardGiftJpaRepository
				.findAllByNocTaskIdIsNotNullAndRewardNotificationStatusName(Constants.DELIVERED_NOTIFY_STATUS_NAME);
		if (saleRewardGiftEntityList != null) {
			saleRewardGiftEntityList.forEach(item -> {
				try {

					var sendType = item.getSaleCampaign().getRuleGroup().getReward().getGift()
							.getRewardGiftSendMethodTypeEntity().getName();






				} catch (Exception e) {
					log.info(e.getMessage());
					log.info(String.format("error when reward status check  taskId:%s  contact no:%s",
							item.getNocTaskId(), item.getSaleCampaign().getContactNumber()));
				}

			});
		}
	}

	@Override
	public void saveSaleRewardGift(SaleRewardGift saleRewardGift) {
		var saleRewardGiftEntity = saleRewardGiftJpaRepository.findById(saleRewardGift.getId())
				.orElseThrow(() -> new CampaignManagementException("sale.record.not.found"));
		var rewardNotificationStatus = rewardNotificationStatusJpaRepository.findByCode(saleRewardGift.getRewardNotificationStatus().getCode())
				.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.REWARD_NOTIFICATION_STATUS_NOT_FOUND));
		saleRewardGiftEntity.setDescription(saleRewardGift.getNotificationDeliveryDescription());
		saleRewardGiftEntity.setNocTaskId(saleRewardGift.getNocTaskId());
		saleRewardGiftEntity.setSendTryCount(saleRewardGift.getSendTryCount());
		saleRewardGiftEntity.setRewardNotificationStatus(rewardNotificationStatus);
		saleRewardGiftEntity.setRewardOwnerContactNo(saleRewardGift.getRewardOwnerContactNo());
		saleRewardGiftEntity.setRewardSendDate(saleRewardGift.getRewardSendDate());

		var soldDetail=saleRewardGiftEntity.getSaleCampaign().getSoldPolicyDetail();
		if(soldDetail!=null){
			var policyNumber=saleRewardGift.getSaleInformation().getSoldPolicyDetail().getPolicyNumber();

			log.info(String.format("Sold detail id %s policyNUmber %s",soldDetail.getId(),policyNumber));


			soldDetail.setPolicyNumber(policyNumber);
			soldPolicyDetailJpaRepository.save(soldDetail);
		}

		saleRewardGiftJpaRepository.save(saleRewardGiftEntity);

	}

	@Override
	public void saveSaleRewardGift(SaleRewardGift saleRewardGift, GiftCodeInformation giftCodeInformation) {
		var saleRewardGiftEntity = saleRewardGiftJpaRepository.findById(saleRewardGift.getId())
				.orElseThrow(() -> new CampaignManagementException("sale.record.not.found"));

		var rewardNotificationStatus = rewardNotificationStatusJpaRepository
				.findByCode(saleRewardGift.getRewardNotificationStatus().getCode())
				.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.REWARD_NOTIFICATION_STATUS_NOT_FOUND));

		saleRewardGiftEntity.setDescription(saleRewardGift.getNotificationDeliveryDescription());
		saleRewardGiftEntity.setNocTaskId(saleRewardGift.getNocTaskId());
		saleRewardGiftEntity.setSendTryCount(saleRewardGift.getSendTryCount());
		saleRewardGiftEntity.setRewardNotificationStatus(rewardNotificationStatus);
		saleRewardGiftEntity.setRewardOwnerContactNo(saleRewardGift.getRewardOwnerContactNo());
		saleRewardGiftEntity.setRewardSendDate(saleRewardGift.getRewardSendDate());

		if(saleRewardGift.getGiftCodes()!=null && !saleRewardGift.getGiftCodes().isEmpty()){

			var giftCodeEntities= saleRewardGift.getGiftCodes().stream().map(item -> {
				var giftCodeEntity = giftCodeJpaRepository
						.findById(item.getId())
						.orElseThrow(()->new CampaignManagementException("reward.code.not.found"));
					giftCodeEntity.setCodeStatusEnum(CodeStatusEnum.USED);
					giftCodeEntity.setContactNumber(saleRewardGiftEntity.getRewardOwnerContactNo());
					giftCodeEntity.setSaleRewardGift(saleRewardGiftEntity);

				return giftCodeEntity;
			}).toList();

			giftCodeEntities=giftCodeJpaRepository.saveAll(giftCodeEntities);
			saleRewardGiftEntity.setGiftCodes(giftCodeEntities);
		}
		saleRewardGiftJpaRepository.save(saleRewardGiftEntity);

	}



	public List<SaleInformation> getAllSalesInformationByCampaignIdAndSaleTypeAndDeliveryType(Long campaignId,
																							   SaleTransactionOperationType requestType,
																							  String deliveryStatus) {
		List<String> rewardNotificationStatusList = new ArrayList<>();

		switch (NotificationDeliveryStatus.of(deliveryStatus != null ? deliveryStatus : "")) {
		case FAILED:
			rewardNotificationStatusList.add(NotificationDeliveryStatus.FAILED.getValue());
			rewardNotificationStatusList.add(NotificationDeliveryStatus.CM_FAILED.getValue());
			return  saleCampaignJpaRepository
					.findAllByCampaignInformationCampaignIdAndRequestTypeAndSaleRewardGiftRewardNotificationStatusDescriptionIn(
							campaignId, requestType, rewardNotificationStatusList)
					.stream().map(SaleCampaignEntity::toModel).collect(Collectors.toList());


		case PENDING:
			rewardNotificationStatusList.add(NotificationDeliveryStatus.PENDING.getValue());
			return  saleCampaignJpaRepository
					.findAllByCampaignInformationCampaignIdAndRequestTypeAndSaleRewardGiftRewardNotificationStatusDescriptionIn(
							campaignId, requestType, rewardNotificationStatusList)
					.stream().map(SaleCampaignEntity::toModel).collect(Collectors.toList());


		case SENT:
			rewardNotificationStatusList.add(NotificationDeliveryStatus.SENT.getValue());
			rewardNotificationStatusList.add(NotificationDeliveryStatus.READ_DELIVERED.getValue());
			return saleCampaignJpaRepository
					.findAllByCampaignInformationCampaignIdAndRequestTypeAndSaleRewardGiftRewardNotificationStatusDescriptionIn(
							campaignId, requestType, rewardNotificationStatusList)
					.stream().map(SaleCampaignEntity::toModel).collect(Collectors.toList());


		default:
			return  saleCampaignJpaRepository
							.findAllByCampaignInformationCampaignIdAndRequestType(campaignId, requestType)
							.stream().map(SaleCampaignEntity::toModel).collect(Collectors.toList());


		}
	}



	@Override
	public List<SaleCampaignInformation> getSaleCampaignInformation() {

		var campaignType=campaignTypeJpaRepository.findByName(Constants.CUSTOMER_CAMPAIGN_TYPE_NAME)
				.orElseThrow(()->new CampaignManagementException("campaign.type.not.found"));

		var saleCampaign = customerCampaignRepositoryJpaAdapter
				.findContactCampaignsInformation("",null);
		return saleCampaign.stream().map(item -> {
			/*var proposalCount = saleCampaignJpaRepository.countByCampaignInformationCampaignIdAndRequestType(
					item.getCampaign().getId(), SaleTransactionOperationType.PROPOSAL);

			var saleCount = saleCampaignJpaRepository.countByCampaignInformationCampaignIdAndRequestType(
					item.getCampaign().getId(), SaleTransactionOperationType.POLICY);*/

			return SaleCampaignInformation.builder()
					.campaignInformation(item)
					//.totalProposalCount(proposalCount)
					//.totalSaleCount(saleCount)
					.build();
		}).collect(Collectors.toList());
	}




	private void notifyCustomerCampaignCode(CreateNotifySaleCampaign createNotifySaleCampaign,
			CampaignRuleGroup campaignRuleGroup) {
		if (createNotifySaleCampaign.getCampaignCode() != null && campaignRuleGroup.getCampaignReward() != null
				&& campaignRuleGroup.getCampaignReward().getCampaignRewardDiscount() != null && campaignRuleGroup
						.getCampaignReward().getCampaignRewardDiscount().getDiscountCodeInformation() != null) {
			var code = discountCodeJpaRepository.findByCodeAndIsActiveTrue(createNotifySaleCampaign.getCampaignCode())
					.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.CODE_NOT_FOUND));
			if (SaleTransactionOperationType.PROPOSAL.equals(createNotifySaleCampaign.getRequestType())) {
				codeStatusMatch(code, CodeStatusEnum.PROPOSED, createNotifySaleCampaign.getContactNumber());
			} else if (SaleTransactionOperationType.POLICY.equals(createNotifySaleCampaign.getRequestType())) {
				codeStatusMatch(code, CodeStatusEnum.USED, createNotifySaleCampaign.getContactNumber());
			} else {
				throw new CampaignManagementException(ExceptionConstants.UNKNOWN_REQUEST_TYPE,
						createNotifySaleCampaign.getContactNumber());
			}
		}
	}

	private void codeStatusMatch(DiscountCodeEntity code, CodeStatusEnum codeStatusEnum, String contactNumber) {
		if (CodeStatusEnum.USED.equals(code.getCodeStatusEnum())) {
			throw new CampaignManagementException(ExceptionConstants.CODE_USED);
		} else if (!"UNLIMITED_USE_CODE".equals(code.getDiscountCodeInformation().getCodeType().getName())) {
			code.setCodeStatusEnum(codeStatusEnum);
			code.setContactNumber(contactNumber);
			discountCodeJpaRepository.save(code);
		}
	}

	@Override
	public SaleInformationSummary getSaleCampaignSummary(Long campaignId) {
		var saleCampaign = customerCampaignJpaRepository.findByCampaignId(campaignId)
				.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.CAMPAIGN_NOT_FOUND));

		var proposalCount = saleCampaignJpaRepository.countByCampaignInformationCampaignIdAndRequestType(campaignId,
				SaleTransactionOperationType.PROPOSAL);

		var saleCount = saleCampaignJpaRepository.countByCampaignInformationCampaignIdAndRequestType(campaignId,
				SaleTransactionOperationType.POLICY);


		return SaleInformationSummary.builder()
				.campaignId(saleCampaign.getCampaignVersion().getCampaignInformation().getCampaign().getId())
				.campaignName(saleCampaign.getCampaignVersion().getCampaignInformation().getCampaignName())
				.isStartedRewardSend(
						saleCampaign.getCampaignVersion().getCampaignInformation().getIsStartedRewardSend())
				.isTriggeredRewardSend(
						saleCampaign.getCampaignVersion().getCampaignInformation().getIsTriggeredRewardSend())
				.totalSaleCount(saleCount).totalProposalCount(proposalCount).build();
	}

	@Override
	public Boolean triggerSaleRewardSendOperation(Long campaignId) {
		var campaignInfo = campaignInformationRepositoryJpaAdapter.findByCampaignId(campaignId);
		campaignInfo.setIsTriggeredRewardSend(Boolean.TRUE);
		campaignInformationRepositoryJpaAdapter.save(campaignInfo);
		return Boolean.TRUE;
	}

	@Override
	public Boolean triggerSaleRewardSendOperation(Long campaignInfoId, Boolean triggerValue) {
		var campaignInfo = campaignInformationJpaRepository.findById(campaignInfoId)
				.orElseThrow(()->new CampaignManagementException(ExceptionConstants.CAMPAIGN_NOT_FOUND));
		campaignInfo.setIsTriggeredRewardSend(triggerValue);
		campaignInformationJpaRepository.save(campaignInfo);
		return triggerValue;
	}

	@Override
	public Boolean startSaleRewardSendOperation(Long campaignId) {
		var campaignInfo = campaignInformationRepositoryJpaAdapter.findByCampaignId(campaignId);
		campaignInfo.setIsStartedRewardSend(Boolean.TRUE);
		campaignInformationRepositoryJpaAdapter.save(campaignInfo);
		return Boolean.TRUE;
	}

	@Override
	public Boolean stopSaleRewardSendOperation(Long campaignId) {
		var campaignInfo = campaignInformationRepositoryJpaAdapter.findByCampaignId(campaignId);
		campaignInfo.setIsStartedRewardSend(Boolean.FALSE);
		campaignInformationRepositoryJpaAdapter.save(campaignInfo);
		return Boolean.TRUE;
	}

	@Override
	public Boolean stopSaleCampaignRewardSendOperation() {
		try {
			var customerCampaigns = customerCampaignJpaRepository.findByCampaignVersionCampaignInformationIsStartedRewardSend(Boolean.TRUE);
			var campaignsInfo = customerCampaigns.stream().map(customerCampaignEntity -> customerCampaignEntity.getCampaignVersion().getCampaignInformation()).toList();
			campaignsInfo.forEach(item -> item.setIsStartedRewardSend(Boolean.FALSE));
			campaignInformationJpaRepository.saveAll(campaignsInfo);
			return Boolean.TRUE;
		}
		catch (Exception e){
			return Boolean.FALSE;
		}
	}

	@Override
	public Boolean startCampaignSaleRewardSendOperation() {
		try {
			var customerCampaigns = customerCampaignJpaRepository.findByCampaignVersionCampaignInformationIsStartedRewardSend(Boolean.FALSE);
			var campaignsInfo = customerCampaigns.stream().map(customerCampaignEntity -> customerCampaignEntity.getCampaignVersion().getCampaignInformation()).toList();
			campaignsInfo.forEach(item -> item.setIsStartedRewardSend(Boolean.TRUE));
			campaignInformationJpaRepository.saveAll(campaignsInfo);
			return Boolean.TRUE;
		}
		catch (Exception e){
			return Boolean.FALSE;
		}
	}

	@Override
	public RemoveSaleReward removeContactSaleReward(RemoveSaleReward removeSaleReward) {

		var saleRecord=saleCampaignJpaRepository
				.findByContactNumberAndCampaignInformationCampaignIdAndRuleGroupIdAndSoldPolicyDetailPolicyNumber(removeSaleReward.getContactNumber(),
						removeSaleReward.getCampaignId(), removeSaleReward.getRuleGroupId(), removeSaleReward.getPolicyNumber())
				.orElseThrow(()->new CampaignManagementException("sale.not.found"));

		var deleteRewardStatus = rewardNotificationStatusJpaRepository
				.findByCode(Constants.DELETE_REWARD_STATUS)
				.orElseThrow(() -> new CampaignManagementException(ExceptionConstants.REWARD_NOTIFICATION_STATUS_NOT_FOUND));


		var rewardRecord=saleRecord.getSaleRewardGift();

		if(rewardRecord!=null){

			rewardRecord.setRewardNotificationStatus(deleteRewardStatus);

			rewardRecord.setDescription(deleteRewardStatus.getDescription());

			rewardRecord.setRemoveDate(LocalDateTime.now());

			rewardRecord.setPolicyEndorsementNumber(removeSaleReward.getPolicyEndorsementNumber());

			saleRewardGiftJpaRepository.save(rewardRecord);
		}


		return removeSaleReward;
	}

	@Override
	public SaleRewardGift findBySaleRewardId(Long id) {

		return  saleRewardGiftJpaRepository.findById(id)
				.orElseThrow(()->new CampaignManagementException("sale.reward.not.found"))
				.toModel();

	}

	@Override
	public int contactSaleCountInCampaign(String contactNo, Long campaignId) {
		return saleCampaignJpaRepository.countByCampaignInformationCampaignIdAndContactNumberAndRequestType(
				campaignId, contactNo,
				SaleTransactionOperationType.POLICY);
	}

	@Override
	public Boolean hasContactOldProposal(String contactNo, String proposalNumber, String revisionNumber) {
		var soldDetail=soldPolicyDetailJpaRepository
				.findByProposalNumberAndRevisionNumberAndSaleCampaignContactNumber(proposalNumber,
						revisionNumber,
						contactNo);
		return !soldDetail.isEmpty();
	}

	private SaleCampaignEntity toSaleEntity(CreateNotifySaleCampaign createNotifySaleCampaign,CampaignEntity campaignEntity,
			CampaignInformationEntity campaignInformation, CampaignRuleGroupEntity campaignRuleGroupEntity) {
		var saleCampaignEntity = new SaleCampaignEntity();
		saleCampaignEntity.setContactNumber(createNotifySaleCampaign.getContactNumber());
		saleCampaignEntity.setCampaignCode(createNotifySaleCampaign.getCampaignCode());
		saleCampaignEntity.setCampaign(campaignEntity);
		saleCampaignEntity.setCampaignInformation(campaignInformation);
		saleCampaignEntity.setCampaignVersion(createNotifySaleCampaign.getCampaignVersion());
		saleCampaignEntity.setRuleGroup(campaignRuleGroupEntity);
		saleCampaignEntity.setOrderOfParticipation(getNumberOfParticipation(createNotifySaleCampaign));
		saleCampaignEntity.setNumberOfParticipation(getOrderOfParticipation(createNotifySaleCampaign));
		saleCampaignEntity.setRequestType(createNotifySaleCampaign.getRequestType());
		return saleCampaignEntity;
	}

	private Integer getNumberOfParticipation(CreateNotifySaleCampaign createNotifySaleCampaign) {
		var sale = saleCampaignJpaRepository
				.findTopByCampaignInformationCampaignIdAndContactNumberAndRequestTypeOrderByNumberOfParticipationDesc(
						createNotifySaleCampaign.getCampaignId(), createNotifySaleCampaign.getContactNumber(),
						SaleTransactionOperationType.POLICY);
		if (sale.isEmpty()) {
			return Constants.DEFAULT_PARTICIPATION_NUMBER;
		} else {
			var value = sale.get().getNumberOfParticipation();
			return ++value;
		}
	}

	private Integer getOrderOfParticipation(CreateNotifySaleCampaign createNotifySaleCampaign) {
		var sale = saleCampaignJpaRepository
				.findTopByCampaignInformationCampaignIdAndRequestTypeOrderByNumberOfParticipationDesc(
						createNotifySaleCampaign.getCampaignId(), SaleTransactionOperationType.POLICY);
		if (sale.isEmpty()) {
			return Constants.DEFAULT_PARTICIPATION_ORDER;
		} else {
			var value = sale.get().getOrderOfParticipation();
			return ++value;
		}
	}

	private void saveSaleRewardGift(SaleCampaignEntity saleCampaignEntity, RuleGroupRewardEntity reward,
			CreateNotifySaleCampaign createNotifySaleCampaign) {

		if (reward.getGift() != null
				&& SaleTransactionOperationType.POLICY.equals(createNotifySaleCampaign.getRequestType())) {
			var saleRewardGiftEntity = new SaleRewardGiftEntity();

			var notificationStatus=rewardNotificationStatusJpaRepository.findByCode(Constants.PENDING_NOTIFY_STATUS)
							.orElseThrow(()->new CampaignManagementException(ExceptionConstants.REWARD_NOTIFICATION_STATUS_NOT_FOUND));

			saleRewardGiftEntity.setSaleCampaign(saleCampaignEntity);
			var giftDeliveryType = reward.getGift().getGiftDeliveryType().getName();
			var roleType = RewardContactRoleTypeEnum.of(reward.getGift().getRewardRole().getName());
			var roleTypeRequest = createNotifySaleCampaign.getSoldPolicyDetail().getRewardContactRoleType();
			saleRewardGiftEntity.setRewardSendDate(
					getRewardSendTime(reward.getGift(), saleCampaignEntity.getCampaignInformation()));
			saleRewardGiftEntity
					.setRewardLastSendDate(saleRewardGiftEntity.getRewardSendDate().plusDays(lastSendDayCount));
			saleRewardGiftEntity.setSendTryCount(0L);
			saleRewardGiftEntity.setRewardNotificationStatus(notificationStatus);
			var rewardCount = saleRewardGiftJpaRepository
					.countAllBySaleCampaignRuleGroupId(reward.getRuleGroup().getId());
			saveSaleRewardEntity(saleCampaignEntity, reward, saleRewardGiftEntity, giftDeliveryType, rewardCount,
					roleType, roleTypeRequest);
		}

	}

	private void saveSaleRewardEntity(SaleCampaignEntity saleCampaignEntity, RuleGroupRewardEntity reward,
			SaleRewardGiftEntity saleRewardGiftEntity, String giftDeliveryType, int rewardCount,
			RewardContactRoleTypeEnum rewardContactRoleType, RewardContactRoleTypeEnum requestRewardContactRoleType) {
		if ((reward.getGift().getTotalCustomerCount() == null || rewardCount < reward.getGift().getTotalCustomerCount())
				&& checkIfContactCanApply(reward, saleCampaignEntity)) {
			switch (giftDeliveryType) {
			case "each_customer":
				saleRewardGiftJpaRepository.save(saleRewardGiftEntity);
				saleCampaignEntity.setSaleRewardGift(saleRewardGiftEntity);
				break;
			case "each_x_customer":
				if (saleCampaignEntity.getNumberOfParticipation() <= reward.getGift().getProductDeliveryOrder()) {
					saleRewardGiftJpaRepository.save(saleRewardGiftEntity);
					saleCampaignEntity.setSaleRewardGift(saleRewardGiftEntity);
				}
				break;
			case "custom":
				if (saleCampaignEntity.getNumberOfParticipation() % reward.getGift().getProductDeliveryOrder() == 0) {
					saleRewardGiftJpaRepository.save(saleRewardGiftEntity);
					saleCampaignEntity.setSaleRewardGift(saleRewardGiftEntity);
				}
				break;
			default: throw new CampaignManagementException("gift.delivery.type.not.found");
			}
		}
	}

	private LocalDateTime getRewardSendTime(RuleGroupRewardGiftEntity gift,
			CampaignInformationEntity campaignInformation) {
		if (gift.getGiftDeliveryStartType() != null) {
			if ("atPolicyIssueDate".equals(gift.getGiftDeliveryStartType().getName()))
				return LocalDateTime.now().plusDays(gift.getDayAfterDeliveryStart());
			else
				return campaignInformation.getCampaignEndDate().plusDays(gift.getDayAfterDeliveryStart());
		}
		return null;
	}

	private Boolean checkIfContactCanApply(RuleGroupRewardEntity ruleGroupRewardEntity,
			SaleCampaignEntity saleCampaignEntity) {
		var giftPayment = ruleGroupRewardEntity.getGift().getGiftPaymentType();
		if ("perCampaign".equals(giftPayment.getName())) {
			var rewardsAlreadyGot = saleRewardGiftJpaRepository
					.findAllBySaleCampaignRuleGroupIdAndSaleCampaignContactNumber(
							ruleGroupRewardEntity.getRuleGroup().getId(), saleCampaignEntity.getContactNumber());
			if (!rewardsAlreadyGot.isEmpty()) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	private static ArrayList<String> getDeliveredAndSendNotificationStatusCodes() {
		var rewardNotificationStatusNames=new ArrayList<String>();

		rewardNotificationStatusNames.add(Constants.SENT_NOTIFY_STATUS);
		rewardNotificationStatusNames.add(Constants.DELIVERED_NOTIFY_STATUS);
		rewardNotificationStatusNames.add(Constants.CAMPUS_NOTIFY_CANCEL_STATUS);
		rewardNotificationStatusNames.add(Constants.EXPIRED_NOTIFY_STATUS);
		rewardNotificationStatusNames.add(Constants.DELETE_REWARD_STATUS);
		return rewardNotificationStatusNames;
	}
}
