package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardDiscountDetailType;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardDiscountDetailTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.DiscountDetailTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.DiscountDetailTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscountDetailTypeRepositoryJpaAdapter implements RewardDiscountDetailTypeRepository {
    private  final DiscountDetailTypeJpaRepository discountDetailTypeJpaRepository;


    @Override
    public List<RewardDiscountDetailType> getRewardDiscountDetailTypes() {
        return discountDetailTypeJpaRepository.findAll().stream()
                .map(DiscountDetailTypeEntity::toModel)
                .collect(Collectors.toList());
    }


}
