package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository;

import com.anadolusigorta.campaignmanagement.domain.saletransaction.model.SaleTransactionOperationType;
import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleCampaignEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface SaleCampaignJpaRepository
		extends JpaRepository<SaleCampaignEntity, Long>, JpaSpecificationExecutor<SaleCampaignEntity> {

	Optional<SaleCampaignEntity> findTopByCampaignInformationCampaignIdAndContactNumberAndRequestTypeOrderByNumberOfParticipationDesc(
			Long campaignId, String contactNumber, SaleTransactionOperationType requestType);

	Optional<SaleCampaignEntity> findTopByCampaignInformationCampaignIdAndRequestTypeOrderByNumberOfParticipationDesc(
			Long campaignId, SaleTransactionOperationType requestType);

	int countByCampaignInformationCampaignIdAndContactNumberAndRequestType(Long campaignId, String contactNumber,
			SaleTransactionOperationType requestType);



	List<SaleCampaignEntity> findAllByCampaignInformationCampaignIdAndRequestType(
			Long campaignId, SaleTransactionOperationType requestType);


	List<SaleCampaignEntity> findAllByCampaignInformationCampaignIdAndRequestTypeAndSaleRewardGiftRewardNotificationStatusDescriptionIn(
			Long campaignId, SaleTransactionOperationType saleTransactionOperationType, List<String> deliveryStatus);




	int countByCampaignInformationCampaignIdAndRequestType(Long campaignId, SaleTransactionOperationType requestType);

	Optional<SaleCampaignEntity> findByContactNumberAndCampaignInformationCampaignIdAndRuleGroupIdAndSoldPolicyDetailPolicyNumber(String contactNumber,Long campaignId,Long ruleGroupId,String policyNumber);

	List<SaleCampaignEntity> findByContactNumberAndSoldPolicyDetailPolicyNumberAndRequestType(String contactNumber,String policyNumber, SaleTransactionOperationType requestType);


}
