package com.anadolusigorta.campaignmanagement.domain.reward.port;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftTicketType;

import java.util.List;

public interface RewardGiftTicketTypeRepository {

    List<RewardGiftTicketType> findAll();

    RewardGiftTicketType save(RewardGiftTicketType rewardGiftTicketType);

}
