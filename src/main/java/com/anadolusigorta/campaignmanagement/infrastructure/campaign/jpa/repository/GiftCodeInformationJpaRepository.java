package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.DiscountCodeInformationEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.GiftCodeInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface GiftCodeInformationJpaRepository extends JpaRepository<GiftCodeInformationEntity, Long> , JpaSpecificationExecutor<GiftCodeInformationEntity> {

    Optional<GiftCodeInformationEntity> findByGiftCodeRewardTypeId(Long giftCodeRewardTypeId);

    Optional<GiftCodeInformationEntity> findByGiftCodeRewardTypeIdAndRewardCompanyInformationId(Long giftCodeRewardTypeId, Long rewardCompanyInformationId);

}
