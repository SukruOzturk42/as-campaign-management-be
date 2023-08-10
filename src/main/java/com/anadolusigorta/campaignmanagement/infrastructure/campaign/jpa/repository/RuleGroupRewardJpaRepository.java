package com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.RuleGroupRewardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RuleGroupRewardJpaRepository extends JpaRepository<RuleGroupRewardEntity, Long> {

    RuleGroupRewardEntity findByRuleGroupId(Long ruleGroupId);

    List<RuleGroupRewardEntity> findByDiscount_DiscountCodeInformation_Id(Long discountCodeInformationId);

    List<RuleGroupRewardEntity> findByGift_GiftCodeInformation_Id(Long giftCodeInformationId);
}
