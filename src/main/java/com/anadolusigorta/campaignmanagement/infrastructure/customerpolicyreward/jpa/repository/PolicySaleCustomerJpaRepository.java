package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.repository;

import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.model.PolicySaleGiftCodeSendStatusEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PolicySaleCustomerJpaRepository extends JpaRepository<PolicySaleCustomerEntity, Long> {

	List<PolicySaleCustomerEntity> findAllByPolicySaleRewardCampaignId(Long id);

	List<PolicySaleCustomerEntity> findAllByPolicySaleRewardCampaignIdAndPolicySaleGiftCodeSendStatus(Long id,
			PolicySaleGiftCodeSendStatusEnum codeSendStatus);

	List<PolicySaleCustomerEntity> findAllByIsSentFalseAndGiftCodeSendDateIsBeforeAndPolicySaleGiftCodeNotNullAndPolicySaleGiftCodeSendStatus(
			LocalDateTime giftSendStatus, PolicySaleGiftCodeSendStatusEnum codeSendStatusEnum);
}
