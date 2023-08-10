package com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.port;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftTicketType;

import java.util.List;

public interface PolicySaleRewardGiftTicketTypeRepository {

    List<RewardGiftTicketType> findAll();

    RewardGiftTicketType save(RewardGiftTicketType rewardGiftTicketType);

}
