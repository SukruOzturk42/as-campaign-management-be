package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardDiscountKind;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardDiscountKindRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.DiscountKindEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.DiscountKindJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscountKindRepositoryJpaAdapter implements RewardDiscountKindRepository {
    private  final DiscountKindJpaRepository discountKindJpaRepository;


    @Override
    public List<RewardDiscountKind> getRewardDiscountKinds() {
        return discountKindJpaRepository.findAll().stream()
                .map(DiscountKindEntity::toModel)
                .collect(Collectors.toList());
    }


}
