package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardNotificationStatus;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardNotificationStatusRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.RewardNotificationStatusJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RewardNotificationStatusRepositoryJpaAdapter implements RewardNotificationStatusRepository {

    private  final RewardNotificationStatusJpaRepository rewardNotificationStatusJpaRepository;


    @Override
    public RewardNotificationStatus findByCode(String code) {
        return rewardNotificationStatusJpaRepository.findByCode(code)
                .orElseThrow(()->new CampaignManagementException("reward.notification.status.not.found"))
                .toModel();
    }
}
