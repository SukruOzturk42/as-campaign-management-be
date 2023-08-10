package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftDeliveryStartType;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftDeliveryType;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardGiftDeliveryStartTypeRepository;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardGiftDeliveryTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftDeliveryStartTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftDeliveryStartTypeGroup;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftDeliveryTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.GiftDeliveryStartTypeGroupJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.GiftDeliveryStartTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.GiftDeliveryTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftDeliveryStartTypeRepositoryJpaAdapter implements RewardGiftDeliveryStartTypeRepository {

    private final GiftDeliveryStartTypeJpaRepository giftDeliveryStartTypeJpaRepository;
    private final GiftDeliveryStartTypeGroupJpaRepository giftDeliveryStartTypeGroupJpaRepository;
    @Override
    public List<RewardGiftDeliveryStartType> findAllByCampaignTypeId(Long campaignTypeId) {
        return  giftDeliveryStartTypeGroupJpaRepository.findAllByCampaignTypeId(campaignTypeId).stream()
                .map(giftDeliveryStartTypeGroup -> giftDeliveryStartTypeGroup.getGiftDeliveryStartType().toModel())
                .collect(Collectors.toList());
    }
}
