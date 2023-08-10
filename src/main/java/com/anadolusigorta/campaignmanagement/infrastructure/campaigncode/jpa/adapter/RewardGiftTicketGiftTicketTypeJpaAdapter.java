package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.adapter;

import com.anadolusigorta.campaignmanagement.domain.common.CampaignManagementException;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftTicketType;
import com.anadolusigorta.campaignmanagement.domain.reward.port.RewardGiftTicketTypeRepository;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.entity.RewardGiftTicketTypeEntity;
import com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.jpa.repository.RewardGiftTicketTypeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardGiftTicketGiftTicketTypeJpaAdapter implements RewardGiftTicketTypeRepository {

    private final RewardGiftTicketTypeJpaRepository rewardGiftTicketTypeJpaRepository;

    @Override
    public List<RewardGiftTicketType> findAll() {
        return rewardGiftTicketTypeJpaRepository.findAll().stream().map(RewardGiftTicketTypeEntity::toModel).collect(Collectors.toList());
    }

    @Override
    public RewardGiftTicketType save(RewardGiftTicketType rewardGiftTicketType) {
        var giftTicket = rewardGiftTicketTypeJpaRepository.findByName(rewardGiftTicketType.getName()).orElse(null);
        if (giftTicket == null)
            return rewardGiftTicketTypeJpaRepository.save(RewardGiftTicketTypeEntity.builder()
                    .name(rewardGiftTicketType.getName())
                    .description(rewardGiftTicketType.getName())
                    .build()).toModel();
        else
            throw new CampaignManagementException("cm.constraint.code.gift.ticket.type.already.defined");
    }
}
