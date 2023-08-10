package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftKind;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardGiftKindRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.GiftKindEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.GiftKindJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GiftKindRepositoryJpaAdapter implements RewardGiftKindRepository {
    private  final GiftKindJpaRepository giftKindJpaRepository;


    @Override
    public List<RewardGiftKind> findAll() {
        return giftKindJpaRepository.findAll().stream()
                .map(GiftKindEntity::toModel)
                .collect(Collectors.toList());
    }


}
