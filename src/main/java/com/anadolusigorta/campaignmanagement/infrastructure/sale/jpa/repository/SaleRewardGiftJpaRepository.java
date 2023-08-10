package com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.repository;

import com.anadolusigorta.campaignmanagement.infrastructure.sale.jpa.entity.SaleRewardGiftEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRewardGiftJpaRepository extends JpaRepository<SaleRewardGiftEntity, Long> {


    List<SaleRewardGiftEntity> findAllByRewardNotificationStatusCodeNotInAndRewardLastSendDateIsBefore(List<String> rewardNotificationNames,LocalDateTime now);


    List<SaleRewardGiftEntity> findAllByNocTaskIdIsNotNullAndRewardNotificationStatusName(String nocStatusName);


    List<SaleRewardGiftEntity> findAllBySaleCampaignCampaignInformationIsStartedRewardSendTrueAndRewardNotificationStatusCodeNotInAndRewardSendDateIsBeforeAndSendTryCountLessThan(List<String> rewardNotificationNames,LocalDateTime now,Long tryCount);


    List<SaleRewardGiftEntity> findAllBySaleCampaignCampaignInformationIdAndRewardNotificationStatusCodeNotInAndRewardSendDateIsBeforeAndSendTryCountGreaterThanEqual(Long campaignInformationId,List<String> rewardNotificationNames,LocalDateTime now,Long tryCount);


    List<SaleRewardGiftEntity> findAllBySaleCampaignRuleGroupIdAndSaleCampaignContactNumber(Long ruleGroupId, String contactNumber);

    int countAllBySaleCampaignRuleGroupId(Long ruleGroupId);

}
