package com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.customerpolicyreward.port.PolicySaleRewardGiftTicketTypeRepository;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftTicketType;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.RewardGiftTicketTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.entity.PolicySaleRewardGiftTicketTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.customerpolicyreward.jpa.repository.PolicySaleRewardGiftTicketTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PolicySaleRewardGiftTicketTypeRepositoryJpaAdapter implements PolicySaleRewardGiftTicketTypeRepository {

    private final PolicySaleRewardGiftTicketTypeJpaRepository policySaleRewardGiftTicketTypeJpaRepository;

    @Override
    public List<RewardGiftTicketType> findAll() {
        return policySaleRewardGiftTicketTypeJpaRepository.findAll().stream().map(PolicySaleRewardGiftTicketTypeEntity::toModel).collect(Collectors.toList());
    }

    @Override
    public RewardGiftTicketType save(RewardGiftTicketType rewardGiftTicketType) {
        var giftTicket = policySaleRewardGiftTicketTypeJpaRepository.findByName(rewardGiftTicketType.getName()).orElse(null);
        if (giftTicket == null)
            return policySaleRewardGiftTicketTypeJpaRepository.save(PolicySaleRewardGiftTicketTypeEntity.builder()
                    .name(rewardGiftTicketType.getName())
                    .description(rewardGiftTicketType.getName())
                    .build()).toModel();
        else
            throw new CampaignManagementException("cm.constraint.code.gift.ticket.type.already.defined");
    }
}
