package com.anadolusigorta.campaignmanagement.domain.reward.port;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftSendMethodType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardGiftSendMethodTypeRepository {

    List<RewardGiftSendMethodType> findAll();
}
