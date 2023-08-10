package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.repository;

import com.anadolusigorta.campaignmanagement.domain.campaigncode.model.CodeStatusEnum;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleGiftCodeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleGiftCodeInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PolicySaleGiftCodeJpaRepository extends JpaRepository<PolicySaleGiftCodeEntity,Long> {


    Optional<PolicySaleGiftCodeEntity> findTopByPolicySaleGiftCodeInformationIdAndCodeAndContactNumberIsNullAndIsActiveTrue(Long policySaleGiftCodeInformationId, String code);

    Optional<PolicySaleGiftCodeEntity> findTopByPolicySaleGiftCodeInformationIdAndContactNumberIsNullAndIsActiveTrue(Long policySaleGiftCodeInformationId);

    Long countByPolicySaleGiftCodeInformationId(Long policySaleGiftCodeInformationId);

    Long countByPolicySaleGiftCodeInformationIdAndCodeStatus(Long policySaleGiftCodeInformationId, CodeStatusEnum codeStatus);

    List<PolicySaleGiftCodeEntity> findAllByPolicySaleGiftCodeInformationId(Long giftCodeInformationId);

}
