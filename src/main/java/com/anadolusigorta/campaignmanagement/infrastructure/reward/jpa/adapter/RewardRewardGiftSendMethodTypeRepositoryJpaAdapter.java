package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftSendMethodType;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardGiftSendMethodTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.RewardGiftSendMethodTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.RewardGiftSendMethodTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardRewardGiftSendMethodTypeRepositoryJpaAdapter implements RewardGiftSendMethodTypeRepository {

    private final RewardGiftSendMethodTypeJpaRepository rewardGiftSendMethodTypeJpaRepository;

    @Override
    public List<RewardGiftSendMethodType> findAll() {
        return rewardGiftSendMethodTypeJpaRepository.findAll()
                .stream()
                .map(RewardGiftSendMethodTypeEntity::toModel)
                .collect(Collectors.toList());
    }
}
