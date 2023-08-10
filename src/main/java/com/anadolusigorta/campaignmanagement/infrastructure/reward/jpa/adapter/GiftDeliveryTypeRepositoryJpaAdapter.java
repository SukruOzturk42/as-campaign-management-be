package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftDeliveryType;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardGiftDeliveryTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftDeliveryTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.GiftDeliveryTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftDeliveryTypeRepositoryJpaAdapter implements RewardGiftDeliveryTypeRepository {

    private final GiftDeliveryTypeJpaRepository giftDeliveryTypeJpaRepository;

    @Override
    public List<RewardGiftDeliveryType> findAll() {
        return giftDeliveryTypeJpaRepository.findAll()
                .stream()
                .map(GiftDeliveryTypeEntity::toModel)
                .collect(Collectors.toList());
    }
}
