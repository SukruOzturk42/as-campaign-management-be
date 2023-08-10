package com.anadolusigorta.campaignmanagement.infrastructure.campaigncode.rest.dto.request;

import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftTicketType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRewardGiftTicketRequest {

    private String name;

    public RewardGiftTicketType toModel() {
        return RewardGiftTicketType.builder()
                .name(name)
                .build();
    }

}
