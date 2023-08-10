package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeStatusEnum;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.*;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.port.PolicySaleCampaignRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleCustomerEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleGiftCodeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleRewardCampaignEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.repository.PolicySaleCustomerJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.repository.PolicySaleGiftCodeInformationJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.repository.PolicySaleGiftCodeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.repository.PolicySaleRewardCampaignJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.model.PolicySaleCustomer;
import com.anadolusigorta.campaignmanagement.infrastructure.fileoperation.parser.ExcelParser;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.RewardGiftSendMethodTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PolicySaleCampaignRepositoryJpaAdapter implements PolicySaleCampaignRepository {
	private final PolicySaleRewardCampaignJpaRepository policySaleRewardCampaignJpaRepository;
	private final PolicySaleGiftCodeInformationJpaRepository policySaleGiftCodeInformationJpaRepository;
	private final RewardGiftSendMethodTypeJpaRepository rewardGiftSendMethodTypeJpaRepository;
	private final PolicySaleCustomerJpaRepository policySaleCustomerJpaRepository;
	private final ExcelParser excelParser;
	private final PolicySaleGiftCodeJpaRepository policySaleGiftCodeJpaRepository;

	@Override
	public PolicySaleRewardCampaign createPolicySaleRewardCampaign(
			CreatePolicySaleRewardCampaign createPolicySaleRewardCampaign) {
		var customers = createPolicySaleRewardCampaign.getCustomerList();
		var policySaleRewardCampaignEntity = new PolicySaleRewardCampaignEntity();

		policySaleRewardCampaignEntity.setCampaignName(createPolicySaleRewardCampaign.getCampaignName());

		var campaignGiftCodeInformation = policySaleGiftCodeInformationJpaRepository
				.findById(createPolicySaleRewardCampaign.getPolicySaleGiftCodeInformationId());

		policySaleRewardCampaignEntity
				.setCustomerListFileName(createPolicySaleRewardCampaign.getFileName());
		campaignGiftCodeInformation.ifPresent(policySaleRewardCampaignEntity::setPolicySaleGiftCodeInformationEntity);

		policySaleRewardCampaignEntity
				.setCustomerListFileName(createPolicySaleRewardCampaign.getFileName());
		policySaleRewardCampaignEntity.setPolicySaleCustomers(new HashSet<>());
		rewardGiftSendMethodTypeJpaRepository.findById(createPolicySaleRewardCampaign.getGiftSendMethodTypeId())
				.ifPresent(policySaleRewardCampaignEntity::setRewardGiftSendMethodTypeEntity);

		policySaleRewardCampaignEntity.setCustomerExcelSaveStatus(CustomerExcelSaveStatus.BEING_RECORDED);
		var savedPolicySaleCampaign = policySaleRewardCampaignJpaRepository.save(policySaleRewardCampaignEntity);

		distributeCodeToCustomers(customers, policySaleRewardCampaignEntity);
		return savedPolicySaleCampaign.toModel();
	}

	@Async
	public void distributeCodeToCustomers(List<PolicySaleCustomer> customers,
			PolicySaleRewardCampaignEntity policySaleRewardCampaignEntity) {

		try{
			customers.forEach(item -> {
				var giftCode = findAvailableGiftCode(
						policySaleRewardCampaignEntity.getPolicySaleGiftCodeInformationEntity().getId(),
						item.getCustomerNo());
				policySaleCustomerJpaRepository.save(PolicySaleCustomerEntity.builder()
						.policySaleGiftCodeSendStatus(giftCode != null ? PolicySaleGiftCodeSendStatusEnum.PENDING
								: PolicySaleGiftCodeSendStatusEnum.INSUFFICIENT)
						.customerNo(item.getCustomerNo()).policySaleGiftCode(giftCode)
						.policySaleRewardCampaign(policySaleRewardCampaignEntity)
						.isSent(Boolean.FALSE)
						.giftCodeSendDate(giftCode != null ? LocalDateTime.now().plusDays(1) : null).build());
			});
			policySaleRewardCampaignEntity.setCustomerExcelSaveStatus(CustomerExcelSaveStatus.RECORDED);
		}catch (Exception e){
			policySaleRewardCampaignEntity.setCustomerExcelSaveStatus(CustomerExcelSaveStatus.FAILED);
		}
		policySaleRewardCampaignJpaRepository.save(policySaleRewardCampaignEntity);
	}

	private PolicySaleGiftCodeEntity findAvailableGiftCode(Long giftCodeInformationId, String customerNo) {

		var giftCode = policySaleGiftCodeJpaRepository
				.findTopByPolicySaleGiftCodeInformationIdAndContactNumberIsNullAndIsActiveTrue(giftCodeInformationId)
				.orElse(null);
		if (giftCode != null) {
			giftCode.setContactNumber(customerNo);
			giftCode.setCodeStatus(CodeStatusEnum.USED);
			giftCode.setIsActive(Boolean.FALSE);
			policySaleGiftCodeJpaRepository.save(giftCode);
		}
		return giftCode;
	}

	@Override
	public List<PolicySaleRewardCampaign> getPolicySaleRewardCampaigns() {
		return policySaleRewardCampaignJpaRepository.findAll().stream().map(PolicySaleRewardCampaignEntity::toModel)
				.collect(Collectors.toList());
	}

	@Override
	public List<com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleCustomer> getPolicySaleCampaignCustomerList(Long campaignId) {
		return policySaleCustomerJpaRepository.findAllByPolicySaleRewardCampaignId(campaignId).stream()
				.map(PolicySaleCustomerEntity::toModel).collect(Collectors.toList());
	}

	@Override
	public PolicySaleRewardCampaign getPolicySaleCampaignDetail(Long campaignId) {
		var policySaleRewardCampaign = policySaleRewardCampaignJpaRepository.findById(campaignId);
		return policySaleRewardCampaign.map(PolicySaleRewardCampaignEntity::toModel).orElse(null);
	}

	@Override
	public PolicySaleRewardCampaign distributeCodeToCustomer(Long campaignId) {
		var policySaleRewardCampaign = policySaleRewardCampaignJpaRepository
				.findById(campaignId).orElse(null);
		
		var customerEntityList = policySaleCustomerJpaRepository
				.findAllByPolicySaleRewardCampaignIdAndPolicySaleGiftCodeSendStatus(campaignId,PolicySaleGiftCodeSendStatusEnum.INSUFFICIENT);
		if(customerEntityList != null){
			customerEntityList.forEach(item -> {
				var giftCode = findAvailableGiftCode(
						policySaleRewardCampaign.getPolicySaleGiftCodeInformationEntity().getId(),
						item.getCustomerNo());
				policySaleCustomerJpaRepository.save(PolicySaleCustomerEntity.builder()
						.policySaleGiftCodeSendStatus(giftCode != null ? PolicySaleGiftCodeSendStatusEnum.PENDING
								: PolicySaleGiftCodeSendStatusEnum.INSUFFICIENT)
						.customerNo(item.getCustomerNo()).policySaleGiftCode(giftCode)
						.isSent(false)
						.policySaleRewardCampaign(policySaleRewardCampaign)
						.giftCodeSendDate(giftCode != null ? LocalDateTime.now().plusDays(1) : null).build());
			});
		}
		return null;
	}

	@Override
	public List<com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleCustomer> findAllSuitablePolicySaleRewardCustomer() {
		return policySaleCustomerJpaRepository
				.findAllByIsSentFalseAndGiftCodeSendDateIsBeforeAndPolicySaleGiftCodeNotNullAndPolicySaleGiftCodeSendStatus(
						LocalDateTime.now(), PolicySaleGiftCodeSendStatusEnum.PENDING)
				.stream().map(PolicySaleCustomerEntity::toModel).collect(Collectors.toList());
	}

	@Override
	public void saveSalePolicySaleRewardGift(com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleCustomer policySaleCustomer) {
		var policySaleCustomerEntity = policySaleCustomerJpaRepository.findById(policySaleCustomer.getId())
				.orElse(null);
		if (policySaleCustomerEntity != null) {
			policySaleCustomerEntity.setDescription(policySaleCustomer.getNotificationDeliveryDescription());
			if (PolicySaleGiftCodeSendStatusEnum.SUCCESS.equals(policySaleCustomer.getPolicySaleGiftCodeSendStatus())) {
				policySaleCustomerEntity.setPolicySaleGiftCodeSendStatus(PolicySaleGiftCodeSendStatusEnum.SUCCESS);
				policySaleCustomerEntity.setIsSent(Boolean.TRUE);
			} else {
				policySaleCustomerEntity.setPolicySaleGiftCodeSendStatus(PolicySaleGiftCodeSendStatusEnum.FAILED);
				policySaleCustomerEntity.setIsSent(Boolean.FALSE);
			}
			policySaleCustomerJpaRepository.save(policySaleCustomerEntity);
		}
	}


	@Override
	public List<com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleCustomer> getPolicySaleRewardCodesByGiftCodeInformationId(Long policySaleRewardCodeInformationId) {
		var policySaleRewardCampaign = policySaleRewardCampaignJpaRepository
				.findById(policySaleRewardCodeInformationId).orElse(null);
		return policySaleRewardCampaign.getPolicySaleCustomers().stream().map(PolicySaleCustomerEntity::toModel).collect(Collectors.toList());

	}
}
