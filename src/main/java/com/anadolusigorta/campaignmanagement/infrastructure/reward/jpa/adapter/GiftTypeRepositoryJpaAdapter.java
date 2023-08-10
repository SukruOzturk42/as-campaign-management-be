package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftType;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardGiftTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.GiftTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftTypeRepositoryJpaAdapter implements RewardGiftTypeRepository {

    private final GiftTypeJpaRepository giftTypeJpaRepository;

    @Override
    public List<RewardGiftType> findAll() {
        return giftTypeJpaRepository.findAll()
                .stream()
                .map(GiftTypeEntity::toModel)
                .collect(Collectors.toList());
    }
}
