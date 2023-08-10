package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftPaymentType;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardGiftPaymentTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftPaymentTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.GiftPaymentTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftPaymentTypeRepositoryJpaAdapter implements RewardGiftPaymentTypeRepository {

    private final GiftPaymentTypeJpaRepository giftPaymentTypeJpaRepository;

    @Override
    public List<RewardGiftPaymentType> findAll() {
        return giftPaymentTypeJpaRepository.findAll()
                .stream()
                .map(GiftPaymentTypeEntity::toModel)
                .collect(Collectors.toList());
    }
}
