package com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignType;
import com.anadolusigorta.campaignmanagement.domain.campaign.port.CampaignTypeRepository;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardDiscountType;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardDiscountTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.entity.CampaignTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaign.jpa.repository.CampaignTypeJpaRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.DiscountDetailTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.entity.DiscountTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.reward.jpa.repository.DiscountTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscountTypeRepositoryJpaAdapter implements RewardDiscountTypeRepository {
    private  final DiscountTypeJpaRepository discountTypeJpaRepository;


    @Override
    public List<RewardDiscountType> getRewardDiscountTypes() {
        return discountTypeJpaRepository.findAll().stream()
                .map(DiscountTypeEntity::toModel)
                .collect(Collectors.toList());
    }


}
