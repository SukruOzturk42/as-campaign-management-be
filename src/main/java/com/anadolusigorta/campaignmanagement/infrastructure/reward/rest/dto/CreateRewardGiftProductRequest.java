package com.anadolusigorta.campaignmanagement.infrastructure.reward.rest.dto;

import com.anadolusigorta.campaignmanagement.domain.campaign.model.CampaignGroup;
import com.anadolusigorta.campaignmanagement.domain.reward.model.RewardGiftProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRewardGiftProductRequest {


    private String name;

    public RewardGiftProduct toModel() {
        return RewardGiftProduct.builder()
                .name(name)
                .build();
    }

}
